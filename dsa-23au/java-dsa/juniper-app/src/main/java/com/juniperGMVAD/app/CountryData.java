package com.juniperGMVAD.app;

import java.util.ArrayList;
import java.util.List;

public class CountryData {
    public class Pair<T, U> {
        public T first;
        public U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }
    }

    private Country country;
    private List<Pair<Indicator,List<YearValue>>> values;

    public CountryData(Country country) {
        this.country = country;
    }

    public void setValue(Indicator indicator, int year, Double value) {
        List<YearValue> indicatorValues = getIndicatorValues(indicator);

        if (!hasIndicator(indicator)) {
            List<YearValue> yearValues = new ArrayList<YearValue>();
            Pair<Indicator,List<YearValue>> newIndicatorPair = new Pair<Indicator,List<YearValue>>(indicator, yearValues);
            values.add(newIndicatorPair);
        }

        if (hasYearValue(indicator, year)) {
            YearValue yv = getYearValue(indicator, year);
            yv.value = value;
            return;
        }
        
        YearValue yv = new YearValue(year, value);
        List<YearValue> iv = getIndicatorValues(indicator);
        iv.add(yv);
    }

    public boolean hasIndicator(Indicator indicator) {
        for (Pair<Indicator,List<YearValue>> pair : values) {
            if (pair.first == indicator) {
                return true;
            }
        }

        return false;
    }

    private List<YearValue> getIndicatorValues(Indicator indicator) {
        for (Pair<Indicator,List<YearValue>> i : values) {
            if (i.first == indicator) {
                return i.second;
            }
        }

        return null;
    }

    private boolean hasYearValue(Indicator indicator, int year) {
        List<YearValue> indicatorValues = getIndicatorValues(indicator);

        for (YearValue yv : indicatorValues) {
            if (yv.year == year) {
                return true;
            }
        }

        return false;
    }

    private YearValue getYearValue(Indicator indicator, int year) {
        List<YearValue> indicatorValues = getIndicatorValues(indicator);

        for (YearValue yv : indicatorValues) {
            if (yv.year == year) {
                return yv;
            }
        }
        
        //TODO: error handling
        return null;
    }
}
