public class Driver {
    
    // member variables
    String name;
    String address;
    String licenseNumber;
    Date birthdate;
    boolean hasAppointment;

    public Driver(
        String name,
        String address,
        String licenseNumber,
        Date birthdate,
        boolean hasAppointment
    ) {
        this.name = name;
        this.address = address;
        this.licenseNumber = licenseNumber;
        this.birthdate = birthdate;
        this.hasAppointment = hasAppointment;
    }

    // TODO: have a checkin method for driver, compare
    // their checkin time with the appointment time
    // if it's too late, move from high priority to low priority
}
