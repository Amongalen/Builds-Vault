package com.amongalen.buildsvault;

import com.amongalen.buildsvault.exporter.Exporter;
import com.amongalen.buildsvault.exporter.PobExporter;
import com.amongalen.buildsvault.importer.Importer;
import com.amongalen.buildsvault.importer.PobImporter;
import com.amongalen.buildsvault.model.build.PathOfBuilding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BuildsvaultApplication {

    public static void main(String[] args) {
        Importer pobImporter = new PobImporter();
        PathOfBuilding build = pobImporter.importBuild("test");
        Exporter pobExporter = new PobExporter();
        String pobString = pobExporter.exportBuild(build);
        SpringApplication.run(BuildsvaultApplication.class, args);
    }

}
