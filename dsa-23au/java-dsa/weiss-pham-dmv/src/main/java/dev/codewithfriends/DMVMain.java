package dev.codewithfriends;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DMVMain {

    List<Driver> drivers;
    List<Clerk> clerks;
    PriorityQueue<Appointment> appointments;
    Queue<Driver> waitList; 
    static Clerk firstClerk;

    public DMVMain() {
        drivers = new ArrayList<>();
        clerks = new ArrayList<>();
        appointments = new PriorityQueue<>();
        clerks.add(new Clerk()); // add a first clerk
    }

    public void addDriverWithAppointment(Driver driver, Appointment appointment) {
        drivers.add(driver);
        appointments.add(appointment);
        driver.setHasAppointment(true); 
    }

    public void addDriverToWaitlist(Driver driver) {
        drivers.add(driver);
        waitList.add(driver);
        driver.setHasAppointment(false); // no appointment
    }

    public void processAppointments() {
        while (!appointments.isEmpty()) {
            Appointment currentAppointment = appointments.poll();
            Driver driver = currentAppointment.getDriver();  // using driver from Appointment
    
            // process with the first available clerk
            Clerk availableClerk = clerks.get(0); 
            availableClerk.processDriver(driver);  // Pass service type for processing
    
            System.out.println("Processed appointment for driver: " + driver.getName());
        }
    }

    public void processWaitList() {
        while (!waitList.isEmpty()) {
            Driver driver = waitList.poll();

            
            Clerk availableClerk = clerks.get(0); 
            availableClerk.processDriver(driver);

            System.out.println("Processed driver from waitlist: " + driver.getName());
        }
    }

    public Clerk getFirstClerk() {
        return clerks.get(0);
    }

    public static int getMostFrequentAppointmentHour() {
        return firstClerk.getMostFrequentAppointmentHour();
    }

    public static void main(String[] args) {
        DMVMain dmv = new DMVMain();
        firstClerk = dmv.getFirstClerk();
        InputStream is = ClassLoader.getSystemResourceAsStream("dmv-mock-data.csv");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 0 || line.length() == 0) {
                    continue;
                }
                    
                try {
                    String dateString = String.format("2023-11-13 %s", tokens[9]);
                    String dateFormat = "yyyy-MM-dd HH:mm a";
                    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                        Driver driver = new Driver(
                        tokens[2] + tokens[3], sdf.parse(dateString));
                    firstClerk.enqueueDriver(driver);

                } catch(ParseException pe) {
                    // continue to next line
                    System.err.println(pe.toString());
                } catch(IndexOutOfBoundsException ioobe) {
                    // that line did not have an appointment time                    
                }
            }
        } catch(IOException ioe) {
            System.err.println(ioe.toString());
        }

        if (args.length < 1) {
            System.err.println("  Usage: <command> mostFreqHour");
        }

        if (args[0].equals("mostFreqHour")) {
            int hour = getMostFrequentAppointmentHour();
            System.out.printf("  Most frequent appointment hour is %d\n", hour);
        }

    }
}
