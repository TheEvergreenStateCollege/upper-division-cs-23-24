package com.harlee.app;
import java.util.List;

public class CSVDataAnalyzer {

    //How many meteors fell in the given year?
    public static int countMeteorsInYear(List<String[]> parsedData, int targetYear) {
        int meteorCount = 0;
        
        int yearColumnIndex = 6; 

        for (String[] row : parsedData) {
            try {
                int year = Integer.parseInt(row[yearColumnIndex]);

                if (year == targetYear) {
                    meteorCount++;
                }
            } catch (NumberFormatException e) {
                // Invalid year
                System.out.println("Invalid year format in the CSV data.");
            }
        }
        return meteorCount;
    }

    /**public static int meteorsCountry(List<String[]> parsedData, String targetCountry) {
        
        int meteorCountryCount = 0;
        
        int countryColumnIndex = 0;
    
        for (String[] row : parsedData) {
            try {
                String country = row[countryColumnIndex];
    
                if (country.equals(targetCountry)) {
                    meteorCountryCount++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid format in CSV data: Index out of bounds.");
            }
        }
        return meteorCountryCount;
    } **/

    //Print All Names of Meteors
    public static void printNames(List<String[]> parsedData) {
        
        int columnIndex = 0;

        // Print names column values to the terminal
        for (String[] row : parsedData) {
            if (row.length > columnIndex) {
                System.out.println(row[columnIndex]);
            }
        }
    }

    //Print All Names of Meteors Starting with the lettter A
      public static void DataStartingWithA(List<String[]> parsedData) {
        // Define the column index for the first column 
        int columnIndex = 0;

        // Iterate and print names starting with "A"
        for (String[] row : parsedData) {
            if (row.length > columnIndex) {
                String value = row[columnIndex];
                if (value.startsWith("A")) {
                    System.out.println(value);
                }
            }
        }
    }

    //Print All Years after 2000
    public static void after2000(List<String[]> parsedData) {
        int columnIndex = 6;
    
        for (String[] row : parsedData) {
            if (row.length > columnIndex) {
                String value = row[columnIndex];
                try {
                    int year = Integer.parseInt(value);
                    if (year > 2000) {
                        System.out.println(year);
                    }
                } catch (NumberFormatException e) {
                    //WIP
                }
            }
        }
    }
}