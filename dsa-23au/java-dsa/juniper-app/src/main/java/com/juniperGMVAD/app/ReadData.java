package com.juniperGMVAD.app;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Hello world!
 *
 
    
    public class ReadData {
        //taking the data from the CSV file and putting it into a hashmap
        //Key is the name (+ year?) and the data is that countries MVA
          //  Map<String, CountryData> countryDataMap = new HashMap<>();
            
            try (FileInputStream fis = new FileInputStream("G20-GMVA.csv")) {
                BufferedInputStream bis = new BufferedInputStream(fis);
                byte[] buffer = bis.readAllBytes();
                String inputString = new String(buffer);
                String[] lines = inputString.split("\n");
    
                for (String line : lines) {
                    String[] tokens = line.split(",");
                    if (tokens.length == 0 || line.length() == 0) {
                        continue;
                    }

                    for (String token : tokens)
                    {
                        System.out.println(token);
                    }
                //   CountryData countryData = new CountryData(tokens[0], tokens[1], );
    
                 
                // countryDataMap.put(countryData.getName(), countryData);
                }
            } catch (IOException ioe) {
                System.err.println(ioe.toString());
            }
    
           

            /*for (Map.Entry<String, CountryData> entry : countryDataMap.entrySet()) {
                System.out.println("Country: " + entry.getKey() + ", Data: " + entry.getValue());
            } 
    }

*/

