package com.juniperGMVAD.app;

import java.util.HashMap;

public class Database {
    HashMap<String,HashMap<Integer,CountryData>> data;

    Database() {

    }

    public boolean addCountryData(String name, int year, double mva, double gmva) {
        CountryData newCountry = new CountryData(name, year, mva, gmva);

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
}