package com.amongalen.buildsvault.exporter;

import com.amongalen.buildsvault.model.build.PathOfBuilding;

public interface PoBExporter {
    public String exportBuild(PathOfBuilding build);
}
