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

    public void addStepCount(String date, int stepCount ){
        stepCounts.put(date, stepCount);
    }

    public void updateStepCount(String date, int newStepCount){
        stepCounts.put(date, newStepCount);
    }

    public void addDistance(String date, double distance) {
        distances.put(date, distance);
    }

    public void updateDistance(String date, double newDistance) {
        distances.put(date, newDistance);
    }

    public void addCalories(String date, double calorie) {
        calories.put(date, calorie);
    }

    public void updateCalories(String date, double newCalorie) {
        calories.put(date, newCalorie);
    }

    public void addRestingEnergy(String date, double energy) {
        restingEnergy.put(date, energy);
    }

    public void updateRestingEnergy(String date, double newEnergy) {
        restingEnergy.put(date, newEnergy);
    }

    public void addSoundLevels(String date, double level) {
        soundLevels.put(date, level);
    }

    public void updateSoundLevels(String date, double newLevel) {
        soundLevels.put(date, newLevel);
    }

    public void addFlightsClimbed(String date, int flights) {
        flightsClimbed.put(date, flights);
    }

    public void updateFlightsClimbed(String date, int newFlights) {
        flightsClimbed.put(date, newFlights);
    }
    public void addHandWashingSeconds(String date, double seconds) {
        handWashingSeconds.put(date, seconds);
    }

    public void updateHandWashingSeconds(String date, double newSeconds) {
        handWashingSeconds.put(date, newSeconds);
    }
}

