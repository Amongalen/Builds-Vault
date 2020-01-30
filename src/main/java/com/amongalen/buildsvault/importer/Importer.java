package com.amongalen.buildsvault.importer;

import com.amongalen.buildsvault.model.build.PathOfBuilding;

public interface Importer {
    public PathOfBuilding importBuild(String link);
}
