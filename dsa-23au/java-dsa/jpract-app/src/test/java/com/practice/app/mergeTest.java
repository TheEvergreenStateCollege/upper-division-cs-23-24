package test.java.com.practice.app;


import java.util.Random;

import main.java.com.practice.app.mergeSort;

public class mergeTest 
{
    public static void main(String[] args)
{
    int[] num = new int[10];
    Random random = new Random();

    int i;
    int j;


    for(i = 0; i < num.length; i++)
    {
        num[i] = random.nextInt(100000);
        System.out.println(num[i]);

    }

    mergeSort MergeSort = new mergeSort();
    MergeSort.sort(num);

    for(j = 0; j < num.length; j++)
    {
        System.out.println(num[j]);
    }
}
}

