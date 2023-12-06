package com.pswishcorp.app;

public class BallNode {
    
    private int pivot;
    private double radius;
    private int dimensionIndex;

    private BallNode left;
    private BallNode right;
    private Point leaf;

    /**
     * Construct a new BallNode
     * @param pivot - the pivot point on the given dimensionIndex that divides this ball into roughly left/right halves / sub-balls
     * @param radius - the radius along the dimension / feature /axis of points we include centered on pivot
     * @param dimensionIndex - the dimension index in Point of the feature / axis we are pivoting along
     * @param leaf - the single Point for this ballnode if it's a leaf (base case)
     */
    public BallNode(
        int pivot, double radius, int dimensionIndex, Point leaf
    ) {
        this.pivot = pivot;
        this.radius = radius;
        this.dimensionIndex = dimensionIndex;
        this.leaf = leaf;
        this.left = null;
        this.right = null;
    }

    public int getPivot() {
        return this.pivot;
    }

    public double getRadius() {
        return this.radius;
    }

    public int getDimensionIndex() {
        return this.dimensionIndex;
    }

    public Point getLeaf() {
        return this.leaf;
    }

    public BallNode getLeft() {
        return this.left;
    }

    public void setLeft(BallNode left) {
        this.left = left;
    }

    public BallNode getRight() {
        return this.right;
    }

    public void setRight(BallNode right) {
        this.right = right;
    }

}