package dev.codewithfriends;

public class BallNode {

    /**
     * INVARIANT:
     * BallNodes are either leaves (this.leaf is non-null)
     * or pivot nodes (this.left and this.right are non-null);
     */
    
    private double pivot;
    private double radius;
    private int dimensionIndex;

    private BallNode left;
    private BallNode right;
    private Point leaf;

    /**
     * Construct a new non-leaf (pivot) BallNode
     * @param pivot - the pivot point on the given dimensionIndex that divides this ball into roughly left/right halves / sub-balls
     * @param radius - the radius along the dimension / feature /axis of points we include centered on pivot
     * @param dimensionIndex - the dimension index in Point of the feature / axis we are pivoting along
     */
    public BallNode(
        double pivot, double radius, int dimensionIndex,
        BallNode left, BallNode right
    ) {
        if (left == null) {
            throw new IllegalArgumentException("Left sub-ball cannot be null.");
        }
        if (right == null) {
            throw new IllegalArgumentException("right sub-ball cannot be null.");
        }
        this.pivot = pivot;
        this.radius = radius;
        this.dimensionIndex = dimensionIndex;
        this.left = left;
        this.right = right;
    }

    /**
     * Constructor for a new leaf BallNode
     * @param leaf - the single neighborhood point in this leaf ball.
     */
    public BallNode(Point leaf) {
        this.leaf = leaf;
    }

    public boolean isLeaf() {
        return (this.leaf != null);
    }

    public double getPivot() {
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

    public BallNode getRight() {
        return this.right;
    }

    public Point findNearestNeighbor(Point target) {
        if (isLeaf()) {
            return this.leaf;
        }
        if (target.x[this.dimensionIndex] <= this.pivot) {
            return this.left.findNearestNeighbor(target);
        } else {
            return this.right.findNearestNeighbor(target);
        }
    }

}
