package com.amongalen.buildsvault.model.pob;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoBTree {

    @JacksonXmlProperty(isAttribute = true)
    private String activeSpec;
    @JsonProperty("Spec")
    private PoBSpec spec;

}