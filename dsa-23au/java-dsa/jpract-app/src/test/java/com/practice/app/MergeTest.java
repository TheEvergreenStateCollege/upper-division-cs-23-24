package com.practice.app;

import java.util.Random;

public class MergeTest 
{

    int[] num = new int[10];
    Random random = new Random();

    int i;
  
    for(i = 0; i < num.length; i++)
    {
        num[i] = random.nextInt(100000);
        System.out.println(num[i]);

    }
}

