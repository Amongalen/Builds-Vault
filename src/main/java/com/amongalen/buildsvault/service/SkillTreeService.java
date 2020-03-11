package com.amongalen.buildsvault.service;

import com.amongalen.buildsvault.model.tree.TreeNode;

import java.util.List;

public interface SkillTreeService {
    String getTreeRepresentation(List<TreeNode> takenNodes);
}
