package com.jon.app;

import java.util.Date;



public class CrimeData {
    private String Year;
    private String County;
    private String crimeType;
    private int indexCount;
    private float indexRate;
    private int violentCount;
    private float violentRate;
    private int propertyCount;
    private float propertyRate;
    private int firearmCount;
    private float firearmRate;
    private int population;

//Constructor
    public CrimeData(String Year, String County, String crimeType, int population, int indexCount, float indexRate, int violentCount, float violentRate, int propertyCount, float propertyRate, int firearmCount, float firearmRate ) {
    this.Year = Year;
    this.County = County;
    this.crimeType = crimeType; 
    this.population = population;
    this.indexCount = indexCount;
    this.indexRate = indexRate;
    this.violentCount = violentCount;
    this.violentRate = violentRate;
    this.propertyCount = propertyCount;
    this.propertyRate = propertyRate;
    this.firearmCount = firearmCount;
    this.firearmRate = firearmRate; 
    
    }

    //Getters 
    public String getYear() {
        return Year;
    }

    public String getCounty() {
        return County;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public int getPopulation() {
        return population;
    }

    public int getIndexCount() {
        return indexCount;
    }
   
    public float getIndexRate() {
        return indexRate;
    }

    public int getViolentCount() {
        return violentCount;
    }

    public float getViolentRate() {
        return violentRate;
    }
    
    public int getPropertyCount() {
        return propertyCount;
    }

    public float getPropertyRate() {
        return propertyRate;
    }

    public int getFirearmCount() {
        return firearmCount;
    }

    public float getFirearmRate() {
        return firearmRate;
    }    
    //toString method for better logging and printing
    @Override 
        public String toString() {
            return "CrimeData{" +
                    "date='" + Date + '\'' +
                    ", county='" + County + '\'' +
                    ", crimeType='" + crimeType + '\'' +
                    ", population='" + population + '\'' +
                    ", indexCount='" + indexCount + '\'' +
                    ", indexRate='" + indexRate + '\'' +
                    ", violentCount='" + violentCount + '\'' +
                    ", violentRate='" + violentRate + '\'' +
                    ", propertyCount='" + propertyCount + '\'' +
                    ", propertyRate='" + propertyRate + '\'' +
                    ", firearmCount='" + firearmCount + '\'' +
                    ", firearmRate='" + firearmRate + '\'' +
                    '}';
    }
}