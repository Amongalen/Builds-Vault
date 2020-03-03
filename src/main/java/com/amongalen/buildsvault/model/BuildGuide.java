package com.amongalen.buildsvault.model;

import com.amongalen.buildsvault.model.build.PathOfBuilding;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "BuildGuide")
public class BuildGuide {
    @Id
    private String id;

    private String name;
    private PathOfBuilding pathOfBuilding;
    private String text;

    public BuildGuide(String name, PathOfBuilding pathOfBuilding) {
        this.name = name;
        this.pathOfBuilding = pathOfBuilding;
    }

}
