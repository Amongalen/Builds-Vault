package com.amongalen.buildsvault.model.pob;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoBTreeView {

    @JacksonXmlProperty(isAttribute = true)
    String searchStr;
    @JacksonXmlProperty(isAttribute = true)
    String zoomY;
    @JacksonXmlProperty(isAttribute = true)
    String showHeatMap;
    @JacksonXmlProperty(isAttribute = true)
    String zoomLevel;
    @JacksonXmlProperty(isAttribute = true)
    String showStatDifferences;
    @JacksonXmlProperty(isAttribute = true)
    String zoomX;
}
