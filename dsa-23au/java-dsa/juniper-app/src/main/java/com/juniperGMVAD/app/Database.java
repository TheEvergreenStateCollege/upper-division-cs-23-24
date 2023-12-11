package com.juniperGMVAD.app;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import com.juniperGMVAD.app.Enum.Country;
import com.juniperGMVAD.app.Enum.Indicator;
import com.juniperGMVAD.app.YearValue.YearValue;
import com.juniperGMVAD.app.HashMap.*;
import com.juniperGMVAD.app.HashMap.HashMap;

import static java.util.stream.Collectors.averagingDouble;

import java.time.Instant;
import java.util.*;
import com.juniperGMVAD.app.BinaryHeap.BinaryHeap;


public class Database {
    HashMap<Country, CountryData> countryData = new HashMap<Country, CountryData>();

    /**
     * Sets yearly value of indicator for given country
     * @param country
     * @param indicator
     * @param year
     * @param value
     * @return Value of previous value, null if yearly value did not exist
     */
    public Double setYearValue(Country country, Indicator indicator, int year, double value) {
        // Is country already stored in database?
        CountryData targetCountry = countryData.get(country);

        // If country is not stored in database
        if (targetCountry == null) {
            // Create country
            targetCountry = new CountryData(country, this);
            // Add country to database
            countryData.put(country, targetCountry);
        }

        // Set new value and return old value (if exists, otherwise null)
        Double oldValue = targetCountry.setValue(indicator, year, value);
        return oldValue;
    }

    /*public List<Pair<Country, Double>> getRanking(Indicator indicator, int year) {
        List<Pair<Country, Double>> values = new ArrayList<Pair<Country, Double>>();
        List<CountryData> countries = countryData.valuesAsListDebug();

        for (CountryData country : countries) {
            values.add(new Pair<Country, Double>(country.country, country.getValue(indicator, year)));
        }

        Ranking rank = new Ranking();

        for (Pair<Country, Double> value : values) {

        }
    }*/

    /**
     * Removes a yearly value of an indicator for a specified country
     * @param country
     * @param indicator
     * @param year
     * @return Value of old year value if it existed, otherwise this is null
     */
    public void removeYearValue(Country country, Indicator indicator, int year) {
        //TODO: implement returning old year value
        CountryData targetCountry = countryData.get(country);

        if (targetCountry == null) {
            return;
        }

        targetCountry.removeValue(indicator, year);
    }

    /**
     * Gets country's value for a specified indicator for a specified year
     * @param country
     * @param indicator
     * @param year
     * @return The value, null if non-existent
     */
    public Double getYearValue(Country country, Indicator indicator, int year) {
        CountryData targetCountry = countryData.get(country);

        // Country does not exist
        if (targetCountry == null) {
            return null;
        }

        return targetCountry.getValue(indicator, year);
    }

    /**
     * Returns a list of all indicator YearValues for specified country
     * @param country
     * @param indicator
     * @return
     */
    public List<YearValue> yearValuesAsList(Country country, Indicator indicator) {
        // Is country already stored in database?
        CountryData targetCountry = countryData.get(country);

        // If country is not stored in database
        if (targetCountry == null) {
            return null;
        }

        // Otherwise, return list of values for indicator (returns null if not tracked)
        return targetCountry.valuesAsList(indicator);
    }

    /**
     * Returns a list of all indicator YearValues for specified country within range, inclusive
     * @param country
     * @param indicator
     * @return
     */
    public List<YearValue> yearValuesAsList(Country country, Indicator indicator, int startYear, int endYear) {
        // Is country already stored in database?
        CountryData targetCountry = countryData.get(country);

        // If country is not stored in database
        if (targetCountry == null) {
            return null;
        }

        // Otherwise, return list of values for indicator (returns null if not tracked)
        return targetCountry.valuesAsList(indicator, startYear, endYear);
    }

    /**
     * Checks if indicator is tracked for specified country
     * @param country
     * @param indicator
     * @return
     */
    public boolean isIndicatorTracked(Country country, Indicator indicator) {
        CountryData targetCountry = countryData.get(country);

        if (targetCountry == null) {
            return false;
        }

        if (targetCountry.isIndicatorTracked(indicator)) {
            return true;
        }

        return false;
    }

    /**
     * Returns a list of all tracked indicators for a specified country
     * @param country
     * @return List of indicators
     */
    /*public List<Indicator> indicatorsTracked(Country country) {
        return new ArrayList<Indicator>();
    }*/

    public Instant lastUpdated(Country country, Indicator indicator, int year) {
        CountryData updatedCountry = countryData.get(country); // get correct country data object for country enum
        return updatedCountry.lastUpdated(indicator, year);
    }

    public void printLastUpdatedDebug(Country country) {
        CountryData targetCountry = countryData.get(country);

        if (targetCountry == null) {
            return;
        }

        targetCountry.printLastUpdatedDebug();
    }
  public double getTop5ContCorrNNIMVA(int startYear, int endYear) {

        Comparator<Country> gmvaComparator = Comparator.comparingDouble(country -> {
            return getYearValue(country, Indicator.PGMVA, endYear);
        });

        BinaryHeap<Country> gmvaHeap = new BinaryHeap<>(gmvaComparator);

        
        for (Map.Entry<Country, CountryData> entry : countryData.entrySet()) {
            gmvaHeap.insert(entry.getKey());
        }


        List<YearValue> nniValues = new ArrayList<>();
        List<YearValue> gmvapValues = new ArrayList<>();
        List<Float> corrlist = new ArrayList<>();
        for (int i = 0; i < 5 && !gmvaHeap.isEmpty(); i++) {
            Country country = gmvaHeap.deleteMax(); 

          
            List<YearValue> nniYearValues = yearValuesAsList(country, Indicator.NNIPC, startYear, endYear);
            List<YearValue> gmvapYearValues = yearValuesAsList(country, Indicator.PGMVA, startYear, endYear);

      
            nniValues.addAll(nniYearValues);
            gmvapValues.addAll(gmvapYearValues);
            nniValues.sort(Comparator.comparingInt(YearValue::getYear));
            gmvapValues.sort(Comparator.comparingInt(YearValue::getYear));
    
            double[] nniValuesArr = valuesAsArray(nniValues);
            double[] gmvapValuesArr = valuesAsArray(gmvapValues); 
            corrlist.add(GetValueCorrelation.getCorrTwoValues(nniValuesArr, gmvapValuesArr));
            
        }
        double avgcorr = calculateAverage(corrlist);
        return avgcorr;
        
    }
    private double calculateAverage(List <Float> marks) {
        return marks.stream()
                    .mapToDouble(d -> d)
                    .average()
                    .orElse(0.0);
    }

    public double[] valuesAsArray(List<YearValue> yearValues) {
        if (yearValues == null) {
            return new double[0]; 
        }
    
        double[] valuesArray = new double[yearValues.size()];
    
        for (int i = 0; i < yearValues.size(); i++) {
            valuesArray[i] = yearValues.get(i).value;
        }
    
        return valuesArray;
    }

    
}

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


