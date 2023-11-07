package dev.codewithfriends;

public class BallTree {

    BallNode root;
    int[] spreads;
    int[] centers;

    /**
     * Statistics for a given point dimension in a neighborhood
     */
    private class DimensionStat {

        // These will always be non-negative, from Point coordinates
        int minSoFar;
        int maxSoFar;
        
        public DimensionStat() {
            this.minSoFar = Integer.MIN_VALUE;
            this.maxSoFar = Integer.MAX_VALUE;
        }

        public void updateMinSoFar(int minSoFar) {
            this.minSoFar = Math.min(minSoFar, this.minSoFar);
        }

        public void updateMaxSoFar(int maxSoFar) {
            this.maxSoFar = Math.max(maxSoFar, this.maxSoFar);
        }

        public int getSpread() {
            return this.maxSoFar - this.minSoFar;
        }

    }

    public BallTree(Point[] allPoints) {
        root = buildBallTree(allPoints, 0, allPoints.length-1);
    }

    public static BallNode buildBallTree(Point[] allPoints, int start, int end) {

        // Base case
        if (start == end) {
            return new BallNode(allPoints[start]);
        }

        // Inductive case
        int biggestSpreadSoFar = Integer.MIN_VALUE;
        int mostSpreadDimSoFar = -1;
        DimensionStat[] dimStats = new DimensionStat[Point.DIMENSION];

        // First pass:
        // Updating of dimension stats in O(nd) time
        // https://en.wikipedia.org/wiki/Ball_tree#Pseudocode
        // Scan points to find most spread dimension so far
        for (int i = 0; i < allPoints.length; i += 1) {
            for (int j = 0; j < Point.DIMENSION; j += 1) {
                // Update the minSoFar for dimension j on point i
                dimStats[j].updateMinSoFar(allPoints[i].x[j]);
                dimStats[j].updateMaxSoFar(allPoints[i].x[j]);
                if (dimStats[j].getSpread() > biggestSpreadSoFar) {
                    mostSpreadDimSoFar = j;
                }
            }
        }
        // At this point,
        // - biggestSpreadSoFar contains the biggest spread between
        //   the max and min of any coordinate
        // - mostSpreadDimSoFar contains the corresponding dimension / axis
        //   which has this biggest spread
        DimensionStat pivotStat = dimStats[mostSpreadDimSoFar];
        double radius = biggestSpreadSoFar / 2.0;
        double pivot = (pivotStat.minSoFar + pivotStat.maxSoFar) / 2;

        // Partition points into left and right half
        int leftRef = start;
        int rightRef = end;
        while (leftRef < rightRef) {
            // how do we partition in-place? 
            // we can't predict where in the middle we will meet,
            // so we have to start at far left and right ends
            // leftRef advances to the right,
            // rightRef advances to the left

            // Advance leftRef until we reach one that is greater than
            // or equal to the pivot and meant for the right half
            while ((allPoints[leftRef].x[mostSpreadDimSoFar] < pivot) &&
                   (leftRef < rightRef)) {
                leftRef += 1;
            }

            // Advance rightRef until we reach one that is less than
            // the pivot and meant for the right half
            while ((allPoints[rightRef].x[mostSpreadDimSoFar] >= pivot) &&
                   (leftRef < rightRef)) {
                rightRef -= 1;
            }

            // When we reach this point, if we haven't met in the middle
            // yet of our partition, we have an inversion and swap
            if (leftRef < rightRef) {
                Point temp = allPoints[leftRef];
                allPoints[leftRef] = allPoints[rightRef];
                allPoints[rightRef] = temp;
            }
        }
        // At this point, leftRef == rightRef, and we should be roughly balanced
        BallNode left = buildBallTree(allPoints, start, leftRef);
        BallNode right = buildBallTree(allPoints, leftRef+1, end);

        return new BallNode(pivot, radius, mostSpreadDimSoFar, left, right);
    }

    public Point findNearestNeighbor(Point target) {
        if (root != null) {
            return root.findNearestNeighbor(target);
        }
        return null;
    }
}
