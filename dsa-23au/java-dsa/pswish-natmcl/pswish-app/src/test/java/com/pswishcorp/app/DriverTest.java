// package com.pswishcorp.app;

// import org.junit.Before;
// import org.junit.Test;
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertTrue;

// import java.util.Map;
// import java.util.HashMap;

// public class DriverTest {
//     private Driver driver1;
//     private Driver driver2;
//     private Driver driver3;

//     @Before
//     public void setUp() {
//         driver1 = new Driver(11, "Paul", "Audi", "Q5", "Millersylvania", "Evergreen");
//         driver2 = new Driver(15, "Nathan", "Hyundai", "Genesis", "Lakewood", "Evergreen");
//         driver3 = new Driver(86, "Paul", "Audi", "Q5", "Gardiner", "Evergreen");
//     }

//     // Remove the throws exception when parent method is finished for all following tests
//     @Test
//     public void testGetDistanceFromHome() throws Exception {
//         assertEquals(11, driver1.getDistanceFromHome());
//         assertEquals(15, driver2.getDistanceFromHome());
//         assertEquals(86, driver3.getDistanceFromHome());
//     }

//     @Test
//     public void testGetDriverName() {
//         Driver driverName = new Driver(0, null, null, null, null, null); // Replace 'YourClass' with the actual class name

//         // Call the method to get the driver name map
//         Object result = driverName.getDriverName("John Doe");

//         // Ensure that the result is a HashMap
//         assertTrue(result instanceof Map);

//         // Cast the result to a HashMap for further testing
//         Map<String, String> driverMap = (Map<String, String>) result;

//         // Add some driver names to the map for testing
//         driverMap.put("Driver1", "Alice");
//         driverMap.put("Driver2", "Bob");

//         // Check if the driver name is in the map
//         assertTrue(driverMap.containsKey("John Doe"));

//         // Check if the driver name matches the expected name
//         assertEquals("John Doe", driverMap.get("John Doe"));
//     }


//     @Test
//     public void testGetCarMake() throws Exception {
//         assertEquals("Audi", driver1.getCarMake());
//         assertEquals("Hyundai", driver2.getCarMake());
//         assertEquals("Audi", driver3.getCarMake());
//     }

//     @Test
//     public void testGetCarModel() throws Exception {
//         assertEquals("Q5", driver1.getCarModel());
//         assertEquals("Genesis", driver2.getCarModel());
//         assertEquals("Q5", driver3.getCarModel());
//     }

//     @Test
//     public void testGetHomeLocation() throws Exception {
//         assertEquals("Millersylvania", driver1.getHome());
//         assertEquals("Lakewood", driver2.getHome());
//         assertEquals("Gardiner", driver3.getHome());
//     }

//     @Test
//     public void testGetSchoolLocation() throws Exception {
//         assertEquals("Evergreen", driver1.getSchool());
//         assertEquals("Evergreen", driver2.getSchool());
//         assertEquals("Evergreen", driver3.getSchool());
//     }
// }
