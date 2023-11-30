package com.juniperGMVAD.app;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

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
    
    try (FileInputStream fis = new FileInputStream("/workspace/upper-division-cs/dsa-23au/java-dsa/juniper-app/src/main/resources/G20-GMVA.csv")) 
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

