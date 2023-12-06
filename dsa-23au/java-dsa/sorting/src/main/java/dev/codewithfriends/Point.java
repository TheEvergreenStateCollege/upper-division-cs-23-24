package dev.codewithfriends;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.google.common.collect.Streams;
import com.google.common.primitives.Ints;

public class Point implements Comparable<Point> {
    
    public final static int DIMENSION = 100;
    public final static int MAX_X = 1_000_000;

    protected int[] x;
    public static Random rand = new Random();

    @Override
    public int compareTo(Point p2) {
        return p2.x[0] - this.x[0];
    }
    
    public static Point getRandomPoint() {
        int[] randomArray = IntStream.range(0, DIMENSION)
            .map(i -> rand.nextInt(MAX_X))
            .toArray();
        return new Point(randomArray);
    }

    public Point(int[] x) {
        this.x = x;
    }

    public int getX(int i) {
        return this.x[i];
    }

    public double distanceTo(Point p) {
        // Use Euclidean distance (L2-norm)
        return Streams.zip(
            Ints.asList(this.x).stream(),
            Ints.asList(p.x).stream(),
            (x1, x2) -> (x1 - x2) * (x1 - x2) // Square the difference
        )
        .mapToInt(Integer::intValue) // Convert to int
        .sum(); // Sum the squared differences                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
    }

    public String toString() {
        String concatenated = Ints.asList(this.x).stream()
            .map(i -> i.toString()+", ")
            .collect(Collectors.joining());
        return String.format("(%s)", concatenated);
    }

}
