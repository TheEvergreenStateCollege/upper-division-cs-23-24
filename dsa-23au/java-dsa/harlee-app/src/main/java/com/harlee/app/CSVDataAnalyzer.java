package com.harlee.app;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class CSVDataAnalyzer {

    static int nameColumnIndex = 0;
    static int massColumnIndex = 4;
    static int yearColumnIndex = 6;

    // How many meteors fell in the given year and what were their names?

    //
    public static int getTargetYear() {
        Scanner scanner = new Scanner(System.in);

        int targetYear = -1;
        boolean isValidInput = false;

        do {
            System.out.println("Enter year: ");
            String inputYear = scanner.nextLine();

            try {
                targetYear = Integer.parseInt(inputYear);

                // Check if the year is within a valid range (you can adjust the range as needed)
                if (targetYear >= 0 && targetYear <= 9999) {
                    isValidInput = true;
                } else {
                    System.out.println("Invalid year. Please enter a valid year.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid year.");
            }

        } while (!isValidInput);

        return targetYear;
    }

    public static List<String> getMeteorNamesInYear(List<String[]> parsedData, int targetYear) {

        List<String> meteorNames = new ArrayList<>();

        for (String[] row : parsedData) {
            try {
                int year = Integer.parseInt(row[yearColumnIndex]);

                if (year == targetYear) {
                    String meteorName = row[nameColumnIndex];
                    meteorNames.add(meteorName);
                }
            } catch (NumberFormatException e) {
                // Invalid year
                System.out.println("Invalid year format in the CSV data.");
            }
        }
        return meteorNames;
    }


    public static void minYearMaxYear(List<String[]> parsedData) {
       
        int minYear = Integer.MAX_VALUE;
        int maxYear = Integer.MIN_VALUE;
    
        for (String[] record : parsedData) {
            try {
                int year = Integer.parseInt(record[yearColumnIndex]);
                minYear = Math.min(minYear, year);
                maxYear = Math.max(maxYear, year);
            } catch (NumberFormatException e) {
                
            }
        }
    
        if (minYear != Integer.MAX_VALUE && maxYear != Integer.MIN_VALUE) {
            System.out.println("Earliest year: " + minYear);
            System.out.println("Latest year: " + maxYear);
        } else {
            System.out.println("No valid years found in the data.");
        }
    
    }
    
    public static void minMaxMass(List<String[]> parsedData){

        double totalMass = 0;  
        int meteoriteCount = 0;

        String smallestMeteoriteName = null;
        double smallestMeteoriteMass = Double.MAX_VALUE;
        String largestMeteoriteName = null;
        double largestMeteoriteMass = Double.MIN_VALUE;

        for (String[] row : parsedData) {
            try {
                double mass = Double.parseDouble(row[massColumnIndex]);
        
                // Update smallest and largest meteorites
                if (mass < smallestMeteoriteMass) {
                    smallestMeteoriteMass = mass;
                    smallestMeteoriteName = row[nameColumnIndex];
                }
                
                if (mass > largestMeteoriteMass) {
                    largestMeteoriteMass = mass;
                    largestMeteoriteName = row[nameColumnIndex];
                }
        
                // Accumulate total mass
                totalMass += mass;
                meteoriteCount++;
            } catch (NumberFormatException e) {
                // Invalid mass format
                //System.out.println("Invalid mass format in the CSV data. Skipping this entry.");
            }
        }

        if (smallestMeteoriteName != null && largestMeteoriteName != null) {
            System.out.println("Smallest Meteorite: " + smallestMeteoriteName + ", Mass: " + smallestMeteoriteMass);
            System.out.println("Largest Meteorite: " + largestMeteoriteName + ", Mass: " + largestMeteoriteMass);
        } else {
            System.out.println("No valid meteorites found in the data.");
        }

        if (meteoriteCount > 0) {
            double averageMass = totalMass / meteoriteCount;
            System.out.println("Total Average Mass of Meteorites: " + averageMass);
        } else {
            System.out.println("No valid meteorites found for calculating average mass.");
        }


    }


    //Print All Names of Meteors
    public static void printNames(List<String[]> parsedData) {
        
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