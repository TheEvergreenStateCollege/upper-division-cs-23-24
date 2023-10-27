package com.pswishcorp.app;
// import java.util.HashMap;
// import java.util.Map;

public class Driver {
    int distanceFromHome;
    String driverName;
    String carMake;
    String carModel;
    String home;
    String school;

   public Driver(int distanceFromHome,  String driverName,
            String carMake, String carModel, String home, String school) {
        this.distanceFromHome = distanceFromHome;
        this.driverName = driverName;
        this.carMake = carMake;
        this.carModel = carModel;
        this.home = home;
        this.school = school;
       }
public static void main(String[] args) {
        Driver driver1 = new Driver(11, "Paul", "Audi", "Q5", "Millersylvania", "Evergreen");
        Driver driver2 = new Driver(15, "Nathan", "Hyndai", "Genesis", "Lakewood", "Evergreen");
        Driver driver3 = new Driver(86, "Paul", "Audi", "Q5", "Gardiner", "Evergreen");
        }

    // Remove the throws exception when finished then check for all following tests
    public Object getDistanceFromHome() {
        return distanceFromHome;
    }

    public Object getDriverName(String string) {
        return driverName;
    }

    public Object getCarMake() {
        return carMake;
    }
    
    public Object getCarModel() {
        return carModel;
    }

    public Object getHome() {
        return home;
    }

    public Object getSchool() {
        return school;
    }   
}