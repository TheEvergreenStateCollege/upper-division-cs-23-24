package com.juniperGMVAD.app;

public class CountryData {

    String name;
    int year;
    double mva;
    double gmva;
    double percentOfGMVA;

    public CountryData(String name, int year, double mva, double gmva) 
    {
        this.name = name;
        this.year = year;
        this.mva = mva;
        this.gmva = gmva;

        percentOfGMVA = mva / gmva;
    }

    
}
