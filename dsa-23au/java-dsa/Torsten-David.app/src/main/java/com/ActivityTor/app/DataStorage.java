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
   
    BST<Double> soundLevelTree = new BST<Double>();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // add public getters and setters for BST
    public void addToSoundTree(double soundLevel, String date){
        soundLevelTree.insert(soundLevel, date);
    }

    public void addStepCount(String date, int stepCount) {
        stepCounts.put(date, stepCount);
    }

    public void addDistance(String date, double distance) {
        distances.put(date, distance);
    }

    public void addCalories(String date, Double calorie) {
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

    // Method for flights climbed 
    public void printFlightsClimbed()
    {
        for (Map.Entry<String, Integer> entry : flightsClimbed.entrySet())
        {
            System.out.println("Date: " + entry.getKey()+ ", FlightsClimbed: " + entry.getValue());
            }
    }

    // Method for active energy 
    public void printActiveEnergy()
    {
        for (Map.Entry<String, Double> entry : calories.entrySet())
     {
         System.out.println("Date: " + entry.getKey()+ ", Calories: " + entry.getValue());
         }
    }

    // Method for Hand washing  
    public void printHandWashing()
    {
        for (Map.Entry<String, Double> entry : handWashingSeconds.entrySet())
     {
         System.out.println("Date: " + entry.getKey()+ ", Average Time Washing Hands: " + entry.getValue());
         }
    }

    // Method for Resting Energy  
    public void printRestingEnergy()
    {
        for (Map.Entry<String, Double> entry : restingEnergy.entrySet())
     {
         System.out.println("Date: " + entry.getKey()+ ", Energy while not active: " + entry.getValue());
         }
    }

    // Method for Sound Levels  
    public void printSoundLevels()
    {
        for (Map.Entry<String, Double> entry : soundLevels.entrySet())
     {
         System.out.println("Date: " + entry.getKey()+ ", Sound Level : " + entry.getValue());
         }
    }

    // Method to Print All Data 
    public void printAllData()
    {
        System.out.println(" Step Count: ");
        printStepCounts();

        System.out.println(" Distances: ");
        printDistances();

        System.out.println(" Flights Climbed: ");
        printFlightsClimbed();

        System.out.println(" Active Energy: ");
        printActiveEnergy();

        System.out.println(" Hand Washing: ");
        printHandWashing();

        System.out.println(" Resting Energy: ");
        printRestingEnergy();

        System.out.println(" Sound Level: ");
        printSoundLevels();
    }

    
}