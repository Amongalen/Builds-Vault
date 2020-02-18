package com.amongalen.buildsvault.importer;

import com.amongalen.buildsvault.model.build.PathOfBuilding;
import com.amongalen.buildsvault.model.build.TreeNode;
import com.amongalen.buildsvault.util.CompressionUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;

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
public class ImporterImpl implements Importer {
    private static final Pattern TREE_LINK_PREFIX = Pattern.compile(".*passive-skill-tree/");

    @Override
    public PathOfBuilding importBuild(String pobLink) {
        PathOfBuilding pathOfBuilding = null;
        try {
            String xml = base64ToXml(pobLink);
            JacksonXmlModule xmlModule = new JacksonXmlModule();
            xmlModule.setDefaultUseWrapper(false);
            XmlMapper xmlMapper = new XmlMapper(xmlModule);
            pathOfBuilding = xmlMapper.readValue(Objects.requireNonNull(xml), PathOfBuilding.class);
        } catch (JsonProcessingException e) {
            log.error("Something went wrong while importing PoB build ", e);
        }
        return pathOfBuilding;
    }

    @Override
    public List<TreeNode> extractTreeNodes(String treeLink) {
        String raw = TREE_LINK_PREFIX.matcher(treeLink).replaceAll("");
        raw = "AAAABAMBABslS65tGdlb34rpAg==";
        String treeXml = base64ToXml2(raw);
        return Collections.emptyList();
    }

    static String base64ToXml2(String rawUrl) {
        try {
            String replaced = rawUrl.replace('-', '+').replace('_', '/');
            byte[] byteValuesBase64Decoded = Base64.getDecoder().decode(replaced);
            ByteBuffer byteBuffer = ByteBuffer.wrap(byteValuesBase64Decoded);
            int version = byteBuffer.getInt();
            byte charactersId = byteBuffer.get();
            byte ascendancyId = byteBuffer.get();
            byte isLocked = byteBuffer.get();
            CharBuffer charBuffer = byteBuffer.asCharBuffer();
            List<Integer> nodes = charBuffer.chars().boxed().collect(Collectors.toList());
            System.out.println(nodes);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    static String base64ToXml(String raw) {

        byte[] byteValueBase64Decoded;
        try {
            String replace = raw.replace('-', '+').replace('_', '/').trim();
            byteValueBase64Decoded = Base64.getDecoder().decode(replace);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
        String inflatedXml = "";
        try {
            //inflate
            byte[] decompressed = CompressionUtils.decompress(byteValueBase64Decoded);
            inflatedXml = new String(decompressed, StandardCharsets.UTF_8);
        } catch (IOException | DataFormatException e) {
            log.error("Couldn't inflate POB XML. raw: {}", raw);
        }
        return inflatedXml;
    }

}

