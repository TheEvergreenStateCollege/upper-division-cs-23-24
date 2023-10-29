package com.pswishcorp.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.junit.Test;

public class BufferTest {

    @Test
    public void testReadCSVFile1() {
        String path1 = "/home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/src/resources/DataSet_DSAau_pswish.csv";

        try {
            List<Buffer.BufferObject> data1 = Buffer.readCSVFile(path1);

            // Assert that the data list is not null
            assertNotNull(data1);
            assertTrue(data1.size() > 0);
            assertEquals(39, data1.size()); // Assuming there are 39 lines in the test file

            // Add more specific tests for your data as needed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadCSVFile2() {
        String path2 = "/home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/src/resources/Time_Driving_Spreadsheet.csv";

        try {
            List<Buffer.BufferObject> data2 = Buffer.readCSVFile(path2);

            // Assert that the data list is not null
            assertNotNull(data2);
            assertTrue(data2.size() > 0);
            assertEquals(27, data2.size()); // Assuming there are 27 lines in the test file

            // Add more specific tests for your data as needed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBufferObjectFields() {
        String time = String.valueOf(1800);
        String date = String.valueOf("10/28/2023");
        String trip = "11 mins";
        String origin = "Origin";
        String destination = "Destination";
        Boolean detour = true;
        String driver = "Driver";
        String distance = "11 miles";

        Buffer.BufferObject bufferObject = new Buffer.BufferObject(time, date, trip, origin, destination, detour, driver, distance);

        // Test individual fields of the BufferObject
        assertEquals(time, bufferObject.getTime());
        assertEquals(date, bufferObject.getDate());
        assertEquals(trip, bufferObject.getTrip());
        assertEquals(origin, bufferObject.getOrigin());
        assertEquals(destination, bufferObject.getDestination());
        assertEquals(detour, bufferObject.getDetour());
        assertEquals(driver, bufferObject.getDriver());
        assertEquals(distance, bufferObject.getDistance());
    }
}
