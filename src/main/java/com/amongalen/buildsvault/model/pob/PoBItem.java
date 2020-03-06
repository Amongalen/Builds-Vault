package com.amongalen.buildsvault.model.pob;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoBItem {

    @JacksonXmlProperty(isAttribute = true)
    private String id;

    @JacksonXmlText
    private String content;

}