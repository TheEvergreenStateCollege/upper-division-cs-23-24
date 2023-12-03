package com.ActivityTor.app;

import static java.util.stream.Collectors.flatMapping;

import java.util.Scanner;

public class Runtime {
    public static void Run(AppleWatchDataApp app){
        Scanner kb = new Scanner(System.in);
        System.out.println("Welcome to Torsten's apple watch data developed by Torsten and David");
        System.err.println();
        boolean running = true;
        while(running){
            System.out.println("Press 1 to Print all data");
            System.out.println("Press 2 to find which day had the highest sound level ");
            System.err.println("Press 3 to find the the day with the lowest sound level");
            
            System.out.println("Press Q to Quit");

            String usrResponse = kb.nextLine();
            switch (usrResponse){
                case "1":
                    app.showAllData();
                    break;

                case "2":
                    //code to print max sound level
                
                    break;

                case "3":
                    //code to print the min sound level
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
