package com.ActivityTor.app;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.BeforeClass;

public class DataStorageTest {

    static AppleWatchDataApp app; 

    @BeforeClass
    public static void setUp()
    {
        app = new AppleWatchDataApp();
        app.loadData();
    }

    @Test
    public void testSoundLevel()
    {
         Object[] maxSoundLevel = app.watchData.soundLevelTree.findMax();
        assertEquals(2, maxSoundLevel.length);

        assertEquals(84.0, maxSoundLevel[0]);

        String dateString = "2023-11-04"; // Sample date string in yyyy-MM-dd format

        // Define the date format matching your input string
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        Date date = null; 
        
        try {
            // Parse the string to create a Date object
             date = dateFormat.parse(dateString);

        
            // You can perform operations with the Date object here
            // ...

        } catch (ParseException e) {
            // In case the string doesn't match the specified format
            e.printStackTrace();
        }
       
            assertEquals(1, ((ArrayList<Date>)maxSoundLevel[1]).size());
    }

          @Test 

         public void testAverageDailyStepCount()  {
             DataStorage dataStorage = new DataStorage();

             dataStorage.addStepCount("2023-10-08", 2908);
             dataStorage.addStepCount("2023-10-11", 4583);
             dataStorage.addStepCount("2023-10-31", 10124);

             double average = dataStorage.AverageDailyStepCount();

             assertEquals(5871.666667, average, 0.001);

    }
    @Test

            public void testPrintAllData(){
                
                DataStorage dataStorage = new DataStorage();

                // Set up a ByteArrayOutputStream to capture printed output
                 ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                 PrintStream printStream = new PrintStream(outputStream);

                 // Redirect System.out to the ByteArrayOutputStream
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        // Invoke the method which prints data to console
        dataStorage.printAllData();

        // Restore the original System.out
        System.setOut(originalOut);

        // Get the printed output from the ByteArrayOutputStream
        String printedData = outputStream.toString();

        // Test if the printed output contains expected strings
        
        assertEquals(true, printedData.contains("Step Count"));
        assertEquals(true, printedData.contains("Distances"));
        assertEquals(true, printedData.contains("Flights Climbed"));
        assertEquals(true, printedData.contains("Active Energy"));
        assertEquals(true, printedData.contains("Hand Washing"));
        assertEquals(true, printedData.contains("Resting Energy"));
        assertEquals(true, printedData.contains("Sound Level"));

     }

     @Test

     public void testAddDistance() {
        DataStorage dataStorage = new DataStorage();
        dataStorage.addDistance("2023-10-5", 6.3);
        assertEquals(6.3, dataStorage.distances.get("2023-10-5"), 0.0);
    }

    @Test

    public void testAddCalories(){
        DataStorage dataStorage = new DataStorage();
        dataStorage.addCalories("2023-11-1", 116.0);
        assertEquals(116, dataStorage.calories.get("2023-11-1"), 0);

    }
}