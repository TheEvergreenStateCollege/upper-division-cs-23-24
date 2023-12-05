package com.juniperGMVAD.app;
import java.util.*;

public class CountryDataEntry {
    private String countryName;
    private String countryCode;
    private String indicatorName;
    private String indicatorCode;
    private List<YearValue> values;

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

    public void setYearValue(int year, Double value) {
        //TODO check if exists
        values.set(index, value);
    }

    public Double getYearValue(int year) {
        //TODO Check if exists
        return values.get(index);
    }

    public void addYearValue(int year, Double value) {
        //TODO: check if value already exists
        YearValue yv = new YearValue(year, value);
        values.add(yv);
    }

    public void removeYearValue(int year) {

    }

    public int getYearsTracked() {
        return values.size();
    }
}
