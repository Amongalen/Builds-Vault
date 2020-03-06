package com.amongalen.buildsvault.exporter;

import com.amongalen.buildsvault.model.pob.PoBPathOfBuilding;
import com.amongalen.buildsvault.util.CompressionUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Service
public class PoBExporterImpl implements PoBExporter {

    @Override
    public String exportBuild(PoBPathOfBuilding build) {
        JacksonXmlModule xmlModule = new JacksonXmlModule();
        xmlModule.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(xmlModule);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        String pobString = null;
        try {
            String xml = xmlMapper.writeValueAsString(build);
            pobString = xmlToBase64(xml);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return pobString;
    }

    static String xmlToBase64(String xml) {
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
