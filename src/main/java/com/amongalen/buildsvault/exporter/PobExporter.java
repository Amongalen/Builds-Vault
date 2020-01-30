package com.amongalen.buildsvault.exporter;

import com.amongalen.buildsvault.model.build.PathOfBuilding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
public class PobExporter implements Exporter {

    @Override
    public String exportBuild(PathOfBuilding build) {
//        try {
//            String replace = input.replace('-', '+').replace('_', '/').trim();
//            byte[] bytes = replace.getBytes(StandardCharsets.UTF_8);
//            byte[] byteValueBase64Decoded = Base64.getDecoder().decode(bytes);
//            byte[] decompressed = CompressionUtils.decompress(byteValueBase64Decoded);
//            byte[] compress = CompressionUtils.compress(decompressed);
//
//            byte[] byteValueBase64encoded = Base64.getEncoder().encode(compress);
//            String encodedString = new String(byteValueBase64encoded, StandardCharsets.UTF_8);
//            String result = encodedString.replace('+', '-').replace('/', '_');
//
//        } catch (IOException | DataFormatException e) {
//            e.printStackTrace();
//        }

        JacksonXmlModule xmlModule = new JacksonXmlModule();
        xmlModule.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(xmlModule);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        String pobString = null;
        try {
            String xml = xmlMapper.writeValueAsString(build);
            log.info("xml: {}", xml);
            pobString = convertToDeflatedBase64(xml);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return pobString;
    }

    static String convertToDeflatedBase64(String xml) {
        byte[] bytes = xml.getBytes(StandardCharsets.UTF_8);
        String result = null;
        try {
            byte[] deflated = CompressionUtils.compress(bytes);
            byte[] encoded = Base64.getEncoder().encode(deflated);
            String encodedString = new String(encoded, StandardCharsets.UTF_8);
            result = encodedString.replace('+', '-').replace('/', '_');
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(result);
        return result;
    }
}
