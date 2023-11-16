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

 
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrimeDatabase {

    // Assume CrimeDatabase class has a method to add crime data
    public void addCrimeData(String[] tokens) {
        // Implement logic to add crime data to database
        // may need to have a data structure to store the crime data
        // For example, you might have a Crime class with fields like date, county, crimeType, etc.
        // Add logic here...
    }

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
                cdd.addCrimeData(tokens); // Add crime data to your database
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
        int year = Integer.parseInt(args[1]);

        // Display data for the specified county and year
        displayDataForCountyAndYear(cdd, county, year);
    }

    private static void displayDataForCountyAndYear(CrimeDatabase cdd, String county, int year) {
        // Implement logic to display data for the specified county and year
        // You can use the 'cdd' instance to access the CrimeDatabase
        // For example, might iterate over the stored crime data and filter based on county and year
        // Add logic here...
    }
}
