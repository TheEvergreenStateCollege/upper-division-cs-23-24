package com.pswishcorp.app;

import java.util.HashMap;
import java.util.Map;

public class MapHashMap {
        public static void main(String[] args) {
        Map<String, Map<String, Integer>> driverNameToDriverData = new HashMap<>();
        // Create a dictionary of dictionaries
        
        Map<String, Integer> innerDict1 = new HashMap<>();
        innerDict1.put("TravelTime", 22);
        innerDict1.put("MilesTravelled", 11);

        Map<String, Integer> innerDict2 = new HashMap<>();
        innerDict2.put("TravelTime", 100);
        innerDict2.put("MilesTravelled", 200);

        driverNameToDriverData.put("driver1", innerDict1);
        driverNameToDriverData.put("driver2", innerDict2);
        /// WIP
        int value1 = driverNameToDriverData.get("dict1").get("key1");
        
    }
    
}

/*
Data structure model selected(As of 10/17)
Example from: 
    public static void main(String[] args) {
        // Create a dictionary of dictionaries
        Map<String, Map<String, Integer>> dictOfDicts = new HashMap<>(); 
        // Add data to the dictionary of dictionaries
        Map<String, Integer> innerDict1 = new HashMap<>();
        innerDict1.put("key1", 42);
        innerDict1.put("key2", 73);

        Map<String, Integer> innerDict2 = new HashMap<>();
        innerDict2.put("keyA", 100);
        innerDict2.put("keyB", 200);

        dictOfDicts.put("dict1", innerDict1);
        dictOfDicts.put("dict2", innerDict2);

        // Access values in the dictionary of dictionaries
        int value1 = dictOfDicts.get("dict1").get("key1");
        int value2 = dictOfDicts.get("dict2").get("keyA");

        System.out.println("Value in dict1: " + value1);
        System.out.println("Value in dict2: " + value2);
    }
*/
