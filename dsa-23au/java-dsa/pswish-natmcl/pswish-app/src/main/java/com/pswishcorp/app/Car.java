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
    }

    public static void main(String[] args) {
        Car car1 = new Car("Paul","Suv", 27, .16);
        Car car2 = new Car("Nate", "Sedan", 28,  .17);
        }

    public Object getDriver() throws Exception {
        throw new Exception("Not yet implemented, v0.4");
    }

    public Object getCarType() throws Exception {
        throw new Exception("Not yet implemented, v0.4");
    }

    public Object getHwyMPG() throws Exception {
        throw new Exception("Not yet implemented, v0.4");
    }

    public double getFuelCostPerGallon() throws Exception {
        throw new Exception("Not yet implemented, v0.4");
    }
    }

