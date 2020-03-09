package com.amongalen.buildsvault.importer;

import com.amongalen.buildsvault.model.tree.TreeNode;

import java.util.List;

public interface TreeImporter {
    List<TreeNode> importTreeNodes(String treeUrl);
}
