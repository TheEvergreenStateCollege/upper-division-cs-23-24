package com.ActivityTor.app;

import java.util.Scanner;

public class Runtime {
    public static void Run(AppleWatchDataApp app){
        Scanner kb = new Scanner(System.in);
        System.out.println("Welcome to Torsten's apple watch data developed by Torsten and David");
        boolean running = true;
        while(running){
            System.out.println("Press 1 to Print all data");
            System.out.println("Press Q to Quit");
            String usrResponse = kb.nextLine();
            switch (usrResponse){
                case "1":
                    app.showAllData();
                    break;

                case "Q":
                    running = false;
                    break;

                default:
                    System.out.println("Your input is garbage, are you garbage too?");
                    break;
            }

        }
    }

}
