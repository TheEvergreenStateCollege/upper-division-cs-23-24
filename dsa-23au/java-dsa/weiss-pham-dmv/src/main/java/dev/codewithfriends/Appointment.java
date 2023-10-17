package dev.codewithfriends;
import java.util.Date;
// import dev.codewithfriends.Driver;
public class Appointment

{
    
    enum ServiceType {
        LICENSE_RENEWAL,
        TAG_RENEWAL
    };

    // member variables
    private Date date;
    private Driver driver;
    private ServiceType serviceType;

    // Start day and time
    // Date date;
    
    
    public void displayAppointmentDetails(Appointment
    appointment) {
        System.out.println("Appointment Details:");
        System.out.println("Date: "+ appointment.getDate());
        System.out.println("Driver Name: " + appointment.getDriver().getName());
        System.out.println("Service Type: " + appointment.getServiceType());
    }
    // Driver driver;
    // ServiceType type;

    public Appointment(Date date, Driver driver, ServiceType type) {
        this.date = date;
        this.driver = driver;
        this.serviceType = type;
    }
    public Date getDate() {
        return this.date;
    }
    public Driver getDriver() {
        return this.driver;
    }
    public ServiceType getServiceType() {
        return this.serviceType; 
    }
}
