package com.amongalen.buildsvault.service;

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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SkillTreeServiceImpl implements SkillTreeService {

    private final SkillTreeData skillTreeData;
    private static final String TAKEN_COLOR = "hsl(0, 100%, 50%)";
    private static final String NOT_TAKEN_COLOR = "hsl(180, 100%, 50%)";

    @Override
    public String getTreeRepresentation(List<TreeNode> takenNodes) {
        StringBuilder stringBuilder = new StringBuilder();

        Map<String, Double> treeDimensions = skillTreeData.getTreeDimensions();
        String svgStart = String.format("<svg style=\"background-color: transparent;\" viewBox=\"%s %s %s %s\">\n",
                treeDimensions.get("minX") - 100, treeDimensions.get("minY") - 100, treeDimensions.get("xSize") + 100, treeDimensions.get("ySize") + 100);

        stringBuilder.append(svgStart);

        Collection<TreeNodeRepresentation> nodes = skillTreeData.getNodeRepresentationsWithTakenNodes(takenNodes).values();
        List<TreeNodeRepresentation> notTakenNodeRepresentations = nodes.stream().filter(node -> !node.isTaken()).collect(Collectors.toList());
        String color = NOT_TAKEN_COLOR;
        for (TreeNodeRepresentation node : notTakenNodeRepresentations) {
            Double posX = node.getPositionXY().getFirst();
            Double posY = node.getPositionXY().getSecond();
            int radius = node.getSize().getRadius();
            String svgCircle = String.format("<circle cx=\"%s\" cy=\"%s\" r=\"%d\" fill=\"%s\"/>\n", posX, posY, radius, color);
            stringBuilder.append(svgCircle);
        }
        List<TreePathRepresentation> paths = skillTreeData.getPathRepresentationsWithTakenNodes(takenNodes);
        List<TreePathRepresentation> notTakenPathRepresentations = paths.stream().filter(path -> !path.isTaken()).collect(Collectors.toList());
        for (TreePathRepresentation path : notTakenPathRepresentations) {
            Double startPosX = path.getStartPosition().getFirst();
            Double startPosY = path.getStartPosition().getSecond();
            Double endPosX = path.getEndPosition().getFirst();
            Double endPosY = path.getEndPosition().getSecond();
            Integer radius = path.getRadius();
            if (path.isCurve()) {
                String svgPath = String.format("<path d=\"M %s %s A %d %d 0 0 0 %s %s\" fill=\"transparent\" stroke=\"%s\" stroke-width=\"24\"/>\n",
                        startPosX, startPosY, radius, radius, endPosX, endPosY, color);
                stringBuilder.append(svgPath);
            } else {
                String svgLine = String.format("<line fill=\"transparent\" stroke=\"%s\" stroke-width=\"24\" x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\"/>\n",
                        color, startPosX, startPosY, endPosX, endPosY);
                stringBuilder.append(svgLine);
            }
        }
        color = TAKEN_COLOR;
        List<TreeNodeRepresentation> takenNodesRepresentations = nodes.stream().filter(TreeNodeRepresentation::isTaken).collect(Collectors.toList());
        for (TreeNodeRepresentation node : takenNodesRepresentations) {
            Double posX = node.getPositionXY().getFirst();
            Double posY = node.getPositionXY().getSecond();
            int radius = node.getSize().getRadius();
            String svgCircle = String.format("<circle cx=\"%s\" cy=\"%s\" r=\"%d\" fill=\"%s\"/>\n", posX, posY, radius, color);
            stringBuilder.append(svgCircle);
        }

        List<TreePathRepresentation> takenPathRepresentations = paths.stream().filter(TreePathRepresentation::isTaken).collect(Collectors.toList());
        for (TreePathRepresentation path : takenPathRepresentations) {
            Double startPosX = path.getStartPosition().getFirst();
            Double startPosY = path.getStartPosition().getSecond();
            Double endPosX = path.getEndPosition().getFirst();
            Double endPosY = path.getEndPosition().getSecond();
            Integer radius = path.getRadius();
            if (path.isCurve()) {
                String svgPath = String.format("<path d=\"M %s %s A %d %d 0 0 0 %s %s\" fill=\"transparent\" stroke=\"%s\" stroke-width=\"24\"/>\n",
                        startPosX, startPosY, radius, radius, endPosX, endPosY, color);
                stringBuilder.append(svgPath);
            } else {
                String svgLine = String.format("<line fill=\"transparent\" stroke=\"%s\" stroke-width=\"24\" x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\"/>\n",
                        color, startPosX, startPosY, endPosX, endPosY);
                stringBuilder.append(svgLine);
            }
        }

        stringBuilder.append("</svg>\n");
        return stringBuilder.toString();
    }
}
