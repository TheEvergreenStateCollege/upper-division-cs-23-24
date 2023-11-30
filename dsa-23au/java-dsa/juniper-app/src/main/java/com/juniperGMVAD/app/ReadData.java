package com.juniperGMVAD.app;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Hello world!
 *
 */
public class ReadData 
{
    private Database database;

    public ReadData(Database database)
    {
        this.database = database;
    }
    public void processData() 
    {
    
    try (FileInputStream fis = new FileInputStream("/workspace/upper-division-cs/dsa-23au/java-dsa/juniper-app/src/main/resources/GMVA.csv")) 
    {
    BufferedInputStream bis = new BufferedInputStream(fis);
    byte[] buffer = bis.readAllBytes();
    String inputString = new String(buffer);
    String[] lines = inputString.split("\n");



    for (int i = 1; i < lines.length; i++) 
    {
        String[] tokens = lines[i].split(",");
        int year = 1960;
        ArrayList<Integer> years = new ArrayList<Integer>();
        ArrayList<Double> mva = new ArrayList<Double>();

        String name = tokens[0].trim();

        for (String line : lines[i].split(","))
        {
            years.add(year);
            year = year + 1;
        }

        for (int j = 5; j < tokens.length; j++)
        {
            mva.add(Double.parseDouble(tokens[j].trim()));
        }
        
        database.addCountryData(years, name, mva);


    }

    }

    catch(IOException ioe) 
    {
        System.err.println(ioe.toString());
    }

    }
}

