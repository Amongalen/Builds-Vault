package com.amongalen.buildsvault.model;

import com.amongalen.buildsvault.model.pob.PoBPathOfBuilding;
import com.amongalen.buildsvault.model.tree.TreeNode;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Document(collection = "BuildGuide")
public class BuildGuide {
    @Id
    private String id;

    private String name;
    private PoBPathOfBuilding pathOfBuilding;
    private String text;
    private List<TreeNode> treeNodes;

    public BuildGuide(String name, PoBPathOfBuilding pathOfBuilding) {
        this.name = name;
        this.pathOfBuilding = pathOfBuilding;

    }

    public List<TreeNode> getKeystones() {
        return treeNodes.stream().filter(TreeNode::isKeystone).collect(Collectors.toList());
    }

}
