package dev.codewithfriends;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class App
{
    public static final float DARK_THRESHOLD = 0.3f; // out of 1.0f
    public static final float LIGHT_THRESHOLD = 0.7f; // out of 1.0f

    private static void sortVerticalRegions(BufferedImage img, List<VerticalRegion> vertRegions) {
        for (int x = 0; x < img.getWidth(); x +=1 ) {
            for (int y = 0; y < img.getHeight(); y += 1) {

            }
        }
    }

    public static void main( String[] args )
    {
        BufferedImage img = null;

        try {
            img = ImageIO.read(ClassLoader.getSystemResource("evergreen-campus.jpg"));
            int[] pixels = new int[img.getWidth() * img.getHeight()];

            img.getRGB(0, 0, img.getWidth(), img.getHeight(), pixels, 0, 0);

            int[] modifiedPixels = new int[img.getWidth() * img.getHeight()];
            List<VerticalRegion> vertRegions  = new LinkedList<>();
            VerticalRegion currentRegion = null;
            // Do something to pixels here
            int count = 0;
            for (int x = 0; x < img.getWidth(); x += 1) {
                for (int y = 0; y < img.getHeight(); y += 1) {
                    //int pixelValue = pixels[x*img.getHeight() + y];
                    int pixelValue = img.getRGB(x, y);
                    int red = (pixelValue >> 16) & 0xFF; // Red component
                    int green = (pixelValue >> 8) & 0xFF;  // Green component
                    int blue = pixelValue & 0xFF; // Blue component


                    // Convert to HSL
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
                        // If we are returning to non-blacked-out pixels, start a new region
                        if (currentRegion == null) {
                            currentRegion = new VerticalRegion(x, y, -1);
                        }
                    }

                    modifiedPixels[x*img.getHeight() + y] = pixelValue;
                    img.setRGB(x,y,pixelValue);
                }
            }

            sortVerticalRegions(img, vertRegions);

            System.out.println(String.format("Zeroed out %d out of %d pixels", count, img.getWidth() * img.getHeight()));
            System.out.println(String.format("Found %d vertical regions", vertRegions.size()));
            //img.setRGB(0, 0, img.getWidth(), img.getHeight(), pixels, 0, 0);
            File output = new File("evergreen-campus-saved.png");
            ImageIO.write(img, "png", output);
        } catch (IOException e) {
            System.err.println(e.toString());
        }

        System.out.println( "Hello World!" );
    }
}
