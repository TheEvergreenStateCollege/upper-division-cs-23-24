package com.juniperGMVAD.app;
import java.util.*;

public class CountryDataEntry {
    private String countryName;
    private String countryCode;
    private String indicatorName;
    private String indicatorCode;
    private List<Double> values;

    public CountryDataEntry(String countryName, String countryCode, String indicatorName, String indicatorCode) 
    {
        this.countryName   = countryName;
        this.countryCode   = countryCode;
        this.indicatorName = indicatorName;
        this.indicatorCode = indicatorCode;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorCode(String indicatorCode) {
        this.indicatorCode = indicatorCode;
    }

    public String getIndicatorCode() {
        return indicatorCode;
    }

    public void setValue(int index, Double value) {
        values.set(index, value);
    }

    public Double getValue(int index) {
        return values.get(index);
    }

    public void addValue(Double value) {
        values.add(value);
    }

    public int getValueSize() {
        return values.size();
    }
}
