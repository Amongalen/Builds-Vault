package com.amongalen.buildsvault.model.build;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Build {
    @JsonProperty("PlayerStat")
    PlayerStat[] playerStats;

    @JacksonXmlProperty(isAttribute = true)
    String level;
    @JacksonXmlProperty(isAttribute = true)
    String targetVersion;
    @JacksonXmlProperty(isAttribute = true)
    String bandit;
    @JacksonXmlProperty(isAttribute = true)
    String banditNormal;
    @JacksonXmlProperty(isAttribute = true)
    String banditCruel;
    @JacksonXmlProperty(isAttribute = true)
    String banditMerciless;
    @JacksonXmlProperty(isAttribute = true)
    String className;
    @JacksonXmlProperty(isAttribute = true)
    String ascendClassName;
    @JacksonXmlProperty(isAttribute = true)
    String mainSocketGroup;
    @JacksonXmlProperty(isAttribute = true)
    String pantheonMinorGod;
    @JacksonXmlProperty(isAttribute = true)
    String pantheonMajorGod;
    @JacksonXmlProperty(isAttribute = true)
    String viewMode;
}
