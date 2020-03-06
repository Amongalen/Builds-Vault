package com.amongalen.buildsvault.importer;

import com.amongalen.buildsvault.model.TreeNode;

import java.util.List;

public interface TreeImporter {
    List<TreeNode> importTreeKeystones(String treeUrl);
}
