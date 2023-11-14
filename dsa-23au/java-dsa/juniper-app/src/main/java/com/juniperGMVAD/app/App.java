package com.juniperGMVAD.app;
import java.util.List;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {

    try (FileInputStream fis = new FileInputStream("dsa-23au/java-dsa/juniper-app/target/classes/G20-GMVA.csv")) {
        BufferedInputStream bis = new BufferedInputStream(fis);
        byte[] buffer = bis.readAllBytes();
        String inputString = new String(buffer);
        String[] lines = inputString.split("\n");

        ArrayList<String> tokens = new ArrayList<String>();
        boolean inQuotes = false;

        //remove the quotes from the numbers in strings
        //strip out commas, ascii to double
        for (String line : lines) {
            int start = 0;
            for (int i = 0; i < line.length(); i++)
            {
                if (line.charAt(i) == '"')
                {
                    if (inQuotes == true)
                    {
                        inQuotes = false;
                        continue;
                    }
                    inQuotes = true;
                }
                if (line.charAt(i) == ',' && inQuotes == false)
                {
                    tokens.add(line.substring(start, i));
                    start = i + 1;
                }

            }
            for (String token : tokens)
            {
                System.out.println(token);
            }


    
            /* 
            String[] tokens = line.split(",");
            if (tokens.length == 0 || line.length() == 0) {
                continue;
            }

            for (String token : tokens)
            {
                System.out.println(token);
            }*/
        //   CountryData countryData = new CountryData(tokens[0], tokens[1], );

         
        // countryDataMap.put(countryData.getName(), countryData);
        }
    } 
    
    
    catch (IOException ioe) {
        System.err.println(ioe.toString());
    }
}

/*  public static void main( String[] args )
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
*/ 
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
*/

