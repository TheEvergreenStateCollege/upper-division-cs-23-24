package com.devincompany.app;
import java.util.ArrayList;

public class DataOperations {
    public int HighestMessages(ArrayList<Integer> messagesPerDay){
        int highestSoFar = 0;
        for (int i = 0; i < messagesPerDay.size();i++){
            if (messagesPerDay.get(i) > highestSoFar){
                highestSoFar = messagesPerDay.get(i);
            }
        }
        return highestSoFar;
    }
}
