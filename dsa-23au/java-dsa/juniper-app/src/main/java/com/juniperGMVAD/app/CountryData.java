package com.juniperGMVAD.app;
import java.util.*;

public class CountryData {
    String name;
    ArrayList<Integer> years = new ArrayList<>();
    ArrayList<Double> mva = new ArrayList<Double>();
    //double gmva;
    //double percentOfGMVA;

    public CountryData(ArrayList<Integer> years, String name, ArrayList<Double> mva) 
    {
        this.name = name;
        this.years = years;
        this.mva = mva;

        //percentOfGMVA = (mva / gmva) * 100;
    }
}
