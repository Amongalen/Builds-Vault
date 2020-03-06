package com.amongalen.buildsvault.importer;

import com.amongalen.buildsvault.model.pob.PoBPathOfBuilding;

public interface PoBImporter {
    PoBPathOfBuilding importBuild(String pobLink);
}
