package com.pswishcorp.app;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CarTest {
    private Car car;

    @Before
    public void setUp() {
        car = new Car("Paul", "Suv", 27, 0.14);
    }

    @Test
    public void testGetDriver() {
        assertEquals("Paul", car.getDriver());
    }

    @Test
    public void testGetCarType() {
        assertEquals("Suv", car.getCarType());
    }

    @Test
    public void testGetHwyMPG() {
        assertEquals(27, car.getHwyMPG());
    }

    @Test
    public void testGetFuelCostPerGallon() {
        assertEquals(0.14, car.getFuelCostPerGallon(), 0.01); // 0.01 is the delta for double comparison
    }
}
