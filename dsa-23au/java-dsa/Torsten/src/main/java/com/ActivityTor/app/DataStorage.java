package com.ActivityTor.app;

import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DataStorage {
    
    private HashMap<String, Integer> stepCounts = new HashMap<>();
    private HashMap<String, Double> distances = new HashMap<>();
    private HashMap<String, Double> calories = new HashMap<>();
    private HashMap<String, Double> restingEnergy = new HashMap<>();
    private HashMap<String, Double> soundLevels = new HashMap<>();
    private HashMap<String, Integer> flightsClimbed = new HashMap<>();
    private HashMap<String, Double> handWashingSeconds = new HashMap<>();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void addData(String date, int stepCount, double distance, double calorie, double restingEnergy, double soundLevel, int flightsClimbed, double handWashingSeconds) {
        stepCounts.put(date, stepCount);
        distances.put(date, distance);
        calories.put(date, calorie);
        //restingEnergy.put(date, restingEnergy);
        restingEnergy.put(date, restingEnergy);
        soundLevels.put(date, soundLevel);
        flightsClimbed.put(date, flightsClimbed);
        handWashingSeconds.put(date, handWashingSeconds);
    }

    // Other accessor and mutator methods for specific data types...
}
