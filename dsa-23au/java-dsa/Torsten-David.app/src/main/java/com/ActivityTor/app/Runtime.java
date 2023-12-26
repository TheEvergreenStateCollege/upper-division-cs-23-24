package com.ActivityTor.app;

import static java.util.stream.Collectors.flatMapping;

import java.util.Scanner;

public class Runtime {
    public static void Run(AppleWatchDataApp app){
        try (Scanner kb = new Scanner(System.in)) {
            System.out.println("Welcome to Torsten's apple watch data ");
            System.err.println();
            boolean running = true;
            while(running){
                System.out.println("Press 1 to Print all data");
                System.out.println("Press 2 to find which day had the highest sound level ");
                System.out.println("Press 3 to find the the day with the lowest sound level");
                System.out.println("Press 4 to show credits for this project");
                System.out.println("Press 5 to show Average Daily Step Count for this project");
                System.out.println("Type 'add data' to add more data to CSV file");
                

                System.out.println("Press Q to Quit");

                String usrResponse = kb.nextLine();
                switch (usrResponse){
                    case "1":
                    //Code to show all data
                        app.showAllData();
                        break;

                    case "2":
                        //code to print max sound level
                       Object[] maxSoundLevel = app.watchData.soundLevelTree.findMax();
                       System.out.println("The highest sound level: " + maxSoundLevel[0] + " " +
                        "on" + " " + maxSoundLevel[1]);


                        break;

                    case "3":
                        //code to print the min sound level
                        Object[] minSoundLevel = app.watchData.soundLevelTree.findMin();
                       System.out.println("The lowest sound level: " + minSoundLevel[0] + " " +
                        "on" + " " + minSoundLevel[1]);

                        break;
                    
                    case "4":
                        System.out.println("****************************");
                        System.out.println("*         CREDITS          *");
                        System.out.println("****************************");
                        System.out.println("*  Developers:             *");
                        System.out.println("*  - Torsten               *");
                        System.out.println("*  - David                 *");
                        System.out.println("*                          *");
                        System.out.println("*  Special Thanks To:      *");
                        System.out.println("*  - [Paul]            *");
                        System.out.println("*  - [Richard]            *");
                        System.out.println("*  - [Quinn]            *");
                        System.out.println("****************************");
                        break;

                        // code to convert average into a 100th decimal percentage
                    case "5":
                        double averageSteps = app.watchData.AverageDailyStepCount(); 
                        System.out.printf("Averge Daily Step Count: %5.2f \n" , averageSteps);
                        break;

                    case "add data":
                    System.out.println("Enter Date:");
                    String date = kb.nextLine();
        
                    // Collect steps
                    System.out.println("Enter Steps:");
                    int steps = Integer.parseInt(kb.nextLine());
        
                    // Collect distance
                    System.out.println("Enter Distance:");
                    double distance = Double.parseDouble(kb.nextLine());

                    // Collect flgihts
                    System.out.println("Enter Flgihts climbed:");
                    int flights = Integer.parseInt(kb.nextLine());

                    // Collect active energy 
                    System.out.println("Enter active energy:");
                    double calories = Double.parseDouble(kb.nextLine());

                    // Collect handwashing
                    System.out.println("Enter handwashing:");
                    double handWashing = Double.parseDouble(kb.nextLine());

                    // Collect resting energy 
                    System.out.println("Enter resting energy:");
                    double restingEnergy = Double.parseDouble(kb.nextLine());

                    // Collect sound level
                    System.out.println("Enter sound level:");
                    double soundLevel = Double.parseDouble(kb.nextLine());
        
                    // Call the method in AppleWatchDataApp to append new data
                    app.appendToCSV(date, steps, distance, flights, calories, handWashing, restingEnergy, soundLevel);
                    System.out.println("Data added to CSV!");
                        break;

                    case "Q":
                        running = false;
                        break;

                    default:
                        System.out.println("Your input is garbage, are you garbage too? Try again!");
                        break;
                }

            }
        }
    }

}
