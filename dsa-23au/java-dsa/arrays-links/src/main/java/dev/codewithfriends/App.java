package dev.codewithfriends;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BufferedInputStream bis = new BufferedInputStream(System.in);
        try {
            byte[] buffer = bis.readAllBytes();
            String inputString = new String(buffer);
            String[] lines = inputString.split("\n");
            for (String line : lines) {
                String[] tokens = line.split(",");
                Book book = new Book(tokens[0], tokens[1]);
            }
        } catch(IOException ioe) {

        }


        System.out.println( "Hello World!" )

    }
}
