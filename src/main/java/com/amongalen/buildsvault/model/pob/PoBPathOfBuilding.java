package com.amongalen.buildsvault.model.pob;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoBPathOfBuilding {
    @JsonIgnore


    @JsonProperty("Build")
    private PoBBuild build;

    @JsonProperty("Calcs")
    private PoBCalcs calcs;

    @JsonProperty("Skills")
    private PoBSkills skills;

    @JsonProperty("Tree")
    private PoBTree tree;

    @JsonProperty("Notes")
    private String notes;

    @JsonProperty("TreeView")
    private PoBTreeView treeView;

    @JsonProperty("Items")
    private PoBItems items;

    @JacksonXmlElementWrapper(localName = "Config")
    @JsonProperty("Input")
    private PoBConfigInput[] configInputs;
}
