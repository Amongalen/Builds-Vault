package com.amongalen.buildsvault.model.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.util.Pair;

@Data
@AllArgsConstructor
public class TreeNodeRepresentation implements DrawableTreeElementRepresentation {
    final TreeNode node;
    final Pair<Double, Double> positionXY;
    final NodeSize size;
    final boolean isTaken;

    @Override
    public String getSvgString() {
        Double posX = positionXY.getFirst();
        Double posY = positionXY.getSecond();
        int radius = size.getRadius();
        String svgCircle = String.format("<circle cx=\"%s\" cy=\"%s\" r=\"%d\" fill=\"%s\"/>\n", posX, posY, radius, getColor());
        return svgCircle;
    }
}
