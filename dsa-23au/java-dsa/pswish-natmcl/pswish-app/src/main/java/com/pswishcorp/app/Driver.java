package com.pswishcorp.app;
import java.util.HashMap;
import java.util.Map;

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
    public Object getDistanceFromHome() throws Exception {
        throw new Exception("Not yet implemented, v0.4");
    }

    public Object getDriverName(String driver) {
        Map<String, String> drivers = new HashMap<>();
        return drivers;
    }

    public Object getCarMake() throws Exception {
        throw new Exception("Not yet implemented, v0.4");
    }
    public Object getCarModel() throws Exception {
        throw new Exception("Not yet implemented, v0.4");
    }
    public Object getHome() throws Exception {
        throw new Exception("Not yet implemented, v0.4");
    }
    public Object getSchool() throws Exception {
        throw new Exception("Not yet implemented, v0.4");
    }
        
}