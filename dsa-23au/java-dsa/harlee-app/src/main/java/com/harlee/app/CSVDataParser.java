package com.harlee.app;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVDataParser {

     // parse CSV file
    public static List<String[]> parseCSV(String filePath, char delimiter) {
        List<String[]> data = new ArrayList<>();
       

        // close
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                
                String[] row = parseCSVLine(line, delimiter);
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //if empty
        if (data.isEmpty()){
            throw new RuntimeException("CSV file is empty.");
        }

        return data;
    }

    // parse from a CSV file
    private static String[] parseCSVLine(String line, char delimiter) {
        List<String> values = new ArrayList<>();
        StringBuilder field = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes; // toggle the inQuotes flag when a double quote is encountered
            } else if (c == delimiter && !inQuotes) {
                values.add(field.toString()); // add the field to the list of values
                field.setLength(0); // reset the field for the next value
            } else {
                field.append(c); // append the character to the field
            }
        }

        values.add(field.toString()); 

        // Convert the list of values to a String array and return it
        return values.toArray(new String[0]);
    }

    
    
}
