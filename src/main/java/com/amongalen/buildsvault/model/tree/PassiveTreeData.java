package com.amongalen.buildsvault.model.tree;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PassiveTreeData {
    Map<Integer, NodeGroup> groups;
    Map<Integer, TreeNode> nodes;
}
