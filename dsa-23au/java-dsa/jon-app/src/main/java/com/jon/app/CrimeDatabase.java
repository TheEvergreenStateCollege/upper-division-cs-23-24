package com.jon.app;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

 

 
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CrimeDatabase {

    // Assume CrimeDatabase class has a method to add crime data
    public void addCrimeData(String[] header, String[] columns) {
        // Implement logic to add crime data to database
        Map<String, Object> row = new HashMap<>();

        for (int i = 0; i < header.length; i++) {
            //convert value to appropriate types according to data type in that column
            String columnName = header[i];
            String columnValue = columns[i];
        

        if (isInteger(columnValue)) {
            row.put(columnName, Integer.parseInt(columnValue));
        }
        else if (isFloat(columnValue)){
            row.put(columnName, Float.parseFloat(columnValue));
        }
        else { row.put(columnName, columnValue); } 
    }

    
    }
    private static boolean isFloat(String columnValue) {
        return false;
    }
    private static boolean isInteger(String columnValue) {
        return false;
    }

       
        // For example, you might have a Crime class with fields like date, county, crimeType, etc.
        // Add logic here...
    
    
    
    private static void displayDataForCountyAndYear(CrimeDatabase cdd, String county, int year) {
        // Implement logic to display data for the specified county and year
        List<CrimeData> crimeDataList = cdd.getCrimeDataList();
        // You can use the 'cdd' instance to access the CrimeDatabase
       
        System.out.println("Crime Data for " + county + " County in " + year + ":");

        //Iterate over the stored data and filter based on county an year
        for (CrimeData crimeData : crimeDataList) {
            if (crimeData.getCounty().equalsIgnoreCase(county) && getYearFromDate(crimeData.getDate()) == year) {
                //Display the relevant infromation for the matching county and year
                System.out.println("Date:" + crimeData.getDate());
                System.out.println("Crime Type: " + crimeData.getCrimeType());

                //Add more infromation as needed
                System.out.println("------------------------------"); //seperation
            }
        }

        
       
        // Add logic here...
    }


    private static int getYearFromDate(String date) {
        return 0;
    }
    private List<CrimeData> getCrimeDataList() {
        return null;
    }
    public static void main(String[] args) {
        CrimeDatabase cdd = new CrimeDatabase();
        InputStream is = CrimeDatabase.class.getClassLoader().getResourceAsStream("Crime.csv");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 0 || line.length() == 0) {
                    continue;
                }
                cdd.addCrimeData(tokens, tokens); // Add crime data to your database
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (args.length < 2) {
            System.err.println("Usage: java CrimeDatabase <county> <year>");
            return;
        }
        else {
            System.err.println("Enter Correct Format");
        }

        // Extract county and year from command-line arguments.
        String county = args[0];
        int year = Integer.parseInt(args[1]);

        // Display data for the specified county and year
        displayDataForCountyAndYear(cdd, county, year);
    }

}
