package com.ActivityTor.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DataStorage {
    
    HashMap<String, Integer> stepCounts = new HashMap<>();
    HashMap<String, Double> distances = new HashMap<>();
    HashMap<String, Double> calories = new HashMap<>();
    HashMap<String, Double> restingEnergy = new HashMap<>();
    HashMap<String, Double> soundLevels = new HashMap<>();
    HashMap<String, Integer> flightsClimbed = new HashMap<>();
    HashMap<String, Double> handWashingSeconds = new HashMap<>();
   
    BST<Double> soundLevelTree = new BST<Double>();
    BST<Double> maxDistanceTree = new BST<Double>();
    BST<Double>activeEnergyTree = new BST<Double>();
    BST<Double> handWashingDurationTree = new BST<Double>();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // methods for BST
    public void addToSoundTree(String date, double soundLevel)
    {
        soundLevelTree.insert(date, soundLevel);
    }

    public void addToDistanceTree(String date, double distance){
        maxDistanceTree.insert(date, distance);
    }

    public void addToActiveEnergyTree(String date, double activeEnergy)
    {
        activeEnergyTree.insert(date, activeEnergy);
    }

    public void addToHandWashingDurationTree(String date, double duration) {
        handWashingDurationTree.insert(date, duration);
    }
    //methods for hashmaps
    public void addStepCount(String date, int stepCount) 
    {
        stepCounts.put(date, stepCount);
    }

    public void addDistance(String date, double distance) 
    {
        distances.put(date, distance);
    }

    public void addCalories(String date, Double calorie) 
    {
        calories.put(date, calorie);
    }

    public void addRestingEnergy(String date, double energy) 
    {
        restingEnergy.put(date, energy);
    }
    public void addSoundLevels(String date, double level) 
    {
        soundLevels.put(date, level);
    }

    public void addFlightsClimbed(String date, int flights) 
    {
        flightsClimbed.put(date, flights);
    }

    public void addHandWashingSeconds(String date, double seconds) 
    {
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

// method for Average Step Count 
 
    public double AverageDailyStepCount ()
    {

         int totalSteps = 0;
         int totalDays = stepCounts.size();

        for (int steps : stepCounts.values())
        {
            totalSteps += steps;
        }
            return (double) totalSteps / totalDays; 
    }

    // Method for calculating average active energy burn
    public double averageDailyActiveEnergyBurn() 
    {
        double totalEnergy = 0;
        for (double energy : calories.values()) 
        {
        totalEnergy += energy;
        }
             return totalEnergy / calories.size();
}
    
}