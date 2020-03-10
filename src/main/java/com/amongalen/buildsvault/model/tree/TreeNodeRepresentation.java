package com.amongalen.buildsvault.model.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

@Data
@AllArgsConstructor
public class TreeNodeRepresentation {
    final Integer id;
    final Pair<Double, Double> positionXY;
    final NodeSize size;
    final boolean isTaken;
}
