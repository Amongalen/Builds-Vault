package com.amongalen.buildsvault.model.build;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.Arrays;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Skills {
    @JsonProperty("Skill")
    private Skill[] skill;
    @JacksonXmlProperty(isAttribute = true)
    private String defaultGemLevel;
    @JacksonXmlProperty(isAttribute = true)
    private String defaultGemQuality;
    @JacksonXmlProperty(isAttribute = true)
    private String showSupportGemTypes;
    @JacksonXmlProperty(isAttribute = true)
    @JsonProperty("sortGemsByDPS")
    private String sortGemsByDps;


}