package com.amongalen.buildsvault.model.tree;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.util.Pair;

@Data
@Builder
public class TreePathRepresentation implements DrawableTreeElementRepresentation {
    final TreeNode startNode;
    final TreeNode endNode;
    final Pair<Double, Double> startPosition;
    final Pair<Double, Double> endPosition;
    final boolean isCurve;
    final Integer radius;
    final boolean isTaken;

    @Override
    public String getSvgString() {
        Double startPosX = startPosition.getFirst();
        Double startPosY = startPosition.getSecond();
        Double endPosX = endPosition.getFirst();
        Double endPosY = endPosition.getSecond();
        String result;
        if (isCurve) {
            result = String.format("<path d=\"M %s %s A %d %d 0 0 0 %s %s\" fill=\"transparent\" stroke=\"%s\" stroke-width=\"24\"/>\n",
                    startPosX, startPosY, radius, radius, endPosX, endPosY, getColor());
        } else {
            result = String.format("<line fill=\"transparent\" stroke=\"%s\" stroke-width=\"24\" x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\"/>\n",
                    getColor(), startPosX, startPosY, endPosX, endPosY);
        }

        return result;
    }
}
