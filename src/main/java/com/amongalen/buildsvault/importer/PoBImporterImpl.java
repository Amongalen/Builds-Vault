package com.amongalen.buildsvault.importer;

import com.amongalen.buildsvault.model.pob.PoBPathOfBuilding;
import com.amongalen.buildsvault.util.CompressionUtils;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import java.util.zip.DataFormatException;

@Slf4j
@AllArgsConstructor
@Service
public class PoBImporterImpl implements PoBImporter {

    @Override
    public PoBPathOfBuilding importBuild(String pobLink) {
        return getPathOfBuildingFromPoBString(pobLink);
    }

    private static PoBPathOfBuilding getPathOfBuildingFromPoBString(String pobString) {
        PoBPathOfBuilding pathOfBuilding = null;
        try {
            String xml = base64ToXml(pobString);
            JacksonXmlModule xmlModule = new JacksonXmlModule();
            xmlModule.setDefaultUseWrapper(false);
            XmlMapper xmlMapper = new XmlMapper(xmlModule);
            pathOfBuilding = xmlMapper.readValue(Objects.requireNonNull(xml), PoBPathOfBuilding.class);
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
        //inflate
        byte[] decompressed = CompressionUtils.decompress(byteValueBase64Decoded);
        String inflatedXml = new String(decompressed, StandardCharsets.UTF_8);
        return inflatedXml;
    }

}

