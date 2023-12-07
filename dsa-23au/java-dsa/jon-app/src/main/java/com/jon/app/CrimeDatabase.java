package com.jon.app;
 

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrimeDatabase {

    private List<CrimeData> crimeDataList = new ArrayList<>();

    // Assume CrimeDatabase class has a method to add crime data
    public void addCrimeData(String[] columns) {
        CrimeData crimeData = new CrimeData(
            columns[1],  // Year
            columns[0],  // County
            columns[2],  // CrimeType
            Integer.parseInt(columns[3]),  // Population
            Integer.parseInt(columns[4]),  // IndexCount
            Float.parseFloat(columns[5]),  // IndexRate
            Integer.parseInt(columns[6]),  // ViolentCount
            Float.parseFloat(columns[7]),  // ViolentRate
            Integer.parseInt(columns[8]),  // PropertyCount
            Float.parseFloat(columns[9]),  // PropertyRate
            Integer.parseInt(columns[10]), // FirearmCount
            Float.parseFloat(columns[11])  // FirearmRate
        );
    
        addCrimeData(crimeData);
    }
    

       
    


    // Assuming you have a method to convert the row map to a CrimeData object
    private CrimeData convertRowToCrimeData(Map<String, Object> row) {
        // Implement logic to convert the row map to a CrimeData object
        // For example, create a CrimeData object and set its properties based on the map
        // Return the CrimeData object
        return null; // replace with the actual implementation
    }

    private boolean isFloat(String columnValue) {
        try {
            Float.parseFloat(columnValue);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isInteger(String columnValue) {
        try {
            Integer.parseInt(columnValue);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void displayDataForCountyAndYear(CrimeDatabase cdd, String County, int Year) {
        List<CrimeData> crimeDataList = cdd.getCrimeDataList();
    
        if (crimeDataList == null || crimeDataList.isEmpty()) {
            System.out.println("No crime data available.");
            return;
        }
    
        System.out.println("Crime Data for " + County + " County in " + Year + ":");
    
        boolean foundData = false;
    
        for (CrimeData crimeData : crimeDataList) {
            if (crimeData.getCounty().equalsIgnoreCase(County) && crimeData.getYear().equals(String.valueOf(Year))) {
                // Debugging: Print the CrimeData object to see what's being matched
                System.out.println("Found matching data: " + crimeData);
        
                // Display relevant information for the matching county and year
                System.out.println("Date: " + crimeData.getYear());
                System.out.println("Crime Type: " + crimeData.getCrimeType());
                System.out.println("Index Count: " + crimeData.getIndexCount());
                System.out.println("Index Rate: " + crimeData.getIndexRate());
                // Add more information as needed
                System.out.println("------------------------------"); // separation
                foundData = true;
            }
        }
        
    
        if (!foundData) {
            System.out.println("No crime data found for " + County + " County in " + Year + ".");
        }
    }
    

    // Method to calculate year-to-year changes in crime rates
    public void calculateYearlyCrimeRateChanges() {
        List<CrimeData> crimeDataList = getCrimeDataList();

        if (crimeDataList == null || crimeDataList.isEmpty()) {
            System.out.println("No crime data available for analysis.");
            return;
        }

        Map<Integer, Integer> yearlyCrimeCounts = new HashMap<>();

        // Count the number of crimes for each year
        for (CrimeData crimeData : crimeDataList) {
            int year = getYearFromString(crimeData.getYear());
            yearlyCrimeCounts.put(year, yearlyCrimeCounts.getOrDefault(year, 0) + 1);
        }

        // Calculate year-to-year changes in crime rates
        Set<Integer> years = yearlyCrimeCounts.keySet();
        List<Integer> sortedYears = new ArrayList<>(years);
        Collections.sort(sortedYears);

        System.out.println("Year-to-Year Changes in Crime Rates:");

        for (int i = 1; i < sortedYears.size(); i++) {
            int currentYear = sortedYears.get(i);
            int previousYear = sortedYears.get(i - 1);

            int currentYearCrimeCount = yearlyCrimeCounts.get(currentYear);
            int previousYearCrimeCount = yearlyCrimeCounts.get(previousYear);

            double crimeRateChange = ((double) currentYearCrimeCount - previousYearCrimeCount) / previousYearCrimeCount * 100;

            System.out.printf("%d to %d: %.2f%% change\n", previousYear, currentYear, crimeRateChange);
        }
    }

    // Add a CrimeData object to the database
    public void addCrimeData(CrimeData crimeData) {
        crimeDataList.add(crimeData);
    }

    private static int getYearFromString(String yearString) {
        try {
            return Integer.parseInt(yearString);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing year: " + e.getMessage());
            return -1; // or throw an exception, depending on your error-handling strategy
        }
    }
    
    

    private List<CrimeData> getCrimeDataList() {
        // Assuming you have a method to obtain or generate CrimeData objects
        List<CrimeData> crimeDataList = generateCrimeData(); // You need to implement this method

        return crimeDataList;
    }

    private List<CrimeData> generateCrimeData() {
        // Example: Generating sample CrimeData objects
        List<CrimeData> generatedList = new ArrayList<>();

        // Implement logic to generate CrimeData objects
        // ...

        return generatedList;
    }

    public static void main(String[] args) {
        CrimeDatabase cdd = new CrimeDatabase();
        Scanner scanner = new Scanner(System.in);

        // Load data from CSV file
        try (InputStream is = CrimeDatabase.class.getClassLoader().getResourceAsStream("Crime.csv");
             BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)))) {
            // Assuming the first line of the CSV file contains the header
            String headerLine = br.readLine();
            String[] header = headerLine.split(",");

            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length >= 12) {
                    System.out.println(Arrays.toString(tokens)); // Add this line for debugging
                    cdd.addCrimeData(tokens);
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Display menu
        int choice;
        do {
            System.out.println("\nNew York Criminal Justice Crime Database Menu:");
            System.out.println("1. Display data for a specific county and year");
            System.out.println("2. Temporal Analysis");
            System.out.println("3. Big 0 Analysis");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume the invalid input
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Prompt user for county and year
                    System.out.print("Enter County: ");
                    String county = scanner.next(); // Use lowercase for variable names
                    System.out.print("Enter Year: ");
                    int year = scanner.nextInt();

                    // Display data for the specified county and year
                    displayDataForCountyAndYear(cdd, county, year);
                    break;
                case 2:
                    // Add logic for Temporal Analysis
                    System.out.print("Enter County: ");
                    String countyTemporal = scanner.next();
                    System.out.print("Enter Year: ");
                    int yearTemporal = scanner.nextInt();
                    // Add your temporal analysis logic here
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        } while (choice != 4); // Update to match the menu options

        scanner.close();
    }
}
