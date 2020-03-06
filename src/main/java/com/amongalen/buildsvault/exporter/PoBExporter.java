package com.amongalen.buildsvault.exporter;

import com.amongalen.buildsvault.model.pob.PoBPathOfBuilding;

public interface PoBExporter {
    String exportBuild(PoBPathOfBuilding build);
}
