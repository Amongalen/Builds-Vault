package com.amongalen.buildsvault.model.tree;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TreeNode {
    @JsonProperty("id")
    Integer id;
    @JsonProperty("dn")
    String name;
    @JsonProperty("ks")
    boolean isKeystone;
    @JsonProperty("m")
    boolean isMastery;
    @JsonProperty("not")
    boolean isNotable;
    @JsonProperty("o")
    int orbitRadii;
    @JsonProperty("oidx")
    int orbitIndex;
    @JsonProperty("out")
    int[] connectedNodes;


}
