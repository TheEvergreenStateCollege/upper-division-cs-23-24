package dev.codewithfriends;

public class BallTree {

    BallNode root;

    /**
     * Statistics for a given point dimension in a neighborhood
     */
    private class DimensionStat {

        int minSoFar;
        int maxSoFar;

        public DimensionStat() {
            this.minSoFar = Integer.MAX_VALUE;
            this.maxSoFar = Integer.MIN_VALUE;
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
        root = buildBallTree(allPoints, 0, allPoints.length - 1);
    }

    public BallNode buildBallTree(Point[] allPoints, int start, int end) {
        if (start == end) {
            return new BallNode(allPoints[start]);
        }

        int biggestSpreadSoFar = Integer.MIN_VALUE;
        int mostSpreadDimSoFar = -1;
        DimensionStat[] dimStats = new DimensionStat[Point.DIMENSION];
        for (int i = 0; i < Point.DIMENSION; i++) {
            dimStats[i] = new DimensionStat();
        }

        for (int i = start; i <= end; i++) {
            for (int j = 0; j < Point.DIMENSION; j++) {
                dimStats[j].updateMinSoFar(allPoints[i].x[j]);
                dimStats[j].updateMaxSoFar(allPoints[i].x[j]);
                if (dimStats[j].getSpread() > biggestSpreadSoFar) {
                    biggestSpreadSoFar = dimStats[j].getSpread();
                    mostSpreadDimSoFar = j;
                }
            }
        }

        DimensionStat pivotStat = dimStats[mostSpreadDimSoFar];
        double radius = biggestSpreadSoFar / 2.0;
        double pivot = (pivotStat.minSoFar + pivotStat.maxSoFar) / 2.0;

        int leftRef = start;
        int rightRef = end;
        while (leftRef < rightRef) {
            while (leftRef <= rightRef && allPoints[leftRef].x[mostSpreadDimSoFar] < pivot) {
                leftRef += 1;
            }

            while (leftRef <= rightRef && allPoints[rightRef].x[mostSpreadDimSoFar] >= pivot) {
                rightRef -= 1;
            }

            if (leftRef < rightRef) {
                Point temp = allPoints[leftRef];
                allPoints[leftRef] = allPoints[rightRef];
                allPoints[rightRef] = temp;
            }
        }

        BallNode left = buildBallTree(allPoints, start, leftRef - 1);
        BallNode right = buildBallTree(allPoints, leftRef, end);

        return new BallNode(pivot, radius, mostSpreadDimSoFar, left, right);
    }

    public Point findNearestNeighbor(Point target) {
        if (root != null) {
            return root.findNearestNeighbor(target);
        }
        return null;
    }
}
