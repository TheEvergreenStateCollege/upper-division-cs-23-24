package dev.codewithfriends;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class ReadArray 
{
    public static void main( String[] args )
    {
        try (FileInputStream fis = new FileInputStream("GhostTowns.csv")) {
            BufferedInputStream bis = new BufferedInputStream(fis);
            byte[] buffer = bis.readAllBytes();
            String inputString = new String(buffer);
            String[] lines = inputString.split("\n");
            for (String line : lines) {
                String[] tokens = line.split(",");
                if (tokens.length == 0 || line.length() == 0) {
                    continue;
                }
            }
        } catch(IOException ioe) {
            System.err.println(ioe.toString());
        }


        System.out.println( "Hello World!" );

    }
}
