package com.ActivityTor.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DataReader 
{
    private String filePath;

    public DataReader(String filePath) 
    {
        this.filePath = filePath;
    }

    public List<String[]> readCSVData() 
    {
        List<String[]> dataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] values = line.split(",");
                dataList.add(values);
            }
        } catch (IOException e) 
        {
            e.printStackTrace();
        }

        return dataList;
    }
}
