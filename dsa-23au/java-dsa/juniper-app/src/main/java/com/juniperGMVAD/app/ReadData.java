package com.juniperGMVAD.app;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    // Each list of strings represents one line of CSV file
    public List<List<String>> readAndTokenizeCSV(String csv_filepath) {
        BufferedReader reader;
        List<List<String>> tokenized = new ArrayList<List<String>>();
        String pattern = "\"([^\"]*)\",";
        Pattern reg = Pattern.compile(pattern);
        
        try {
		    reader = new BufferedReader(new FileReader(csv_filepath));
			String line = reader.readLine();

			while (line != null) {
                List<String> split = new ArrayList<String>();

                Matcher ma = reg.matcher(line);
                ma.groupCount();

                while (ma.find()) {
                    split.add(ma.group(1));
                }

                tokenized.add(split);
				line = reader.readLine();
			}

			reader.close();
            return tokenized;
		} catch (IOException e) {
			e.printStackTrace();
            return null;
		}
    }

    /*public readMVAIntoDatabase(String csv_filepath) {
        
    }*/

    /*public List<CountryData> readCountryData(String csv_filepath) {
        try (FileInputStream fis = new FileInputStream("/workspace/upper-division-cs/dsa-23au/java-dsa/juniper-app/src/main/resources/GMVA.csv")) {
            BufferedInputStream bis = new BufferedInputStream(fis);
            byte[] buffer = bis.readAllBytes();
            String inputString = new String(buffer);
            String[] lines = inputString.split("\n");
        } catch (IOException ioe) {

        }
    }*/

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
    }

    }

    catch(IOException ioe) 
    {
        System.err.println(ioe.toString());
    }

    }
}

