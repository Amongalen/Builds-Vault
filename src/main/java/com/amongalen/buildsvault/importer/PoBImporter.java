package com.amongalen.buildsvault.importer;

import com.amongalen.buildsvault.model.build.PathOfBuilding;
import com.amongalen.buildsvault.model.build.TreeNode;

import java.util.List;

public interface PoBImporter {
    public PathOfBuilding importBuild(String pobLink);
}
