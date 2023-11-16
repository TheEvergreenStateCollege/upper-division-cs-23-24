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

        // Provide the path to your CSV file
       String csvFilePath = "AppleWatchData_myData.csv";

       // InputStream is = ClassLoader.getSystemResourceAsStream("/AppleWatchData_myData.csv");
       InputStream is = AppleWatchDataApp.class.getClassLoader().getResourceAsStream(csvFilePath);
      // System.out.printf(" is null? %b \n", is == null);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 0 || line.length() == 0) {
                    continue;
                }
                System.out.printf(" Line %d -%s\n", i, line);
                i += 1;    
                try {
                    String dateString = String.format("2023-11-13 %s", tokens[9]);
                    String dateFormat = "yyyy-MM-dd HH:mm a";
                    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                        DataStorage watchData  = new DataStorage();

                } catch(IndexOutOfBoundsException ioobe) {
                    // that line did not have an appointment time                    
                }
            }
        } catch(IOException ioe) {
            System.err.println(ioe.toString());
        }

/*
        DataReader dataReader = new DataReader(csvFilePath);
        List<String[]> csvData = dataReader.readCSVData();

         // Process the data and add it to your DataStorage
         DataStorage watchData = new DataStorage();
         processData(watchData, csvData);

         // Print the data after processing
           watchData.printAllData();
 */       

    }
    
    private static void processData(DataStorage dataStorage, List<String[]> csvData)
    {
        for (String[] values : csvData)
        {

            String date = values[0];
            int stepCount = Integer.parseInt(values[1]);
            double distance = Double.parseDouble(values[2]);
            int flightsClimbed = Integer.parseInt(values[3]);
            double activeEnergy = Double.parseDouble(values[4]);
            double handWashingSeconds = Double.parseDouble(values[5]);
            double restingEnergy = Double.parseDouble(values[6]);
            double soundLevel =Double.parseDouble(values[7]);

            dataStorage.addStepCount(date, stepCount);
            dataStorage.addDistance(date, distance);
            dataStorage.addCalories(date, activeEnergy);
            dataStorage.addRestingEnergy(date, restingEnergy);
            dataStorage.addSoundLevels(date, soundLevel);
            dataStorage.addFlightsClimbed(date, flightsClimbed);
            dataStorage.addHandWashingSeconds(date, handWashingSeconds);


            
        }

    }
}
