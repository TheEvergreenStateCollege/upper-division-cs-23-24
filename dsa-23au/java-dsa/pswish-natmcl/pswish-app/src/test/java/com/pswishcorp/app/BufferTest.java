package com.pswishcorp.app;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BufferTest {
    private String path1;
    private String path2;

    @Before
    public void setUp() {
        // Set the file paths for testing
        path1 = "/home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/src/resources/DataSet_DSAau_pswish.csv";
        path2 = "/home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/src/resources/Time_Driving_Spreadsheet.csv";
    }

    @Test
    public void testReadCSVFile1() throws IOException {
        List<String[]> data = Buffer.readCSVFile(path1);
        assertEquals(39, data.size()); // Assuming there are 2 lines in the test file
    }

    @Test
    public void testReadCSVFile2() throws IOException {
        List<String[]> data = Buffer.readCSVFile(path2);
        assertEquals(27, data.size()); // Assuming there are 3 lines in the test file
        }
    }

