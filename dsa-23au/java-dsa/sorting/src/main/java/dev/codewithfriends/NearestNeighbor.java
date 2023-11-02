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
       int length = endCoord - startCoord + 1;

       if(allPoints.length <= 0){
        return null;
       }
       if(startCoord == endCoord){
        return allPoints[startCoord];
       }
       Point leftClosest = findNearestNeighborHelper(allPoints,startCoord,startCoord + (length/2),target);
       Point rightClosest = findNearestNeighborHelper(allPoints,startCoord + (length/2),endCoord + 1, target);
        
        if (leftClosest.distanceTo(target) < rightClosest.distanceTo(target)); {
            return leftClosest;
        } else {
            return rightClosest;
        }
        
    }


    public static Point findNearestNeighbor(Point[] allPoints, Point target) {

        // sort all the points in allPoints by their first coordinate
        // p.x[0]
        Point[] sorted = App.insertionSort(allPoints);

        // divide up array into halves and recurse
        
        return findNearestNeighborHelper(allPoints,0,allPoints.length-1,target);
    }

    public static final int NEIGHBORHOOD_SIZE = 8_350_000;
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
        Point closest = NearestNeighbor.findNearestNeighborBruteForce(neighborhood, target);
        long elapsed = System.currentTimeMillis() - now;
        System.out.printf("Elapsed time: %d seconds\n", Math.round(elapsed / 1000));

        System.out.println(String.format("Closest neighbor was %s", closest.toString()));

    }
    
}
