package com.juniperGMVAD.app;

import java.time.Instant;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import com.juniperGMVAD.app.BinaryTree.BinaryTree;
import com.juniperGMVAD.app.HashMap.HashMap;

import com.juniperGMVAD.app.BinaryTree.BinaryTree;
import com.juniperGMVAD.app.HashMap.HashMap;

public class CountryData {
    private Country country;
    private Database database;
    private HashMap<Indicator, BinaryTree<YearValue>> values;
    private HashMap<Pair<Indicator, Integer>, Instant> lastUpdated; // Key: Tuple of Indicator enum and Year; Value: Timestamp of last update

    public CountryData(Country country, Database database) {
        this.country  = country;
        this.database = database;
        this.values      = new HashMap<Indicator, BinaryTree<YearValue>>();
        this.lastUpdated = new HashMap<Pair<Indicator, Integer>, Instant>();
    }

    /**
     * Set's a value for an indicator and a given year, overwriting existing value. Cannot overwrite calculated values
     * @param indicator Indicator type
     * @param year Year value is given
     * @param value Value of given indicator for given year
     * @return Overwritten value, if it exists
     */
    public Double setValue(Indicator indicator, int year, Double value) {
        // Invariant: user cannot manually set values of calculated indicators
        if (indicator.isCalculated) {
            System.err.println("Error: cannot add year and value of calculated indicator type " + indicator.indicatorName);
            return null;
        }

        return setAnyValue(indicator, year, value);
    }

    /**
     * Gets value of year and indicator. Calculates this value if necessary
     * @param indicator
     * @param year
     * @return Value of indicator for year
     */
    public Double getValue(Indicator indicator, int year) {
        // Case 1: indicator is a calculated indicator. Calculate value for year (if necessary), store, and return
        if (indicator.isCalculated) {
            return calculateAndUpdate(indicator, year);
        }

        // Case 2: indicator is not calculated, null if nonexistent
        return getValueWithoutUpdate(indicator, year);
    }

    public void removeValue(Indicator indicator, int year) {
        // TODO: fix if-statement hacky solution, implement real return value
        if (indicator.isCalculated == true) {
            BinaryTree<YearValue> indicatorValues = values.get(indicator);
            indicatorValues.delete(new YearValue(year, 0d));
            lastUpdated.remove(new Pair<Indicator, Integer>(indicator, year));
            return;
        }

        if (indicator == Indicator.MVA) {
            BinaryTree<YearValue> indicatorValues = values.get(indicator);
            BinaryTree<YearValue> calcIndicatorValues = values.get(Indicator.PGMVA);
            indicatorValues.delete(new YearValue(year, 0d));
            calcIndicatorValues.delete(new YearValue(year, 0d));
            lastUpdated.remove(new Pair<Indicator, Integer>(indicator, year));
            lastUpdated.remove(new Pair<Indicator, Integer>(Indicator.PGMVA, year));
            return;
        }
    }

    public List<YearValue> valuesAsList(Indicator indicator) {
        BinaryTree<YearValue> indicatorValues = values.get(indicator);

        if (indicatorValues == null) {
            return null;
        }

        if (indicator.isCalculated == true) {
            Indicator basis = indicator.indicatorBasis;
            List<YearValue> basisValues = valuesAsList(basis);

            for (YearValue yv : basisValues) {
                calculateAndUpdate(indicator, yv.year);
            }
        }

        return indicatorValues.asList();
    }

    /**
     * Overloaded method to return yearly values for an indicator over a range of years. Skips missing yearly values
     * @param indicator
     * @param startYear
     * @param endYear
     * @return List of yearly values as YearValues, null if indicator is not tracked or start and end do not constitute a range of at least one
     */
    public List<YearValue> valuesAsList(Indicator indicator, int startYear, int endYear) {
        if (startYear > endYear || endYear < startYear) {
            return null;
        }

        if (indicator.isCalculated == true) {
            Indicator basis = indicator.indicatorBasis;
            List<YearValue> basisValues = valuesAsList(basis, startYear, endYear);

            for (YearValue yv : basisValues) {
                calculateAndUpdate(indicator, yv.year);
            }

            // Knowing that these bases exist, check if they have been updated more recently than calculated indicator value
            if (areBasesNewer(indicator, year)) {
                // If so, calculate new value for year, store it, and return it
                Double percentile = basisValue / percentileBasisValue;
                setAnyValue(indicator, year, percentile);
                updateTimestamp(indicator, year);
                return percentile;
            }

            // If they are not newer, return the previously stored value
            return oldCalculated;    
        }

        BinaryTree<YearValue> indicatorValues = values.get(indicator);

        if (indicatorValues == null) {
            return null;
        }

        return indicatorValues.rangeAsList(new YearValue(startYear, 0d), new YearValue(endYear, 0d));
    }

