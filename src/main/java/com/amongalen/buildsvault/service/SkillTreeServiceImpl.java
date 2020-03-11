package com.amongalen.buildsvault.service;

import com.amongalen.buildsvault.model.tree.DrawableTreeElementRepresentation;
import com.amongalen.buildsvault.model.tree.TreeNode;
import com.amongalen.buildsvault.model.tree.TreeNodeRepresentation;
import com.amongalen.buildsvault.model.tree.TreePathRepresentation;
import com.amongalen.buildsvault.util.SkillTreeData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class SkillTreeServiceImpl implements SkillTreeService {

    private final SkillTreeData skillTreeData;

    @Override
    public String getTreeRepresentation(List<TreeNode> takenNodes) {
        StringBuilder stringBuilder = new StringBuilder();

        Map<String, Double> treeDimensions = skillTreeData.getTreeDimensions();
        String svgStart = String.format("<svg style=\"background-color: transparent;\" viewBox=\"%s %s %s %s\">\n",
                treeDimensions.get("minX") - 100, treeDimensions.get("minY") - 100, treeDimensions.get("xSize") + 100, treeDimensions.get("ySize") + 100);

        stringBuilder.append(svgStart);


        List<TreePathRepresentation> paths = skillTreeData.getPathRepresentationsWithTakenNodes(takenNodes);
        List<DrawableTreeElementRepresentation> drawableTreeElements = new ArrayList<>(paths);
        Collection<TreeNodeRepresentation> nodes = skillTreeData.getNodeRepresentationsWithTakenNodes(takenNodes).values();
        drawableTreeElements.addAll(nodes);

        drawableTreeElements.sort(Comparator.comparing(DrawableTreeElementRepresentation::isTaken));

        drawableTreeElements.forEach(obj -> stringBuilder.append(obj.getSvgString()));

        stringBuilder.append("</svg>\n");
        return stringBuilder.toString();
    }
}
