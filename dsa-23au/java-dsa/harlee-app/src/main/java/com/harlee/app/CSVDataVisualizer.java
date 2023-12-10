package com.harlee.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Index 0 = name
 * Index 1 = id #
 * Index 2 = nametype
 * Index 3 = recclass
 * Index 4 = mass 
 * Index 5 = fall/found
 * Index 6 = year
 * Index 7 = reclat
 * Index 8 = relong
 * Index 9 = geolocation
 * Index 10 = states
 * Index 11 = counties
 */

 public class CSVDataVisualizer {


    //OPTION 1
    public static void visualizeColumn(List<String[]> parsedData, int columnIndex) {
        //if empty
        if (parsedData.isEmpty() || columnIndex < 0 || columnIndex >= parsedData.get(0).length) {
            System.out.println("Invalid column index or empty data.");
            return;
        }

        System.out.println("Column: " + columnIndex);

        // Extract values from the specified column
        List<String> columnValues = extractColumnValues(parsedData, columnIndex);

        // Sort the values
        Collections.sort(columnValues);

        // Print the sorted values in a comma-separated format
        System.out.println(String.join(", ", columnValues));
    }

    public static List<String> extractColumnValues(List<String[]> parsedData, int columnIndex) {
        // Extract values from the specified column
        List<String> columnValues = new ArrayList<>();

        for (int i = 0; i < parsedData.size(); i++) {
            String[] row = parsedData.get(i);
            if (columnIndex < row.length) {
                columnValues.add(row[columnIndex]);
            }
        }

        return columnValues;
    }
}

