package com.amongalen.buildsvault.model.pob;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoBSkills {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Skill")
    private PoBSkill[] skill;
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