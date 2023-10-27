package com.pswishcorp.app;

public class Car {
    String cartype;
    String driver;
    int hwyMPG;
    String carMake;
    String carModel;
    String fuelType;
    String availabilityStatus;
    double averageCostPerGallonPrem;

    public Car(String driver, String cartype, int hwyMPG, double averageCostPerGallonPrem) {
        this.cartype = cartype;
        this.hwyMPG = hwyMPG;
        this.averageCostPerGallonPrem = averageCostPerGallonPrem;
        this.driver = driver;
    }

    public static void main(String[] args) {
        Car car1 = new Car("Paul","Suv", 27, .14);
        Car car2 = new Car("Nate", "Sedan", 28,  .17);
        }

    public Object getDriver(){
        return driver;
    }

    public Object getCarType() {
        return cartype;
    }

    public Object getHwyMPG() {
        return hwyMPG;
    }

    public double getFuelCostPerGallon() {
        return averageCostPerGallonPrem;
    }
}

