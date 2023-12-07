package com.harlee.app;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class App {

    //Grab parsedData
    static String csvFile = "/workspace/upper-division-cs/dsa-23au/java-dsa/harlee-app/src/main/java/com/harlee/app/Meteorite_Landings2.csv"; //CSV file
    static char delimiter = ','; // delimiter in CSV file
    static List<String[]> parsedData = CSVDataParser.parseCSV(csvFile, delimiter);

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

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
        System.out.println("=======================");
        System.out.println("1. How many meteors fell in a given year?");
        System.out.println("2. What is the largest and smallest meteorite recorded? What is the average?");
        System.out.println("3. View a list of a requested column.");
        System.out.println("4. Something else sigh");
        System.out.println("5. Quit");
        System.out.println("=====================");
    }

    private static void optionOne() {
        System.out.println("You selected Option One.\n");
        System.out.println("=====================");;

        System.out.println("Below is the current date range that you can select:");
        CSVDataAnalyzer.minYearMaxYear(parsedData);
    
        int targetYear = CSVDataAnalyzer.getTargetYear();
    
        List<String> meteorNames = CSVDataAnalyzer.getMeteorNamesInYear(parsedData, targetYear);
    
        if (!meteorNames.isEmpty()) {
            System.out.println("Meteors fallen in " + targetYear + ": " + meteorNames.size());
            System.out.println("Meteor Names: " + meteorNames);
        } else {
            System.out.println("No meteors found in " + targetYear);
        }
    
        pressEnterToContinue();
    }

    private static void optionTwo() {
        System.out.println("You selected Option Two.\n");
    
        CSVDataAnalyzer.minMaxMass(parsedData);
        
       
        pressEnterToContinue();
    }

    private static void optionThree() {
        System.out.println("You selected Option Three.\n");
        System.out.println("* Index 0 = name        * Index 6 = year");
        System.out.println("* Index 1 = id #        * Index 7 = reclat");
        System.out.println("* Index 2 = nametype    * Index 8 = relong");
        System.out.println("* Index 3 = recclass    * Index 9 = geolocation");
        System.out.println("* Index 4 = mass        * Index 10 = states");
        System.out.println("* Index 5 = fall/found  * Index 11 = counties\n");
    
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the column index you want to visualize '#': ");
    
        try {
            int columnIndex = scanner.nextInt();
            CSVDataVisualizer.visualizeColumn(parsedData, columnIndex);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        } finally {
            pressEnterToContinue();
        }
    }

    private static void optionFour() {
        System.out.println("You selected Option Four.");
    
        MeteoriteYearChart chart = new MeteoriteYearChart("Meteorites Over the Years", parsedData);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
      
    
        pressEnterToContinue();
    }

    private static void clearConsole() {
        try {
            // Clear the console by printing empty lines
            for (int i = 0; i < 50; ++i) System.out.println();
            
        } catch (final Exception e) {
            // Handle exceptions
        }
    }

    private static void pressEnterToContinue() {
        System.out.print("Press Enter to continue...");
        new Scanner(System.in).nextLine();
        
    }

}



