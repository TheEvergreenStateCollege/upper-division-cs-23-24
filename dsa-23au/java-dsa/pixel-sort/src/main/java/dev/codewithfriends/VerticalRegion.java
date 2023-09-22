package dev.codewithfriends;

import java.awt.image.BufferedImage;

class VerticalRegion {

    int x;
    int startY;
    int stopY;

    public VerticalRegion(int x, int startY, int stopY) {
        this.startY = startY;
        this.stopY = stopY;
        this.x = x;
    }

    public static void sortPixels(BufferedImage img, PixelSorter sorter) {

    }
}
