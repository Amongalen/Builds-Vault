package com.amongalen.buildsvault.importer;

import com.amongalen.buildsvault.model.build.PathOfBuilding;

public interface PoBImporter {
    public PathOfBuilding importBuild(String pobLink);
}
