package com.jon.app;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

 
public class CrimeDatabase {

    public static void main(String[] args) {
        CrimeDatabase cdd = new CrimeDatabase();
        InputStream is = ClassLoader.getSystemResourceAsStream("Crime.csv");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 0 || line.length() == 0) {
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (args.length < 2) {
            System.err.println("Usage: java CrimeDatabase <county> <year>");
            return;
        }

        // Extract county and year from command-line arguments. 
        String county = args[0];
        int year = Integer.parseInt(args[1]);   //FIXME??

        // Display data for the specified county and year
        displayDataForCountyAndYear(cdd, county, year);
    }

    private static void displayDataForCountyAndYear(CrimeDatabase cdd, String county, int year) { //FIXME
        // Implement your logic to display data for the specified county and year
        // You can use the 'cdd' instance to access the CrimeDatabase
        // Add your logic here...
    }
}
