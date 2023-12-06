package dev.codewithfriends;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Driver {
    
    // member variables
    private String name;
    private String address;
    private String licenseNumber;
    private Date birthdate;
    private Date appointmentTime;
    private boolean hasAppointment;
    private Date checkInTime;  // added check-in time

    public Driver(
        String name,
        Date appointmentTime
    ) {
        this.name = name;
        this.appointmentTime = appointmentTime;
    }

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
        this.checkInTime = null;  // Initializing with no check-in time
    }

    // New method to handle check-in
    public void checkIn() {
        this.checkInTime = new Date(); // Capture the current time as check-in time
    }

    public boolean isLateForAppointment(Appointment appointment) {
        if (this.hasAppointment && this.checkInTime != null) {
            if (this.checkInTime.after(appointment.getDate())) { 
                return true;  // The driver is late
            }
        }
        return false; // The driver is not late
    }
    public Integer getAppointmentHour(){
        LocalDateTime localDataTime = this.appointmentTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        Integer hour = localDataTime.getHour();
        return hour;
    }
   
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public boolean hasAppointment() {
        return hasAppointment;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setHasAppointment(boolean hasAppointment) {
        this.hasAppointment = hasAppointment;
    }
}

