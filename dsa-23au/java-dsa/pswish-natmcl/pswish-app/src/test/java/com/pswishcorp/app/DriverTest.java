package com.pswishcorp.app;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DriverTest {
    private Driver driver;

    @Before
    public void setUp() {
        driver = new Driver(11, "Paul", "Audi", "Q5", "Millersylvania", "Evergreen");
    }

    @Test
    public void testGetDistanceFromHome() {
        assertEquals(11, driver.getDistanceFromHome());
    }

    @Test
    public void testGetDriverName() {
        assertEquals("Paul", driver.getDriverName(null));
    }

    @Test
    public void testGetCarMake() {
        assertEquals("Audi", driver.getCarMake());
    }

    @Test
    public void testGetCarModel() {
        assertEquals("Q5", driver.getCarModel());
    }

    @Test
    public void testGetHome() {
        assertEquals("Millersylvania", driver.getHome());
    }

    @Test
    public void testGetSchool() {
        assertEquals("Evergreen", driver.getSchool());
    }
}
