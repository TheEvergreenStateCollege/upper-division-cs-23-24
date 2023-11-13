package com.ActivityTor.app;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;

public class AppleWatchDataApp 
{
    public static void main( String[] args )
    {
        DataStorage watchData = new DataStorage();

        // Provide the path to your CSV file
        String csvFilePath = "";

        DataReader dataReader = new DataReader(csvFilePath);
        List<String[]> csvData = dataReader.readCSVData();

         // Process the data and add it to your DataStorage
         processData(watchData, csvData);

    }
    private static void processData(DataStorage dataStorage, List<String[]> csvData)
    {
        for (String[] values : csvData)
        {

            String date = values[0];
            int stepCount = Integer.parseInt(values[1]);
            double distance = Double.parseDouble(values[2]);
            int flightsClimbed = Integer.parseInt(values[3]);
            double activeEnergy =Double.parseDouble(values[4]);
            double handWashingSeconds = Double.parseDouble(values[5]);
            double restingEnergy = Double.parseDouble(values[6]);
            double soundLevel =Double.parseDouble(values[7]);

            dataStorage.addStepCount(date, stepCount);
            dataStorage.addDistance(date, distance);
            dataStorage.addCalories(date, calorie);
            dataStorage.addRestingEnergy(date, restingEnergy);
            dataStorage.addSoundLevels(date, soundLevel);
            dataStorage.addFlightsClimbed(date, flightsClimbed);
            dataStorage.addHandWashingSeconds(date, handWashingSeconds);

        }

    }
}
