package com.amongalen.buildsvault.model.build;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.Arrays;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Skill {
    @JacksonXmlProperty(isAttribute = true)
    private String mainActiveSkill;
    @JacksonXmlProperty(isAttribute = true)
    private String mainActiveSkillCalcs;
    @JacksonXmlProperty(isAttribute = true)
    private String slot;
    @JacksonXmlProperty(isAttribute = true)
    private String enabled;
    @JsonProperty("Gem")
    private Gem[] gems;


}