package com.amongalen.buildsvault.model.pob;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoBSpec {

    @JacksonXmlProperty(isAttribute = true)
    private String treeVersion;

    @JsonProperty("URL")
    private String url;

    @JsonProperty("Sockets")
    @JacksonXmlElementWrapper(localName = "Sockets")
    private PoBSocket[] socket;

    public String getTrimmedUrl() {
        return url.trim();
    }
}