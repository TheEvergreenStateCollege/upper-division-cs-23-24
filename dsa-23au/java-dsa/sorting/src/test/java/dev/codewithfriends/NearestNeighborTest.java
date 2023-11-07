package dev.codewithfriends;

import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NearestNeighborTest {
    
    public static final int NEIGHBORHOOD_SIZE = 1_000_000;
    public static final int MAX_X = 1_000_000;
    public static final int MAX_Y = 1_000_000;


    private static Point[] neighborhood;
    private static Point target;

    @BeforeClass
    public static void setup() {
        neighborhood = new Point[NEIGHBORHOOD_SIZE];
        for (int i = 0; i < neighborhood.length; i += 1) {
            neighborhood[i] = Point.getRandomPoint();
        }
        target = Point.getRandomPoint();


    }

    @Test
    public void testBallTree() {
        System.out.println(String.format("Target point %s", target.toString()));

        Point closest = NearestNeighbor.findNearestNeighborBruteForce(neighborhood, target);

        System.out.println(
            String.format("Closest neighbor had distance of %f and was %s",
            target.distanceTo(closest), closest.toString())
        );
    }

    @Test
    public void testBruteForce() {
        System.out.println(String.format("Target point %s", target.toString()));

        Point closest = NearestNeighbor.findNearestNeighborBruteForce(neighborhood, target);

        System.out.println(String.format("Closest neighbor was %s", closest.toString()));
    }
}
