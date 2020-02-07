package com.amongalen.buildsvault.importer;

import com.amongalen.buildsvault.exporter.CompressionUtils;
import com.amongalen.buildsvault.model.build.PathOfBuilding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@Slf4j
public class PobImporter implements Importer {

    @Override
    public PathOfBuilding importBuild(String link) {
        PathOfBuilding pathOfBuilding = null;
        try {
            String xml = extractFromPobPastebin(link);
            JacksonXmlModule xmlModule = new JacksonXmlModule();
            xmlModule.setDefaultUseWrapper(false);
            XmlMapper xmlMapper = new XmlMapper(xmlModule);
            pathOfBuilding = xmlMapper.readValue(Objects.requireNonNull(xml), PathOfBuilding.class);
        } catch (JsonProcessingException e) {
            log.error("Something went wrong while importing PoB build ", e);
        }
        return pathOfBuilding;
    }

    static String extractFromPobPastebin(String raw) {

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

