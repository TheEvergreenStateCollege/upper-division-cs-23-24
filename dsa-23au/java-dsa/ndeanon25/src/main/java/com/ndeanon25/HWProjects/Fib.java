package com.ndeanon25.HWProjects;

/**
 * Hello world!
 *
 */
public class Fib
{
    public static void fib(int n){
     int num1 = 0;
     int num2 = 1;
     int counter = 0;
     
     while (counter < n){
        System.out.print (num1 + " ");
        int num3 = num2 + num1;
        num1 = num2;
        num2 = num3;
        counter = counter + 1;   

     }
    }

    public static void main( String[] args )
    {
        fib(25);
    }
}
