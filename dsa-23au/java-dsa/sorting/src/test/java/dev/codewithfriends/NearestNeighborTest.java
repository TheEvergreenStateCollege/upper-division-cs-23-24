package dev.codewithfriends;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class NearestNeighborTest {
    
    public static final int NEIGHBORHOOD_SIZE = 1_000_000;
    public static final int MAX_X = 1_000_000;
    public static final int MAX_Y = 1_000_000;

    public static Random rand = new Random();
    
    public static Point getRandomPoint() {
        int x = rand.nextInt(MAX_X);
        int y = rand.nextInt(MAX_Y);
        return new Point(x, y);
    }

    Point[] neighborhood;
    Point target;

    @Before
    public void setup() {
        neighborhood = new Point[NEIGHBORHOOD_SIZE];
        for (int i = 0; i < neighborhood.length; i += 1) {
            neighborhood[i] = getRandomPoint();
        }
        target = getRandomPoint();


    }

    @Test
    public void testBruteForce() {
        System.out.println(String.format("Target point %s", target.toString()));

        Point closest = NearestNeighbor.findNearestNeighborBruteForce(neighborhood, target);

        System.out.println(String.format("Closest neighbor was %s", closest.toString()));

    }
}
