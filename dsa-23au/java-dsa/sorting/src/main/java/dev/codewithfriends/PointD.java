package dev.codewithfriends;
import java.util.Comparator;

public class PointD extends Point {
    public PointD(int[] x) {
        super(x);
    }
    public static class PointComparator implements Comparator<Point> {
        private int d;
        public PointComparator(int d) {
            this.d = d;
        }
        public int compare(Point p1, Point p2) {
            return  p1.x[d] - p2.x[d];
        }
    }
}
