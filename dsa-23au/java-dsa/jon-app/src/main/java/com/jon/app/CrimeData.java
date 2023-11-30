package com.jon.app;

import java.util.Date;



public class CrimeData {
    private String date;
    private String county;
    private String crimeType;

//Constructor
    public CrimeData(String date, String county, String crimeType) {
    this.date = date;
    this.county = county;
    this.crimeType = crimeType; 
    }

    //Getters 
    public String getDate() {
    return date;
    }

    public String getCounty() {
    return county;
    }

    public String getCrimeType() {
    return crimeType;
    }

    //toString method for better logging and printing
    @Override 
        public String toString() {
            return "CrimeData{" +
                    "date='" + date + '\'' +
                    ", county='" + county + '\'' +
                    ", crimeType='" + crimeType + '\'' +
                    '}';
    }
}