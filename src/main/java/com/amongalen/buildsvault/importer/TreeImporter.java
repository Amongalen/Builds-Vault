package com.amongalen.buildsvault.importer;

import com.amongalen.buildsvault.model.build.TreeNode;

import java.util.List;

public interface TreeImporter {
    public List<TreeNode> importTreeKeystones(String treeUrl);
}
