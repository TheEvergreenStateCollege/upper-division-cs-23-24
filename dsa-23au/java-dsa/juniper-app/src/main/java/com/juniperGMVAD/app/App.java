package com.juniperGMVAD.app;
import java.util.List;
/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        System.out.println(args[0]);

        if (args[0].equals("top5mva")) {
            calculateTopMVA();
        } else if (args[0].equals("retrieve")) {
            retrieveTopCountries();
        }
    }

    public static List<CountryData> calculateTopMVA ()
    {

    }

 }











/*
Consider complextiy & big O shit
-Calculate the percent value of gmva
-Create a maxheap of the countries by gmva
	->first in first out?
-take top 5 countries and put them into a hashmap
-do same with 5 bottom
-create something that represents the change in the top 5 countries and bottom 5 countries
-Compare this change in the top 5 countries for wages and bottom 5 and top 5 countries for interest rate and bottom 5
-What countries had the greatest increase? Decrease?
*\

