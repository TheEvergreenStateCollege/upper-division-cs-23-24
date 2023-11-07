package com.faulkdf.app;

import java.util.List;

public class BallTreeNode {
    Point center;
    double radius;
    BallTreeNode left;
    BallTreeNode right;

    public BallTreeNode(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}

