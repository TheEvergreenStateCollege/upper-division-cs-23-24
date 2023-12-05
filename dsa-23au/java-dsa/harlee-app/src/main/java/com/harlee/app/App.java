package com.harlee.app;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        
        Scanner myScan = new Scanner(System.in);
        String csvFile = "/workspace/DSA2023-Project/harlee-app/src/main/java/com/harlee/app/Meteorite_Landings2.csv"; //CSV file
        char delimiter = ','; // delimiter in CSV file
        List<String[]> parsedData = CSVDataParser.parseCSV(csvFile, delimiter);
        
        

        System.out.println("Enter year: ");
        String inputYear = myScan.nextLine(); // specify the target year // make this a user input
        int targetYear = Integer.parseInt(inputYear);
        int meteorCount = CSVDataAnalyzer.countMeteorsInYear(parsedData, targetYear);
        System.out.println("Meteors fallen in " + targetYear + ": " + meteorCount);

        //System.out.println("Enter country: ");
        //String inputCountry = myScan.nextLine();
        //int meteorCountryCount = CSVDataAnalyzer.meteorsCountry(parsedData, inputCountry);
        //System.out.println("Meteors fall in " + inputCountry + ": " + meteorCountryCount);

        myScan.close();

        //CSVDataVisualizer.after2000(parsedData);

        int choice; 

        do {

            System.out.println("Menu: ");
            System.out.println("1.");
            System.out.println("2.");
            System.out.println("3.");
            System.out.println("4.");
            System.out.println("Enter choice: ");

            choice = myScan.nextInt();
            myScan.nextLine();

            switch (choice)
            {

                case 1:
                //
                break;

                case 2:
                //
                break;

                case 3:
                //
                break;

                case 4:
                // 
                break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");


            }

        } while (choice != 4);

        
    }
}



