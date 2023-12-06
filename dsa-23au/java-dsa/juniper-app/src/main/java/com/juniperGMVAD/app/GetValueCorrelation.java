package com.juniperGMVAD.app;
import java.math.*;
public class GetValueCorrelation {
    //Gets the correlation coefficient given a 
    //2d array, the columns to be correlated, and the # of rows
    //Coefficient values approaching 1 are high, .50 are moderate, <.30 is weak, 
    //approaching -1 is an inverse correlation values near 0 show little to no relationship
    
    static float correlationCoefficient(double[][] data, int columnX, int columnY, int n)
    {
        double sum_X = 0;
        double sum_Y = 0;
        double sum_XY = 0;
        double squareSum_X = 0;
        double squareSum_Y = 0;

        for (int i = 0; i < n; i++)
        {
            sum_X = sum_X + data[i][columnX];
            sum_Y = sum_Y + data[i][columnY];
            sum_XY = sum_XY + data[i][columnX] * data[i][columnY];
            squareSum_Y = squareSum_Y + data[i][columnY] * data[i][columnY];
            squareSum_X = squareSum_X + data[i][columnX] * data[i][columnX];
        }

        float corr = (float) (n * sum_XY - sum_X * sum_Y) /
        (float) (Math.sqrt((n * squareSum_X - sum_X * sum_X) * (n * squareSum_Y - sum_Y * sum_Y)));


        return corr;
    }

    static float getCorrTwoValues(double[] x, double[] y)
    {
        double[][] data = new double[x.length][2];
        int n = x.length;
        int columnX = 0;
        int columnY = 1;

        for (int i = 0; i < x.length; i++)
        {
            data[i][0] = x[i];
            data[i][1] = y[i];
        }

        float corr = correlationCoefficient(data, columnX, columnY, n);

        return corr;
    }
}
