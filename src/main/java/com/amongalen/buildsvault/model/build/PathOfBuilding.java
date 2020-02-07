package com.amongalen.buildsvault.model.build;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PathOfBuilding {

    @JsonProperty("Build")
    private Build build;

    @JsonProperty("Calcs")
    private Calcs calcs;

    @JsonProperty("Skills")
    private Skills skills;

    @JsonProperty("Tree")
    private Tree tree;

    @JsonProperty("Notes")
    private String notes;

    @JsonProperty("TreeView")
    private TreeView treeView;

    @JsonProperty("Items")
    private Items items;

    @JacksonXmlElementWrapper(localName = "Config")
    @JsonProperty("Input")
    private ConfigInput[] configInputs;
}
