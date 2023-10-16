public class Appointment {
    
    enum ServiceType {
        LICENSE_RENEWAL,
        TAG_RENEWAL
    };

    // member variables

    // Start day and time
    Date date;
    
    Driver driver;
    ServiceType type;

    public Appointment(Date date, Driver driver, ServiceType type) {
        this.date = date;
        this.driver = driver;
        this.type = type;
    }
}
