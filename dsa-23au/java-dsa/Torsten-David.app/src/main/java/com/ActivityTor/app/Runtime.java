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
                System.out.println("Press 6 to find the day with the maximum distance covered");
                System.out.println("Press 7 to find the day with the highest active energy burn");
                System.out.println("Press 8 to show Average Daily Active Energy Burn");
                System.out.println("Press 9 to find the day with the longest hand washing duration");

                
                

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

                        case "6":
                        //code to print max distance 
                       Object[] maxDistance = app.watchData.maxDistanceTree.findMax();
                       System.out.println("The longest distance covered: " + maxDistance[0] + " " +
                        "on" + " " + maxDistance[1]);
                        break;

                        case "7":
                        Object[] maxEnergy = app.watchData.activeEnergyTree.findMax();
                        System.out.println("The day with the highest active energy burn: " + maxEnergy[0] + " " +
                       "on" + " " + maxEnergy[1]);
                        break;

                        case "8":
                        double averageEnergy = app.watchData.averageDailyActiveEnergyBurn();
                        System.out.printf("Average Daily Active Energy Burn: %5.2f calories\n", averageEnergy);
                        break;

                        case "9":
                        Object[] maxHandWashing = app.watchData.handWashingDurationTree.findMax();
                        System.out.println("The day with the longest hand washing duration: " + maxHandWashing[0] + " " +
                       "seconds on" + " " + maxHandWashing[1]);
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
