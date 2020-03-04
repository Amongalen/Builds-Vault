package com.amongalen.buildsvault.model.build;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;
import org.thymeleaf.util.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @JacksonXmlProperty(isAttribute = true)
    private String id;

    @JacksonXmlText
    private String content;

    public String printItem() {
        String escapedContent = StringUtils.escapeXml(content);
        String[] lines = escapedContent.split("\n");
        String result = Arrays.stream(lines).map(Item::parseLine).collect(Collectors.joining("\n"));
        return result;
    }

    private static String parseLine(String line) {
        StringBuilder builder = new StringBuilder(line.trim());
        return builder.toString();
    }
}