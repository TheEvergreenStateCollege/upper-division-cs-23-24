package com.ActivityTor.app;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AppleWatchDataApp 
{    

     public static void main( String[] args )
    {

        String csvFilePath = "AppleWatchData_myData.csv";
        InputStream is = AppleWatchDataApp.class.getClassLoader().getResourceAsStream(csvFilePath);
    
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            int i = 0;
    
            DataStorage watchData = new DataStorage(); // Initialize DataStorage
    
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 0 || line.length() == 0) {
                    continue;
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
    
                    // Add parsed data to DataStorage
                    watchData.addStepCount(date, steps);
                    watchData.addDistance(date, distance);
                    watchData.addFlightsClimbed(date, flightsClimbed);
                    watchData.addCalories(date, activeEnergy);
                    watchData.addHandWashingSeconds(date, handWashingSeconds);
                    watchData.addRestingEnergy(date, restingEnergy);
                    watchData.addSoundLevels(date, soundLevels);
    
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
    
            watchData.printAllData(); // Print processed data
        } catch (IOException ioe) {
            System.err.println(ioe.toString());
        }
    }
}
