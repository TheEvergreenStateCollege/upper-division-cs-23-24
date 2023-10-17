package main.java.com.pswiscorp.app;

public class Car {
    String cartype;
    String driver;
    int hwyMPG;

    public Car(String driver, String cartype, int hwyMPG) {
        this.cartype = cartype;
        this.hwyMPG = hwyMPG;
        String gastype;
        float avgerageCostPerGallonReg;
        float avgerageCostPerGallonPrem;

    }

    public static void main(String[] args) {
        Car car1 = new Car("Paul","Audi", 31);
        Car car2 = new Car("Nate", "Natescar", 25);
        }
    }

