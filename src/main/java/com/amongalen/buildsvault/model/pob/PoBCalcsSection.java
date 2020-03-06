package com.amongalen.buildsvault.model.pob;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoBCalcsSection {
    @JacksonXmlProperty(isAttribute = true)
    String collapsed;
    @JacksonXmlProperty(isAttribute = true)
    String id;
}
