package com.amongalen.buildsvault.service;

import com.amongalen.buildsvault.model.tree.TreeNode;
import com.amongalen.buildsvault.model.tree.TreeNodeRepresentation;
import com.amongalen.buildsvault.model.tree.TreePathRepresentation;

import java.util.List;
import java.util.Map;

public interface SkillTreeService {
    Map<Integer, TreeNodeRepresentation> getTreeNodeRepresentations(List<TreeNode> takenNodes);

    List<TreePathRepresentation> getTreePathRepresentation(List<TreeNode> takenNodes);
}
