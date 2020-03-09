package com.amongalen.buildsvault.model.tree;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeGroup {
    @JsonProperty("oo")
    Map<String, Boolean> orbitals;
    @JsonProperty("n")
    List<Integer> nodeIds;
    float x;
    float y;
}
