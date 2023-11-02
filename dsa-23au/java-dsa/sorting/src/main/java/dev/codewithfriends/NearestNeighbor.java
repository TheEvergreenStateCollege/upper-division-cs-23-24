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
    /*
     *  /\ __ /\
     *   0    0
     *  '' ^ '' 
     *   {      }   
     */

    public static Point findNearestNeighborHelper(Point[] allPoints, int startCoord, int endCoord, Point target) {
        //code recursively! this will go through and divide and conquer the different distances
        //so we will comparing the relationships between different coordinate
        //points
        //base case:

        //this is logn
 
        if((endCoord - startCoord) <= 0)
        {
            //when it gets to the base case
            //the start coord is the closest coord
            //which it then returns
            return allPoints[startCoord];

        }
        //we divide allpoints in half repeatedly
        //and when we merge we make the first index into the one that
        //is closer to the target
        //
        else
        {
            //issues!!!!
            int centerCoord = (startCoord + endCoord);
           Point leftClosest = findNearestNeighborHelper(allPoints, startCoord, centerCoord, target);
           Point rightClosest = findNearestNeighborHelper(allPoints,centerCoord - 1, endCoord, target);

           //merge two results into one

          if (leftClosest.distanceTo(target) < rightClosest.distanceTo(target))
          {
            return leftClosest;
          }
          else
          {
            return rightClosest;
          }
        }
    }
//meow
    public static Point findNearestNeighbor(Point[] allPoints, Point target) {

        Point closest;
        // sort all the points in allPoints by their first coordinate
        // p.x[0]
        Point[] sorted = App.insertionSort(allPoints);

        findNearestNeighborHelper(allPoints, 0, allPoints.length, target);

        return closest;

    

   
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
