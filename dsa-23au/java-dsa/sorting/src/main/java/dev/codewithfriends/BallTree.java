package dev.codewithfriends;

public class BallTree {
    
    public static class BallNode {

        private int pivot;
        private int radius;

        public BallNode(int pivot, int radius) {
            this.pivot = pivot;
            this.radius = radius;
        }
    }

    BallNode root;
    int[] spreads;
    int[] centers;

    public BallTree(Point[] allPoints) {
        spreads = new int[Point.DIMENSION];
        root = buildBallTree(allPoints);
    }

    public static BallTree buildBallTree(Point[] allPoints) {
        for (int i = 0; i < spreads.length; i += 1) {
            spreads[i] = 0;
        }
        for (Point p : allPoints) {
            
        }
    }
}
