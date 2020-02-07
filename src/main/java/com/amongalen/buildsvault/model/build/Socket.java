package com.amongalen.buildsvault.model.build;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Socket {

    @JacksonXmlProperty(isAttribute = true)
    private String nodeId;

    @JacksonXmlProperty(isAttribute = true)
    private String itemId;

}