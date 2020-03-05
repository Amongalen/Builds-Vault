package com.amongalen.buildsvault.model.build;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @JacksonXmlProperty(isAttribute = true)
    private String id;

    @JacksonXmlText
    private String content;

    @JsonIgnore
    private ItemDetails itemDetails;

    public void setContent(String content) {
        itemDetails = new ItemDetails(content);
        log.error(itemDetails.toString());
        this.content = content;
    }
}