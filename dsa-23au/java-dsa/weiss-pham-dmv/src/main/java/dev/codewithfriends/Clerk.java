package dev.codewithfriends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Clerk {
    
    private List<Driver> drivers;
    private Map<Integer,Integer> appointmentsByHour;
    private int mostFrequentCount;
    private int mostFrequentHour;

    public Clerk() {
        drivers = new ArrayList<>();
        appointmentsByHour = new HashMap<>();
        mostFrequentCount = 0;
        mostFrequentHour = -1;
    }

    public void enqueueDriver(Driver driver){
        drivers.add(driver);
        Integer hour = driver.getAppointmentHour();
        Integer count = appointmentsByHour.get(hour);
        if (count == null){
            appointmentsByHour.put(hour, 1);
        } else {
            count += 1;
            appointmentsByHour.put(hour, count);
        }
        if ((count != null) && (count > mostFrequentCount)) {
            mostFrequentCount = count;
            mostFrequentHour = hour;
        }
        System.out.printf("Enqueued a new driver at end of %d others.\n", drivers.size());
        System.out.printf("  startingHour %d withCount %d\n", hour, count);
    }

    public int getMostFrequentAppointmentHour() {
        return mostFrequentHour;
    }

    public void processDriver(Driver driver) {
        // process a driver here
        // print the driver's details
        System.out.println("Processing driver: " + driver.getName());
    }
}
