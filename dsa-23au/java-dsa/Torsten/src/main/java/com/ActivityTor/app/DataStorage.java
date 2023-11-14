package com.ActivityTor.app;

import java.util.HashMap;
import java.util.Map;
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

    public void addStepCount(String date, int stepCount) {
        stepCounts.put(date, stepCount);
    }

    public void addDistance(String date, double distance) {
        distances.put(date, distance);
    }

    public void addCalories(String date, double calorie) {
        calories.put(date, calorie);
    }

    public void addRestingEnergy(String date, double energy) {
        restingEnergy.put(date, energy);
    }
    public void addSoundLevels(String date, double level) {
        soundLevels.put(date, level);
    }

    public void addFlightsClimbed(String date, int flights) {
        flightsClimbed.put(date, flights);
    }

    public void addHandWashingSeconds(String date, double seconds) {
        handWashingSeconds.put(date, seconds);
    }

    // Method to print step counts
    public void printStepCounts() 
    {
    for (Map.Entry<String, Integer> entry : stepCounts.entrySet()) 
    {
        System.out.println("Date: " + entry.getKey() + ", Step Count: " + entry.getValue());
        }
     }

    // Method to print distances
    public void printDistances() 
    {
    for (Map.Entry<String, Double> entry : distances.entrySet()) 
    {
        System.out.println("Date: " + entry.getKey() + ", Distance: " + entry.getValue());
         }
    }

// Similar methods for other data types (calories, resting energy, etc.)


}