    public Instant lastUpdated(Indicator indicator, int year) {
        return lastUpdated.get(new Pair<Indicator, Integer>(indicator, year));
    }

    public void printLastUpdatedDebug() {
        lastUpdated.printHashMapDebug();
    }

    public boolean isIndicatorTracked(Indicator indicator) {
        if (values.get(indicator) == null) {
            return false;
        }

        return true;
    }

    private Double calculateAndUpdate(Indicator indicator, int year) {
        Indicator base = indicator.indicatorBasis; // Base indicator type on which this indicator bases its value

        if (indicator.isPercentile) {
            // Here we assume that the percentile indicator is the same as the base
            Double oldCalculated        = getValueWithoutUpdate(indicator, year);
            Double percentileBasisValue = database.getYearValue(indicator.percentileBasis, base, year); // this year's value for percentile basis
            Double basisValue           = getValueWithoutUpdate(base, year); // this object's value for this year, given indicator

            if (percentileBasisValue == null || basisValue == null) {
                //TODO: error handling; bases does not exist
                if (oldCalculated == null) {
                    System.err.println("Cannot calculate " + indicator.indicatorName + " for year " + year + ", bases do not exist");
                    return null;
                }
                return oldCalculated;
            }

            // If indicator has not been previously calculated
            if (oldCalculated == null) {
                // If so, calculate new value for year, store it, and return it
                Double percentile = basisValue / percentileBasisValue;
                setAnyValue(indicator, year, percentile);
                updateTimestamp(indicator, year);
                return percentile;
            }

            // Knowing that these bases exist and value has been calculated, 
            // check if they have been updated more recently than calculated indicator value
            if (areBasesNewer(indicator, year)) {
                // If so, calculate new value for year, store it, and return it
                Double percentile = basisValue / percentileBasisValue;
                setAnyValue(indicator, year, percentile);
                updateTimestamp(indicator, year);
                return percentile;
            }

            // If they are not newer, return the previously stored value
            return oldCalculated;    
        }

        //TODO: implement other kinds of calculated indicators
        return null;
    }

    private Double getValueWithoutUpdate(Indicator indicator, int year) {
        BinaryTree<YearValue> indicatorValues = values.get(indicator);

        if (indicatorValues == null) {
            return null;
        }

        YearValue yv = indicatorValues.search(new YearValue(year, 0d));

        if (yv == null) {
            return null;
        }

        return yv.value;
    }

    /**
     * Method to check if divisor and numerator of percentage are more recent than quotient. Assumes the objects that
     * contain the divisor value and the numerator value really exist, and really have those values. Assumes this
     * object has already calculated and stored this indicator and year at some time in the past.
     * @param indicator
     * @param year
     * @return
     */
    private boolean areBasesNewer(Indicator indicator, int year) {
        Indicator base = indicator.indicatorBasis; // Base indicator type on which this indicator bases its value

        Instant calculated = lastUpdated(indicator, year);
        Instant basis = lastUpdated(base, year); // timestamp of last update to this object's base value
        Instant percentileBasis = database.lastUpdated(indicator.percentileBasis, base, year); // timestamp of last update to external base value

        // If either base has been updated since indicator value was calculated
        if (basis.isAfter(calculated) || percentileBasis.isAfter(calculated)) {
            return true;
        }

        return false;
    }

    private void updateTimestamp(Indicator indicator, Integer year) {
        lastUpdated.put(new Pair<Indicator, Integer>(indicator, year), Instant.now());
    }

    private Double setAnyValue(Indicator indicator, int year, Double value) {
        //TODO: automatically recalculate values of indicators which use given indicator values as basis

        BinaryTree<YearValue> indicatorValues = values.get(indicator);
        YearValue newValue = new YearValue(year, value);

        // Tree does not exist for given indicator. Create new tree and insert new YearValue
        if (indicatorValues == null) {
            BinaryTree<YearValue> newIndicatorValues = new BinaryTree<YearValue>(new SortByYear()); // Create new indicator values tree
            newIndicatorValues.insert(newValue); // Insert new YearValue into new indicator values tree

            values.put(indicator, newIndicatorValues); // Add new indicator values tree to values map
            updateTimestamp(indicator, year); // set timestamp for new year
            return null;
        }

        YearValue oldValue = indicatorValues.set(newValue); // set newValue of YearValue, returning previous YearValue
        updateTimestamp(indicator, year); // set timestamp for new year

        // If this is a year which was not previously tracked in the tree
        if (oldValue == null) {
            return null;
        }

        return oldValue.value;
    }
}
