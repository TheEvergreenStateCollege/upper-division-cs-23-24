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

    public void readMVAIntoDatabase(String csv_filepath) 
    {
    List<List<String>> tokenized = readAndTokenizeCSV(String csv_filepath);

            for (int i = 6; i < tokenized.size(); i++) 
            {
                int year = tokenized.get(5).get(0);
                double mva;
        
                String countryName = tokenized.get(i).get(0);
                String countryCode = tokenized.get(i).get(1);
                String indicatorName = tokenized.get(i).get(2);
                String indicatorCode = tokenized.get(i).get(3);
        
                for (String line : tokenized.get(i).size())
                {
                 y++
                 years = years + 1;
                 mva = Double.parseDouble(tokenized.get(i).get(5));
            }

    }
}
    catch(IOException ioe) 
    {
        System.err.println(ioe.toString());
    }

    }


