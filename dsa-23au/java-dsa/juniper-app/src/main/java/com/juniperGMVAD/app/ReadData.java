package com.juniperGMVAD.app;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Hello world!
 */
 
    
    public class ReadData 
    {
        //taking the data from the CSV file and putting it into a hashmap
        //Key is the name (+ year?) and the data is that countries MVA
          //  Map<String, CountryData> countryDataMap = new HashMap<>();
            
               try (FileInputStream fis = new FileInputStream("dsa-23au/java-dsa/juniper-app/target/classes/G20-GMVA.csv")) {
        BufferedInputStream bis = new BufferedInputStream(fis);
        byte[] buffer = bis.readAllBytes();
        String inputString = new String(buffer);
        String[] lines = inputString.split("\n");

        ArrayList<String> tokens = new ArrayList<String>();
        boolean inQuotes = false;

        //remove the quotes from the numbers in strings
        //strip out commas, ascii to double
        for (String line : lines) 
        {
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


        }
              

        for (Map.Entry<String, CountryData> entry : countryDataMap.entrySet()) 
        {
            System.out.println("Country: " + entry.getKey() + ", Data: " + entry.getValue());
        } 
    }
    }


