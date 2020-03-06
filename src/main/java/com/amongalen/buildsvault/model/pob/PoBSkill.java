package com.amongalen.buildsvault.model.pob;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoBSkill {
    @JacksonXmlProperty(isAttribute = true)
    private String mainActiveSkillCalcs;
    @JacksonXmlProperty(isAttribute = true)
    private String enabled;
    @JacksonXmlProperty(isAttribute = true)
    private String slot;
    @JacksonXmlProperty(isAttribute = true)
    private String mainActiveSkill;
    @JsonProperty("Gem")
    private PoBGem[] gems;


}