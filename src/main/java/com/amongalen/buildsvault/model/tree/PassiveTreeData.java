package com.amongalen.buildsvault.model.tree;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PassiveTreeData {
    private Map<Integer, NodeGroup> groups;
    private Map<Integer, TreeNode> nodes;
    private List<Integer> skillsPerOrbit;
    private List<Integer> orbitRadii;

    @SuppressWarnings("unchecked")
    @JsonProperty("constants")
    private void unpackConstants(Map<String, Object> constants) {
        skillsPerOrbit = (List<Integer>) constants.get("skillsPerOrbit");
        orbitRadii = (List<Integer>) constants.get("orbitRadii");
    }
}
