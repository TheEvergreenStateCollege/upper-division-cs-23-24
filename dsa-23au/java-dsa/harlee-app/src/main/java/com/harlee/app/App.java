package com.harlee.app;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;


public class App {

    //Grab parsedData
    static String csvFile = "/workspace/upper-division-cs/dsa-23au/java-dsa/harlee-app/src/main/java/com/harlee/app/Meteorite_Landings2.csv"; //CSV file
    static char delimiter = ','; // delimiter in CSV file
    static List<String[]> parsedData = CSVDataParser.parseCSV(csvFile, delimiter);

    public static void main(String[] args) {
        //SCANNER FOR MENU
        Scanner scanner = new Scanner(System.in);
        int choice;
        //MENU SYSTEM
        do {
            printMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    optionOne();
                    break;
                case 2:
                    optionTwo();
                    break;
                case 3:
                    optionThree();
                    break;
                case 4:
                    optionFour();
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            clearConsole();

        } while (choice != 5);

        scanner.close();
    }
    //TERMINAL MENU
    private static void printMenu() {
        
        String titleArt = 
        "  __  __      _                  _ _         _                    _ _                 \n" +
        " |  \\/  | ___| |_ ___  ___  _ __(_) |_ ___  | |    __ _ _ __   __| (_)_ __   __ _ ___ \n" +
        " | |\\/| |/ _ \\ __/ _ \\/ _ \\| '__| | __/ _ \\ | |   / _` | '_ \\ / _` | | '_ \\ / _` / __|\n" +
        " | |  | |  __/ ||  __/ (_) | |  | | ||  __/ | |__| (_| | | | | (_| | | | | | (_| \\__ \\\n" +
        " |_|  |_|\\___|\\__\\___|\\___/|_|  |_|\\__\\___| |_____\\__,_|_| |_|\\__,_|_|_| |_|\\__, |___/\n" +
        "                                                                            |___/     ";

      
        System.out.println(titleArt);

        System.out.println("=======================");
        System.out.println("     * Main Menu *     ");
        System.out.println("=======================\n");
        System.out.println("1. Display a specific column from the meteorite dataset.\n");
        System.out.println("2. Analyze meteorite mass: find the largest, smallest, and average.\n");
        System.out.println("3. Explore meteorite occurrences: how many fell in a specific year?\n");
        System.out.println("4. View Credits.\n");
        System.out.println("5. Quit.\n");
        System.out.println("=====================");
    }

    //OPTION ONE
    private static void optionOne() {
        System.out.println("\nYou selected Option One: Display Dataset Column.\n");
        System.out.println("=================================================");
        System.out.println("* Index 0 = name        * Index 6 = year");
        System.out.println("* Index 1 = id #        * Index 7 = reclat");
        System.out.println("* Index 2 = nametype    * Index 8 = relong");
        System.out.println("* Index 3 = recclass    * Index 9 = geolocation");
        System.out.println("* Index 4 = mass        * Index 10 = states");
        System.out.println("* Index 5 = fall/found  * Index 11 = counties");
        System.out.println("=================================================\n");
    
        Scanner scanner = new Scanner(System.in);
        System.out.println("Some lists are not as clean as others. You've been warned.\n");
        System.out.print("Enter the column index you want to visualize '#': ");
    
        //CORRECT NUMBER TRY/CATCH
        try {
            int columnIndex = scanner.nextInt();
            CSVDataVisualizer.visualizeColumn(parsedData, columnIndex);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        } finally {
            pressEnterToContinue();
        };
    }
    //OPTION 2
    private static void optionTwo() {
        System.out.println("\nYou selected Option Two: Analyze Meteorite Mass\n");
        System.out.println("=======================================================\n");
    
        CSVDataAnalyzer.minMaxMass(parsedData);
        
        pressEnterToContinue();
    }
    //OPTION 3
    private static void optionThree() {
        System.out.println("\nYou selected Option Three: Explore Meteorite Occurrences.\n");
        System.out.println("==============================================================\n");

        System.out.printf("The current date range that you can select: %s%n\n", CSVDataAnalyzer.minYearMaxYear(parsedData));

        
        int totalMeteorites = parsedData.size();
        System.out.println("The current total of meteorites recorded: " + totalMeteorites);
        System.out.println("");

        int targetYear = CSVDataAnalyzer.getTargetYear();

        List<String> meteorNames = CSVDataAnalyzer.getMeteorNamesInYear(parsedData, targetYear);

        if (!meteorNames.isEmpty()) {
            System.out.println("==============================================================\n");
            System.out.println("Meteors fallen in " + targetYear + ": " + meteorNames.size());
            System.out.println("");
            System.out.println("Meteor Names: " + meteorNames);
            System.out.println("");
        } else {
            System.out.println("No meteors found in " + targetYear);
        }

        pressEnterToContinue();
       
    }

    private static void optionFour() {
        System.out.println("\nCREDITS: \n");
        System.out.println("=== Meteorite Data Analyzer ===");
        System.out.println("Version: 1.0");
        System.out.println("Author: Harlee Hair");
        System.out.println("Copyright @ 2023 NCC-1701");
        System.out.println("-------------------------------");
        System.out.println("Special thanks to:");
        System.out.println("- Paul and Richard");
        System.out.println("- Fellow Evergeen CS Students");
        System.out.println("-------------------------------");
        System.out.println("For more information, visit: https://data.nasa.gov\n");
    
    
        pressEnterToContinue();
    }

    private static void clearConsole() {
        try {
            // Clear the console by printing empty lines
            for (int i = 0; i < 50; ++i) System.out.println();
            
        } catch (final Exception e) {
        }
    }

    private static void pressEnterToContinue() {
        System.out.print("Press Enter to continue...");
        new Scanner(System.in).nextLine();
        
    }

}



