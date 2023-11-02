# DMV Diagram for @learner-long-life

Practice diagrams in Mermaid in GitHub Markdown.
```mermaid
classDiagram
    class Appointment {
        -Date date
        -Driver driver
        -ServiceType serviceType
        +displayAppointmentDetails(Appointment)
        +Appointment(Date, Driver, ServiceType)
        +getDate(): Date
        +getDriver(): Driver
        +getServiceType(): ServiceType
    }
    class Clerk {
        +Clerk()
        +processDriver(Driver)
    }
    class DMVMain {
        -List<Driver> drivers
        -List<Clerk> clerks
        -PriorityQueue<Appointment> appointments
        -Queue<Driver> waitList
        +DMVMain()
        +addDriverWithAppointment(Driver, Appointment)
        +addDriverToWaitlist(Driver)
        +processAppointments()
        +processWaitList()
    }
    class Driver {
        -String name
        -String address
        -String licenseNumber
        -Date birthdate
        -boolean hasAppointment
        -Date checkInTime
        +Driver(String, String, String, Date, boolean)
        +checkIn()
        +isLateForAppointment(Appointment): boolean
        +getName(): String
        +getAddress(): String
        +getLicenseNumber(): String
        +getBirthdate(): Date
        +hasAppointment(): boolean
        +getCheckInTime(): Date
        +setHasAppointment(boolean)
    }
    Appointment --> Driver : has
    DMVMain --> Driver : uses
    DMVMain --> Clerk : uses
    DMVMain --> Appointment : uses
    Clerk --> Driver : processes
```
