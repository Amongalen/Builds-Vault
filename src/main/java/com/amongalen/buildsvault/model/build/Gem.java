package com.amongalen.buildsvault.model.build;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gem {
    @JacksonXmlProperty(isAttribute = true)
    private String enableGlobal2;
    @JacksonXmlProperty(isAttribute = true)
    private String quality;
    @JacksonXmlProperty(isAttribute = true)
    private String level;
    @JacksonXmlProperty(isAttribute = true)
    private String gemId;
    @JacksonXmlProperty(isAttribute = true)
    private String skillId;
    @JacksonXmlProperty(isAttribute = true)
    private String enableGlobal1;
    @JacksonXmlProperty(isAttribute = true)
    private String enabled;
    @JacksonXmlProperty(isAttribute = true)
    private String nameSpec;


}