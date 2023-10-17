package com.pswishcorp.app;

public class Car {
    String cartype;
    String driver;
    int hwyMPG;

    public Car(String driver, String cartype, int hwyMPG) {
        this.cartype = cartype;
        this.hwyMPG = hwyMPG;
        float averageCostPerGallonPrem;
    }

    public static void main(String[] args) {
        Car car1 = new Car("Paul","Q5", 27);
        Car car2 = new Car("Nate", "Genesis", 28);
        }
    }

