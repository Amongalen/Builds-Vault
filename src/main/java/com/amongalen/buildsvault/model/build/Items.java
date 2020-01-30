package com.amongalen.buildsvault.model.build;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;

import java.util.Arrays;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items
{
    @JsonProperty("Item")
    private Item[] item;
    @JsonProperty("ItemSet")
    private ItemSet itemSet;
    @JsonProperty("Slot")
    private Slot[] slot;
    private String activeItemSet;
    private String useSecondWeaponSet;


}
