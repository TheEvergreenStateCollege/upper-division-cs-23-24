package com.devincompany.app;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DataOperations {
    public static int highestMessages(ArrayList<Integer> messagesPerDay){
        int highestSoFar = 0;
        for (int i = 0; i < messagesPerDay.size();i++){
            if (messagesPerDay.get(i) > highestSoFar){
                highestSoFar = messagesPerDay.get(i);
            }
        }
        return highestSoFar;
    }
    public static ArrayList<Integer> sameCountDays(ArrayList<Integer> messagesPerDay){
        HashMap<Integer,String> newMap = new HashMap<Integer,String>();
        ArrayList<Integer> duplicates = new ArrayList<Integer>();
        for (int i = 0; i < messagesPerDay.size();i++){
            if (newMap.get(messagesPerDay.get(i)) != null) {
                duplicates.add(Integer.parseInt(newMap.get(i)));
                Scanner stringScanner = new Scanner(newMap.get(i));
                newMap.put(messagesPerDay.get(i),Integer.toString((int)(stringScanner.nextInt()+1)) + messagesPerDay.get(i));
            }
            
        }
        return duplicates;
    }
    //public static ArrayList<Integer> NotableDays(ArrayList<Integer> messagesPerDay){

    //}
}
