package com.amongalen.buildsvault.model.tree;

public interface DrawableTreeElementRepresentation {

    boolean isTaken();

    String getSvgString();

    default String getColor() {
        String takenColor = "hsl(0, 100%, 50%)";
        String notTakenColor = "hsl(180, 100%, 50%)";
        return isTaken() ? takenColor : notTakenColor;
    }
}
