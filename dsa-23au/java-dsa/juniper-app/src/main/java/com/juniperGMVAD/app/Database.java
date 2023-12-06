package com.juniperGMVAD.app;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.*;

public class Database {
    List<CountryData> countryData = new ArrayList<CountryData>();
    /*HashMap<String,HashMap<Integer,CountryData>> data;

    Database() {
        this.data = new HashMap<>();
    }

    public boolean addCountryData(ArrayList<Integer> year, String name, ArrayList<Double> mva) {
        CountryData newCountry = new CountryData(year, name, mva);

        if (!data.containsKey(name)) {
            data.put(name, new HashMap<>());
        }

        // Checks if data for given year already exists
        if (data.get(name).containsKey(year)) {
            return false;
        }

        data.get(name).put(year, newCountry);
        return true;
    }

    public double getMVA(String name, int year) {
        return data.get(name).get(year).mva;
    }

    public double getMVAPerGMVA(String name, int year) {
        return data.get(name).get(year).percentOfGMVA;
    }

    public double getMVAPercentChange(String name, int firstYear, int lastYear) {
        if (data.get(name).containsKey(firstYear) && data.get(name).containsKey(lastYear))
        {
            CountryData dat1 = data.get(name).get(firstYear);
            CountryData dat2 = data.get(name).get(lastYear);

            double change = dat2.percentOfGMVA - dat1.percentOfGMVA;
            return change;
        } else {
            return Double.POSITIVE_INFINITY;
        }
    }

    public List<String> getTop(int top, int firstYear, int lastYear) 
    {
        List<String> sortCountries = new ArrayList<String>();


        for (Map.Entry<String, HashMap<Integer, CountryData>> Entry : data.entrySet())
        {
            String countName = Entry.getKey();
            double percChange = getMVAPercentChange(countName, firstYear, lastYear);

            if(!Double.isInfinite(percChange))
            {
                sortCountries.add(countName + ": " + percChange + "%");
            }
        }

        Collections.sort(sortCountries, (s1, s2) ->
        {
            double percChange1 = Double.parseDouble(s1.split(": ")[1].replace('%', ' '));
            double percChange2 = Double.parseDouble(s2.split(": ")[1].replace('%', ' '));

            return Double.compare(percChange2, percChange1);
        });

        List<String>topCountries = new ArrayList<String>();
        topCountries = sortCountries.subList(0, Math.min(sortCountries.size(), top));

        for (int j = 0; j < topCountries.size(); j++)
        {
            String[] x = topCountries.get(j).split(":");
            String cName = x[0];
            System.out.println(cName);
            System.out.println(firstYear + " percent: " + getMVAPerGMVA(cName, firstYear) + "%");
            System.out.println(lastYear + " percent: " + getMVAPerGMVA(cName, lastYear) + "%");
            System.out.println("Percent Change: " + x[1]);


        }
        return topCountries;
    }

    public List<String> top5PerYear(int nYear)
    {
        List<String> topPerYear = new ArrayList<String>();
        for (Map.Entry<String, HashMap<Integer, CountryData>> Entry : data.entrySet())
        {
            String newName = Entry.getKey();
            double percCount = getMVAPerGMVA(newName, nYear);

            if(!Double.isInfinite(percCount))
            {
                topPerYear.add(newName + ": " + percCount + "%");

            }
        }

            Collections.sort(topPerYear, (s1, s2) ->
            {
                double perc1 = Double.parseDouble(s1.split(": ")[1].replace('%', ' '));
                double perc2 = Double.parseDouble(s2.split(": ")[1].replace('%', ' '));
    
                return Double.compare(perc2, perc1);
            });
            topPerYear = topPerYear.subList(0, Math.min(topPerYear.size(), 10));

            for (int i = 0; i < topPerYear.size(); i++)
            {
                System.out.println(topPerYear.get(i));
    
            }
            return topPerYear;

        }
*/
}
