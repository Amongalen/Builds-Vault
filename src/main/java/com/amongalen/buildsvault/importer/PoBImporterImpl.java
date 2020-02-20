package com.amongalen.buildsvault.importer;

import com.amongalen.buildsvault.model.build.PathOfBuilding;
import com.amongalen.buildsvault.model.build.TreeNode;
import com.amongalen.buildsvault.util.CompressionUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@Slf4j
@AllArgsConstructor
@Service
public class PoBImporterImpl implements PoBImporter {

    private final TreeImporter treeImporter;

    @Override
    public PathOfBuilding importBuild(String pobLink) {
        PathOfBuilding pathOfBuilding = getPathOfBuildingFromPoBString(pobLink);
        if (pathOfBuilding != null) {
            String treeUrl = pathOfBuilding.getTree().getSpec().getUrl();
            List<TreeNode> treeNodes = treeImporter.importTreeKeystones(treeUrl);
            pathOfBuilding.setKeystones(treeNodes);
        }
        return pathOfBuilding;
    }

    private PathOfBuilding getPathOfBuildingFromPoBString(String pobString) {
        PathOfBuilding pathOfBuilding = null;
        try {
            String xml = base64ToXml(pobString);
            JacksonXmlModule xmlModule = new JacksonXmlModule();
            xmlModule.setDefaultUseWrapper(false);
            XmlMapper xmlMapper = new XmlMapper(xmlModule);
            pathOfBuilding = xmlMapper.readValue(Objects.requireNonNull(xml), PathOfBuilding.class);
        } catch (DataFormatException | IOException e) {
            log.error("Something went wrong while importing PoB build ", e);
        }
        return pathOfBuilding;
    }

    static String base64ToXml(String raw) throws IOException, DataFormatException {

        String replace = raw.replace('-', '+').replace('_', '/').trim();
        byte[] byteValueBase64Decoded = Base64.getDecoder().decode(replace);
        String inflatedXml = inflate(byteValueBase64Decoded);
        return inflatedXml;
    }

    private static String inflate(byte[] byteValueBase64Decoded) throws IOException, DataFormatException {
        String inflatedXml = "";
        //inflate
        byte[] decompressed = CompressionUtils.decompress(byteValueBase64Decoded);
        inflatedXml = new String(decompressed, StandardCharsets.UTF_8);
        return inflatedXml;
    }

}

