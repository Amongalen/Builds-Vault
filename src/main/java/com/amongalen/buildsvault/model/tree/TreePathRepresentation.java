package com.amongalen.buildsvault.model.tree;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.util.Pair;

@Data
@Builder
public class TreePathRepresentation {
    final int startId;
    final int endId;
    final Pair<Double, Double> startPosition;
    final Pair<Double, Double> endPosition;
    final boolean isCurve;
    final Integer radius;
    final boolean isTaken;
}
