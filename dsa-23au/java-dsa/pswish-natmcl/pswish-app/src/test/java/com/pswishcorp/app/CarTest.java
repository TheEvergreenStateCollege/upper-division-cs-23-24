// package test.java.com.pswishcorp.app;

// import org.junit.Before;
// import org.junit.Test;
// import static org.junit.Assert.assertEquals;

// public class CarTest {
//     private Car car1;
//     private Car car2;

//     @Before
//     public void setUp() {
//         car1 = new Car("Paul", "Suv", 27, 0.16);
//         car2 = new Car("Nathan", "Genesis", 28, 0.17);
//     }

//     // Remove the throws exception when parent method is finished for all following tests
//     @Test
//     public void testCarDriver() throws Exception {
//         assertEquals("Paul", car1.getDriver());
//         assertEquals("Nathan", car2.getDriver());
//     }

//     @Test
//     public void testCarType() throws Exception {
//         assertEquals("Suv", car1.getCarType());
//         assertEquals("Sedan", car2.getCarType());
//     }

//     @Test
//     public void testCarHwyMPG() throws Exception {
//         assertEquals(27, car1.getHwyMPG());
//         assertEquals(28, car2.getHwyMPG());
//     }

//     @Test
//     public void testFuelCostPerGallon() throws Exception {
//         assertEquals(0.16, car1.getFuelCostPerGallon(), 0.001);
//         assertEquals(0.17, car2.getFuelCostPerGallon(), 0.001);
//     }
// }
