package dev.codewithfriends;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class App {
    public static final float DARK_THRESHOLD = 0.3f; // out of 1.0f
    public static final float LIGHT_THRESHOLD = 0.7f; // out of 1.0f

    public static void main(String[] args) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(ClassLoader.getSystemResource("evergreen-campus.jpg"));
            List<VerticalRegion> vertRegions  = new LinkedList<>();
            processPixels(img, vertRegions);
            sortPixels(img);
            sortVerticalRegions(img, vertRegions);
            File output = new File("evergreen-campus-saved.png");
            ImageIO.write(img, "png", output);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    private static void processPixels(BufferedImage img, List<VerticalRegion> vertRegions) {
        VerticalRegion currentRegion = null;
        int count = 0;
        for (int x = 0; x < img.getWidth(); x += 1) {
            for (int y = 0; y < img.getHeight(); y += 1) {
                int pixelValue = img.getRGB(x, y);
                int red = (pixelValue >> 16) & 0xFF; // Red component
                int green = (pixelValue >> 8) & 0xFF;  // Green component
                int blue = pixelValue & 0xFF; // Blue component

                float[] hsb = Color.RGBtoHSB(red, green, blue, null);
                if (hsb[2] < DARK_THRESHOLD || hsb[2] > LIGHT_THRESHOLD) {
                    count += 1;
                    pixelValue = 0;
                    if (currentRegion != null) {
                        currentRegion.stopY = y;
                        vertRegions.add(currentRegion);
                        currentRegion = null;
                    }
                } else {
                    if (currentRegion == null) {
                        currentRegion = new VerticalRegion(x, y);
                    }
                }
                img.setRGB(x, y, pixelValue);
            }
        }
    }

    private static void sortVerticalRegions(BufferedImage img, List<VerticalRegion> vertRegions) {
        for (VerticalRegion region : vertRegions) {
            int[] col = new int[region.stopY - region.startY];
            for (int y = region.startY; y < region.stopY; y++) {
                col[y - region.startY] = img.getRGB(region.startX, y);
            }

            Arrays.sort(col, (rgb1, rgb2) -> {
                float brightness1 = getBrightness(rgb1);
                float brightness2 = getBrightness(rgb2);
                return Float.compare(brightness1, brightness2);
            });

            for (int y = region.startY; y < region.stopY; y++) {
                img.setRGB(region.startX, y, col[y - region.startY]);
            }
        }
    }

    private static void sortPixels(BufferedImage img) {
        for (int y = 0; y < img.getHeight(); y++) {
            int[] row = new int[img.getWidth()];
            for (int x = 0; x < img.getWidth(); x++) {
                row[x] = img.getRGB(x, y);
            }

            Arrays.sort(row, (rgb1, rgb2) -> {
                float brightness1 = getBrightness(rgb1);
                float brightness2 = getBrightness(rgb2);
                return Float.compare(brightness1, brightness2);
            });

            for (int x = 0; x < img.getWidth(); x++) {
                img.setRGB(x, y, row[x]);
            }
        }
    }

    private static float getBrightness(int rgb) {
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        return (r + g + b) / 3.0f / 255.0f; // Normalize to 0-1 range
    }
}

class VerticalRegion {
    int startX, startY, stopY;

    public VerticalRegion(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
        this.stopY = -1; 
    }
}
