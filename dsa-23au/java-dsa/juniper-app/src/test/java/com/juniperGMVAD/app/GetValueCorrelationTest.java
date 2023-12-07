package com.juniperGMVAD.app;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GetValueCorrelationTest {
    @Test
    public void corr2value() {
        double[] arrX = {.04, .08, .06, .07, .08};
        double[] arrY = {.03, .04, .07, .07, .09};

        double correlation = GetValueCorrelation.getCorrTwoValues(arrX, arrY);

 
        assertEquals(0.5489437580108643, correlation, 0.0001);
    }

    @Test
    public void corrCalcTest() {
        double[][] arr = {
                {1200000000, 0.10, 135, 130},
                {1300000000, 0.11, 177, 160},
                {1400000000, 0.12, 9, 7},
                {1500000000, 0.13, 4, 6},
                {1600000000, 0.14, 78, 90},
                {1700000000, 0.15, 67, 56},
                {1800000000, 0.16, 80, 76},
                {1900000000, 0.17, 600, 800}
        };

        double correlation1 = GetValueCorrelation.correlationCoefficient(arr, 0, 1, 8);
        double correlation2 = GetValueCorrelation.correlationCoefficient(arr, 2, 3, 8);


     //   assertEquals(0.9927228990682609, correlation1, 0.0001);
       // assertEquals(0.9750421806006255, correlation2, 0.0001);
    }
}

