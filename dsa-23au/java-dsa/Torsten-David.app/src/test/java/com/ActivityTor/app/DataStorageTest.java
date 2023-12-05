package com.ActivityTor.app;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
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
}
