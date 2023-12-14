package com.devincompany.app;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.PriorityQueue;

public class DataOperations
{
    public static int highestMessages(ArrayList<Integer> messagesPerDay)
    {
        int highestSoFar = 0;
        for (int i = 0; i < messagesPerDay.size();i++){
            if (messagesPerDay.get(i) > highestSoFar)
            {
                highestSoFar = messagesPerDay.get(i);
            }
        }
        return highestSoFar;
    }
    public static ArrayList<Integer> sameCountDays(ArrayList<Integer> messagesPerDay)
    {
        HashMap<Integer,String> newMap = new HashMap<Integer,String>();
        ArrayList<Integer> duplicates = new ArrayList<Integer>();
        for (int i = 0; i < messagesPerDay.size();i++)
        {
            if (newMap.get(messagesPerDay.get(i)) != null)
            {
                duplicates.add(Integer.parseInt(newMap.get(i)));
                Scanner stringScanner = new Scanner(newMap.get(i));
                newMap.put(messagesPerDay.get(i),Integer.toString((int)(stringScanner.nextInt()+1)) + messagesPerDay.get(i));
            }
            
        }
        return duplicates;
    }
    public static ArrayList<Integer> topDays(ArrayList<Integer> messagesPerDay, int x)
    {
        PriorityQueue<Integer> SortedDays = new PriorityQueue<Integer>();
        ArrayList<Integer> topDays = new ArrayList<Integer>();
        for (int i = 0; i < messagesPerDay.size(); i++)
        {
            SortedDays.add(messagesPerDay.get(i));
        }
        for (int i = 0; i < x; i++)
        {
            topDays.add(SortedDays.poll());
        }
        return topDays;
    }
    public static ArrayList<ArrayList<Integer>> notableDays(ArrayList<Integer> messagesPerDay)
    {
        ArrayList<ArrayList<Integer>> notableDays = new ArrayList<ArrayList<Integer>>();
        for (int i = 1; i < messagesPerDay.size(); i++)
        {
            if (messagesPerDay.get(i)/messagesPerDay.get(i-1) > 0.75)
            {
                notableDays.get(0).add(i);
                notableDays.get(1).add(messagesPerDay.get(i)/messagesPerDay.get(i-1));

            }
            if (messagesPerDay.get(i)/messagesPerDay.get(i-1) < 1.4)
            {
                notableDays.get(0).add(i);
                notableDays.get(1).add(messagesPerDay.get(i)/messagesPerDay.get(i-1));
            }
        }
        return notableDays;
    }
    public static int[][] coolIndexMatrix(ArrayList<Integer> messagesPerDay)
    {
        PriorityQueue<Integer> SortedDays = new PriorityQueue<Integer>();
        int[][] matrix = new int[(int)Math.ceil(Math.sqrt(messagesPerDay.size()))][(int)Math.ceil(Math.sqrt(messagesPerDay.size()))];
        for (int i = 0; i < messagesPerDay.size(); i++)
        {
            SortedDays.add(i);
        }
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length && SortedDays.isEmpty() != true; j++)
            {
                matrix[i][j] = messagesPerDay.get(SortedDays.poll());
            }
        }
        return matrix;
    }
    public static int[][] coolIndexMatrixTwo(ArrayList<Integer> messagesPerDay)
    {
        PriorityQueue<Integer> SortedDays = new PriorityQueue<Integer>();
        int[][] matrix = new int[(int)Math.ceil(Math.sqrt(messagesPerDay.size()))][(int)Math.ceil(Math.sqrt(messagesPerDay.size()))];
        for (int i = 0; i < messagesPerDay.size(); i++)
        {
            SortedDays.add(i);
        }
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < i; j++)
            {
                matrix[j][i-j] = messagesPerDay.get(SortedDays.poll());
            }
        }
        return matrix;
    }
}
