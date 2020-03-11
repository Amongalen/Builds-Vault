package com.amongalen.buildsvault.model.tree;

public enum NodeSize {
    SMALL(28), MEDIUM(46), BIG(54);
    private final int radius;

    NodeSize(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }
}
