package com.amongalen.buildsvault.model.tree;

import lombok.Data;
import org.springframework.data.util.Pair;

@Data
public class TreePathRepresentation {
    Pair<Double, Double> startPosition;
    Pair<Double, Double> endPosition;
    boolean isCurve;
    Integer radius;
}
