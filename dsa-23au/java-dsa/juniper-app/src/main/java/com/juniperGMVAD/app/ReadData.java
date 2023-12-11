package com.juniperGMVAD.app;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.juniperGMVAD.app.Enum.Country;
import com.juniperGMVAD.app.Enum.Indicator;
import com.juniperGMVAD.app.YearValue.YearValue;
import com.juniperGMVAD.app.Database.*;

public class ReadData
{
    private Database database;


    public ReadData(Database database)
    {
        this.database = database;
    }

    public void loadAndProcess(String csv_filepath) {
        List<List<String>> tokenized = readAndTokenizeCSV(csv_filepath);
        readMVAIntoDatabase(tokenized);
    }
    public void loadAndProcess2(String csv_filepath) {
        List<List<String>> tokenized = readAndTokenizeCSV(csv_filepath);
        readNNIIntoDatabase(tokenized);
    }

    public void readMVAIntoDatabase(List<List<String>> tokenized) {
        for (List<String> line : tokenized.subList(5, tokenized.size())) {
            String countryCode = line.get(1);

            int year = 1960;
            List<YearValue> yearValues = new ArrayList<YearValue>();
            for (String value : line.subList(4, line.size())) {
                if (!value.isEmpty()) {
                    yearValues.add(new YearValue(year, Double.parseDouble(value)));
                } else {
                    yearValues.add(new YearValue(year, -1d));
                }
                year++;
            }

            Country country = Country.valueOf(countryCode);

            for (YearValue yearValue : yearValues) {
                database.setYearValue(country, Indicator.MVA, yearValue.year, yearValue.value);
           
             }
             }
        }
        public void readNNIIntoDatabase(List<List<String>> tokenized) {
            for (List<String> line : tokenized.subList(5, tokenized.size())) {
                String countryCode = line.get(1);
    
                int year = 1960;
                List<YearValue> yearValues = new ArrayList<YearValue>();
                for (String value : line.subList(4, line.size())) {
                    if (!value.isEmpty()) {
                        yearValues.add(new YearValue(year, Double.parseDouble(value)));
                    } else {
                        yearValues.add(new YearValue(year, -1d));
                    }
                    year++;
                }
    
                Country country = Country.valueOf(countryCode);
    
                for (YearValue yearValue : yearValues) {
                    database.setYearValue(country, Indicator.MVA, yearValue.year, yearValue.value);
                }
            }
    }

    /*private void addCountryDataEntry(CountryDataEntry entry) {
        List<YearValue> values = entry.allYearValues();

        for (YearValue yv : values) {
            Database.setYearValue(entry.getCountry(), entry.getIndicator(), yv.year, yv.value);
        } 
    }*/
    
    // Each list of strings represents one line of CSV file
    private List<List<String>> readAndTokenizeCSV(String csv_filepath) {
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


    /*public void readMVAIntoDatabase(String csv_filepath) {
        List<List<String>> gmvaTokens = readAndTokenizeCSV(csv_filepath);
        for (int i = 6; i < gmvaTokens.size(); i++) {
            int year = Integer.parseInt(gmvaTokens.get(5).get(5));
            double mva;

            String countryName = gmvaTokens.get(i).get(0);
            String countryCode = gmvaTokens.get(i).get(1);
            String indicatorName = gmvaTokens.get(i).get(2);
            String indicatorCode = gmvaTokens.get(i).get(3);

            for (int j = 5; j < gmvaTokens.get(i).size(); j++) {
                year = year + 1;
                mva = Double.parseDouble(gmvaTokens.get(i).get(j));
                countryDataEntry.addYearValue(year, mva);
            }

            countryDataEntry.setCountryName(countryName);
            countryDataEntry.setCountryCode(countryCode);
            countryDataEntry.setIndicatorCode(indicatorCode);
            countryDataEntry.setIndicatorName(indicatorName);

        }
    }*/


    /*
    public void readNNIPCIntoDatabase(String csv_filepath)
    {
        List<List<String>> nniTokens = readAndTokenizeCSV(csv_filepath);

        for (int i = 6; i < nniTokens.size(); i++) 
        {
            int year = Integer.parseInt(nniTokens.get(5).get(5));
            double nni;
    
            String countryName = nniTokens.get(i).get(0);
            String countryCode = nniTokens.get(i).get(1);
            String indicatorName = nniTokens.get(i).get(2);
            String indicatorCode = nniTokens.get(i).get(3);
    
            for (int j = 5; j < nniTokens.get(i).size(); j++)
            {
             year = year + 1;
             nni = Double.parseDouble(nniTokens.get(i).get(j));
            }


        }
    }
    */


}



    /*public void readIncomeIntoDatabase(String csv_filepath) {
        catch(IOException ioe) 
        System.err.println(ioe.toString());
    {*/
