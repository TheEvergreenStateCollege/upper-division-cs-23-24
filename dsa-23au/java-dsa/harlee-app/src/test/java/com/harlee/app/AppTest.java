package com.harlee.app;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import java.util.List;

import org.junit.Test;




/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        int targetYear = 2000;
        List<String[]> parsedData = new ArrayList<String[]>();
        parsedData.add(new String[] {
            "Gasseltepaoua","10866","Valid","H5","","Fell","2000","14.15083","-2.04167","(14.15083, -2.04167)","",""
        });
            
        int meteorCount = CSVDataAnalyzer.countMeteorsInYear(parsedData, targetYear);

        assertEquals(1,meteorCount);

    }

    @Test
    public void shouldAnswerWithFalse()
    {
       
        assertEquals(1,2);

    }

    @Test
    public void wrongYear()
    {
        int targetYear = 2001;
        List<String[]> parsedData = new ArrayList<String[]>();
        parsedData.add(new String[] {
            "Gasseltepaoua","10866","Valid","H5","","Fell","2000","14.15083","-2.04167","(14.15083, -2.04167)","",""
        });
            
        int meteorCount = CSVDataAnalyzer.countMeteorsInYear(parsedData, targetYear);

        assertEquals(1,meteorCount);

    }

    
}
