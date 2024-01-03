package com.ActivityTor.app;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;

public class AppleWatchDataApp 
{    

    DataStorage watchData = new DataStorage(); // Initialize DataStorage

    public void loadData(){
        
        String csvFilePath = "AppleWatchData_myData.csv";
        InputStream is = AppleWatchDataApp.class.getClassLoader().getResourceAsStream(csvFilePath);
    
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            
            boolean isHeaderSkipped = false;

            while ((line = br.readLine()) != null) 
            {
                String[] tokens = line.split(",");

                //skip any 0 length lines
                if (tokens.length == 0 || line.length() == 0) 
                {
                    continue;
                }

                //skip first line that is coluoum headers
                if (!isHeaderSkipped) 
                {
                    isHeaderSkipped = true;
                    continue; 
                }
    
                // try to scan one line and add to the data structure 
                try 
                {
                    // assume formating is correct 
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
                    watchData.addToSoundTree(date, soundLevels);
                    watchData.addToDistanceTree(date, distance);
                    watchData.addToActiveEnergyTree(date, activeEnergy);
                    watchData.addToHandWashingDurationTree(date, handWashingSeconds); 
                

                } catch (IndexOutOfBoundsException | NumberFormatException e) 
                {
                    System.out.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            } // while()
    
        } catch (IOException ioe) 
        {
            System.err.println(ioe.toString());
        }
    } // loadData()

    public void showAllData(){
        watchData.printAllData(); // Print processed data
    }

    
} //class applWatchDataApp   

