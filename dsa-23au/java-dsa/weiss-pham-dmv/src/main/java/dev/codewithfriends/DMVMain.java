package dev.codewithfriends;
import java.util.PriorityQueue;
import java.util.*;

public class DMVMain {

    List<Driver> drivers;
    List<Clerk> clerks;
    PriorityQueue<Appointment> appointments;
    Queue<Driver> waitList; {

        public DMVMain() {
            this.drivers = new ArrayList<>();
            this.clerks = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                this.clerks.add(new Clerk()); 
            }
            this.appointments = new PriorityQueue<>(Comparator.comparing(Appointment::getDate));
            this.waitList = new LinkedList<>();
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
            availableClerk.processDriver(driver, currentAppointment.getServiceType());  // Pass service type for processing
    
            System.out.println("Processed appointment for driver: " + driver.getName());
        }
    }

    public void processWaitList() {
        while (!waitList.isEmpty()) {
            Driver driver = waitList.poll();

            
            Clerk availableClerk = clerks.get(0); 
            availableClerk.processDriver(driver);

            System.out.println("Processed driver from waitlist: " + driver.name);
        }
    }

    public static void main(String[] args) {
        DMVMain dmv = new DMVMain();

        // Create drivers
        Driver driver1 = new Driver("John Doe", "1234 Elm St", "D123456", new Date(), false);
        Driver driver2 = new Driver("Jane Smith", "5678 Oak St", "D789012", new Date(), false);

        // create appointments
        Appointment appointment1 = new Appointment(new Date(), driver1, Appointment.ServiceType.LICENSE_RENEWAL);

        // add drivers
        dmv.addDriverWithAppointment(driver1, appointment1);
        dmv.addDriverToWaitlist(driver2);

        dmv.processAppointments();
        dmv.processWaitList();
    }
}
