package com.Lohen-dsa.app;
import java.util.*;

public class Main {
    List<String> list;
    int[] array;
    PriorityQueue<Integer> priorityQueue;
    HashMap<Integer, String> hashMap;

    public Data() {
        // Create a List object
        listy = new ArrayList<>();
        listy.add("Element1");
        listy.add("Element2");

        // Create an Array object
        arrayy = new int[2];
        arrayy[0] = 1;
        arrayy[1] = 2;

        // Create a PriorityQueue object
        priorityQueuey = new PriorityQueue<>();
        priorityQueuey.add(1);
        priorityQueuey.add(2);

        // Create a HashMap object
        hashMapy = new HashMap<>();
        hashMapy.put(1, "Value1");
        hashMapy.put(2, "Value2");
    }

    public static void main(String[] args) {
        Main data = new Main();

        System.out.println("Listy: " + data.list);
        System.out.println("Arrayy: " + Arrays.toString(data.array));
        System.out.println("PriorityQueuey: " + data.priorityQueue);
        System.out.println("HashMapy: " + data.hashMap);
    }
}
