package com.pswishcorp.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Buffer {
    public static class BufferObject {
        private String time;
        private String date;
        private String trip;
        private String origin;
        private String destination;
        private Boolean detour;
        private String driver;
        private String distance;

        public BufferObject(
            String time,
            String date,
            String trip,
            String origin,
            String destination,
            Boolean detour,
            String driver,
            String distance) {
            
            this.time = time;
            this.date = date;
            this.trip = trip;
            this.origin = origin;
            this.destination = destination;
            this.detour = detour;
            this.driver = driver;
            this.distance = distance;
        }

        // getter and setter methods for all the vars
        public String getTime() {
            return time;
        }

        public String getDate() {
            return date;
        }

        public String getTrip() {
            return trip;
        }

        public String getOrigin() {
            return origin;
        }

        public String getDestination() {
            return destination;
        }

        public Boolean getDetour() {
            return detour;
        }

        public String getDriver() {
            return driver;
        }

        public String getDistance() {
            return distance;
        }
    }

    public static List<BufferObject> readCSVFile(String path) throws IOException {

        // Main CSV reader function
        List<BufferObject> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 8) { // Process CSV files that match the format of 8 fields

                    // Parse the values and create a BufferObject
                    String time = String.valueOf(values[0]);
                    String date = String.valueOf(values[1]);
                    String trip = String.valueOf(values[2]);
                    String origin = values[3];
                    String destination = values[4];
                    Boolean detour = Boolean.valueOf(values[5]);
                    String driver = values[6];
                    String distance = String.valueOf(values[7]);

                    BufferObject obj = new BufferObject(time, date, trip, origin, destination, detour, driver, distance);
                    data.add(obj);
                }
            }
        }
        return data;
    }

    public static void main(String[] args) {

        // Paths are hardcoded, a config file might be better eventually
        String path1 = "/home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/src/resources/DataSet_DSAau_pswish.csv";
        String path2 = "/home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/src/resources/Time_Driving_Spreadsheet.csv";

        try {
            // read the first csv file
            List<BufferObject> data1 = Buffer.readCSVFile(path1);
            for (BufferObject obj : data1) {
                System.out.println("Time: " + obj.getTime() + ", Date: " + obj.getDate() + ", Trip: " + obj.getTrip() + ", Origin: " + obj.getOrigin() + ", Destination: " + obj.getDestination() + ", Detour: " + obj.getDetour() + ", Driver: " + obj.getDriver() + ", Distance: " + obj.getDistance());
            }  // Printing out first object in data1 for further testing and development
        
            List<BufferObject> data2 = Buffer.readCSVFile(path2);
            for (BufferObject obj : data2) {
                System.out.println("Time: " + obj.getTime() + ", Date: " + obj.getDate() + ", Trip: " + obj.getTrip() + ", Origin: " + obj.getOrigin() + ", Destination: " + obj.getDestination() + ", Detour: " + obj.getDetour() + ", Driver: " + obj.getDriver() + ", Distance: " + obj.getDistance());
            }  // Printing out first object in data2 for further testing and development

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
