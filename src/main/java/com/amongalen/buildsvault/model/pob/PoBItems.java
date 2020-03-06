package com.amongalen.buildsvault.model.pob;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoBItems {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Item")
    private PoBItem[] item;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Slot")
    private PoBSlot[] slot;

    @JsonProperty("ItemSet")
    private PoBItemSet itemSet;

    @JacksonXmlProperty(isAttribute = true)
    private String activeItemSet;

    @JacksonXmlProperty(isAttribute = true)
    private String useSecondWeaponSet;
}
