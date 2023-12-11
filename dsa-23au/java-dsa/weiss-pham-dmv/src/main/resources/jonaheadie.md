classDiagram
    Clerk --|> Driver
    Appointment --|> Driver
    Appointment --|> enumServiceType
    class DMVMain {
        +drivers
        +clerks
        +appointments
        +waitList
        +firstClerk
        +addDriverWithAppointment()
        +addDriverToWaitlist()
        +processAppointments()
        +processWaitList()
        +getFirstClerk()
        +getMostFrequentAppointmentHour()
    }
    class Clerk {
        +drivers
        +appointmentsByHour
        +mostFrequentCount
        +mostFrequentHour
        +enqueueDriver()
        +getMostFrequentAppointmentHour()
        +processDriver()
    }
    class Appointment {
        +date
        +driver 
        +serviceType 
        +displayAppointmentDetails()
        +getData()
        +getDriver()
        +getServiceType()

    }
    class Driver {
        +name 
        +address 
        +licenseNumber
        +birthdate
        +appointmentTime 
        +hasAppointment
        +checkInTime
        +checkIn()
        +isLateForAppointment()
        +getAppointmentHour()
        +getName()
        +getAddress()
        +getLicenseNumber()
        +getBirthdate()
        +hasAppointment()
        +getCheckInTime()
        +setHasAppointment()
    }
    class enum ServiceType {
        LICENSE_RENEWAL
        TAG_RENEWAL
    }
    class Authorship {
        Based on design of source code by Pham/Weiss
    }