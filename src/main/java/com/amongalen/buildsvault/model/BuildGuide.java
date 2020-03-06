package com.amongalen.buildsvault.model;

import com.amongalen.buildsvault.model.pob.PoBPathOfBuilding;
import com.amongalen.buildsvault.model.tree.TreeNode;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "BuildGuide")
public class BuildGuide {
    @Id
    private String id;

    private String name;
    private PoBPathOfBuilding pathOfBuilding;
    private String text;
    private List<TreeNode> keystones;

    public BuildGuide(String name, PoBPathOfBuilding pathOfBuilding) {
        this.name = name;
        this.pathOfBuilding = pathOfBuilding;

    }

}
