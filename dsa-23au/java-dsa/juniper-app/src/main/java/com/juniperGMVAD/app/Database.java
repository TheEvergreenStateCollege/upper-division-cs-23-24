package com.juniperGMVAD.app;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.*;

public class Database {
    HashMap<String,HashMap<Integer,CountryData>> data;

    Database() {
        this.data = new HashMap<>();
    }

    public boolean addCountryData(int year, String name, double mva, double gmva) {
        CountryData newCountry = new CountryData(year, name, mva, gmva);

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

    public List<String> getTop5() {
        List<String> sortCountries = new ArrayList<String>();

        int firstYear = 2016;
        int lastYear = 2022;

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
        topCountries = sortCountries.subList(0, Math.min(sortCountries.size(), 5));

        
        System.out.println(Arrays.toString(topCountries.toArray()));
        return topCountries;
    }

}