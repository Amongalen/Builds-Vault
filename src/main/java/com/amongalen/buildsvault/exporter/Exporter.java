package com.amongalen.buildsvault.exporter;

import com.amongalen.buildsvault.model.build.PathOfBuilding;

public interface Exporter {
    public String exportBuild(PathOfBuilding build);
}
