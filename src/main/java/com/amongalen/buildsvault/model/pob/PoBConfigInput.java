package com.amongalen.buildsvault.model.pob;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoBConfigInput {
    @JacksonXmlProperty(isAttribute = true)
    String name;

    @JsonProperty("boolean")
    @JacksonXmlProperty(isAttribute = true)
    String bool;
    @JacksonXmlProperty(isAttribute = true)
    String string;
}
