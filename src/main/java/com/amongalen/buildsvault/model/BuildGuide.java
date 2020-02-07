package com.amongalen.buildsvault.model;

import com.amongalen.buildsvault.model.build.PathOfBuilding;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class BuildGuide {
    @Id
    private String id;
    private PathOfBuilding pathOfBuilding;
}
