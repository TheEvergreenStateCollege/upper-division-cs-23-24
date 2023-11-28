package com.ActivityTor.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DataStorageTest {
    
    @Test
    
public void testAddStepCount() {

DataStorage dataStorage = new DataStorage();
dataStorage.addStepCount("2023-10-16", 7402);
assertEquals(7402, dataStorage.getStepCout("2023-10-16"));
        // Arrange
        String date = "2023-11-28";
        int stepCount = 1000;

}
