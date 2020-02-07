package com.amongalen.buildsvault.model.build;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Item")
    private Item[] item;
    @JsonProperty("ItemSet")
    private ItemSet itemSet;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Slot")
    private Slot[] slot;
    @JacksonXmlProperty(isAttribute = true)
    private String activeItemSet;
    @JacksonXmlProperty(isAttribute = true)
    private String useSecondWeaponSet;
}
