package com.juniperGMVAD.app;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class ReadData 
{
    public void processData() 
    {
    //taking the data from the CSV file and putting it into a hashmap
    //Key is the name (+ year?) and the data is that countries MVA
      //  Map<String, CountryData> countryDataMap = new HashMap<>();
    Database database = new Database();
    try (FileInputStream fis = new FileInputStream("dsa-23au/java-dsa/juniper-app/target/G20-GMVA.csv")) 
    {
    BufferedInputStream bis = new BufferedInputStream(fis);
    byte[] buffer = bis.readAllBytes();
    String inputString = new String(buffer);
    String[] lines = inputString.split("\n");



    for (int i = 1; i < lines.length; i++) 
    {
        String[] tokens = lines[i].split(",");
        
        int year = Integer.parseInt(tokens[0].trim());
        String name = tokens[1].trim();
        double mva = Double.parseDouble(tokens[2].trim());
        double gmva = Double.parseDouble(tokens[3].trim());
        
        database.addCountryData(year, name, mva, gmva);


    }

    }

    catch(IOException ioe) 
    {
        System.err.println(ioe.toString());
    }

    }
}

