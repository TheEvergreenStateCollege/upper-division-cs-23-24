package dev.codewithfriends;

import java.util.Random;

public class NearestNeighbor {

    /**
     * Brute force approach to finding a nearest neighbor to the given target.
     * Running time O(N)
     * @param allPoints - all points in the neighborhood
     * @param target - our query point that we want the closest neighbor to
     * @return closest neighbor to target by Euclidean distance (using Point.distanceTo)
     */
    public static Point findNearestNeighborBruteForce(Point[] allPoints, Point target) {

        double closestDist = Double.MAX_VALUE;
        Point closestPoint = null;
        for (Point p : allPoints) {
            if (p.distanceTo(target) < closestDist) {
                closestDist = p.distanceTo(target);
                closestPoint = p;
            }
        }

        return closestPoint;
    }

    public static Point findNearestNeighborHelper(Point[] allPoints, int startCoord, int endCoord, Point target) {

        //base case
        if((endCoord - startCoord) <= 0)
        {
            return allPoints[startCoord];
        }
        else{
            int centerCoord = (startCoord + endCoord)/2;

            Point leftClosest = findNearestNeighborHelper(allPoints, startCoord, centerCoord, target);
            Point rightClosest = findNearestNeighborHelper(allPoints, centerCoord +1, endCoord, target);

           if (leftClosest.distanceTo(target) < rightClosest.distanceTo(target)){
            return leftClosest;
           }
           else
           {
            return rightClosest;
           }
        }
        
    }

    public static Point findNearestNeighbor(Point[] allPoints, Point target) {

        // sort all the points in allPoints by their first coordinate
        // p.x[0]
        // Point[] sorted = App.insertionSort(allPoints);

        BallTree bt = new BallTree(allPoints);
        return bt.findNearestNeighbor(target);

    }

    public static final int NEIGHBORHOOD_SIZE = 1_000_000;
    public static final int MAX_X = 1_000_000;
    public static final int MAX_Y = 1_000_000;

    public static Random rand = new Random();
    static Point[] neighborhood;
    static Point target;

    public static void main(String[] args) {

        neighborhood = new Point[NEIGHBORHOOD_SIZE];
        for (int i = 0; i < neighborhood.length; i += 1) {
            neighborhood[i] = Point.getRandomPoint();
        }
        target = Point.getRandomPoint();

        System.out.println(String.format("Target point %s", target.toString()));

        long now = System.currentTimeMillis();
        /*Point closest = NearestNeighbor.findNearestNeighborBruteForce(neighborhood, target);*/
        Point closest = NearestNeighbor.findNearestNeighbor(neighborhood, target);
        long elapsed = System.currentTimeMillis() - now;
        System.out.printf("Elapsed time: %d seconds\n", Math.round(elapsed / 1000));

        System.out.println(String.format("Closest neighbor was %s", closest.distanceTo(target)));

    }
    
}
