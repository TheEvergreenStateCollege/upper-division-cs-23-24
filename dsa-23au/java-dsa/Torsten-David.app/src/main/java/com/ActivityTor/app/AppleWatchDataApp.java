package com.ActivityTor.app;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AppleWatchDataApp 
{    

    DataStorage watchData = new DataStorage(); // Initialize DataStorage

    public void loadData(){
        
        String csvFilePath = "AppleWatchData_myData.csv";
        InputStream is = AppleWatchDataApp.class.getClassLoader().getResourceAsStream(csvFilePath);
    
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            int i = 0;


            boolean isHeaderSkipped = false;
            //DataStorage watchData = new DataStorage(); // Initialize DataStorage
    
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 0 || line.length() == 0) {
                    continue;
                }

                if (!isHeaderSkipped) {
                    isHeaderSkipped = true;
                    continue; // Skip the header line
                }
    
                try {
                    String date = tokens[0];
                    int steps = Integer.parseInt(tokens[1]);
                    double distance = Double.parseDouble(tokens[2]);
                    int flightsClimbed = Integer.parseInt(tokens[3]);
                    double activeEnergy = Double.parseDouble(tokens[4]);
                    double handWashingSeconds = Double.parseDouble(tokens[5]);
                    double restingEnergy = Double.parseDouble(tokens[6]);
                    double soundLevels = Double.parseDouble(tokens[7]);
                    
                    //more code that adds date paired with it's sound level to a min tree or max tree (or min or max heap)

                    // Add parsed data to DataStorage
                    watchData.addStepCount(date, steps);
                    watchData.addDistance(date, distance);
                    watchData.addFlightsClimbed(date, flightsClimbed);
                    watchData.addCalories(date, activeEnergy);
                    watchData.addHandWashingSeconds(date, handWashingSeconds);
                    watchData.addRestingEnergy(date, restingEnergy);
                    watchData.addSoundLevels(date, soundLevels);
                    watchData.addToSoundTree(soundLevels, date);
    
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
    
        } catch (IOException ioe) {
            System.err.println(ioe.toString());
        }
    }

    public void showAllData(){
        watchData.printAllData(); // Print processed data
    }

    //Method to add data to csv
    public void appendToCSV(String date, int steps, double distance, int flights, double calories, double handWashing, double restingEnergy, double soundLevel) 
    {
        String filePath = "resources/AppleWatchData_myData.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Append new data in CSV format
            String newData = date + "," + steps + "," + distance + "," + flights + "," + calories + "," 
            + handWashing + "," + restingEnergy + "," + soundLevel;
            writer.write(newData);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}   

