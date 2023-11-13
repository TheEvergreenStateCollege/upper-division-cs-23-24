package com.juniperGMVAD.app;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class ReadData
{

    
    public static void main( String[] args )
    {
        try (FileInputStream fis = new FileInputStream("G20 Shifting Globalization - Sheet1(1).csv")) {
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
