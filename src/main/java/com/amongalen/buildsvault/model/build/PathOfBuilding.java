package com.amongalen.buildsvault.model.build;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PathOfBuilding {
    @JsonProperty("Skills")
    private Skills skills;
    @JsonProperty("Tree")
    private Tree tree;
    @JsonProperty("Items")
    private Items items;
    @JsonProperty("Notes")
    private String notes;


}
