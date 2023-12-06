package com.harlee.app;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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

    public static void visualizeCSV(List<String[]> parsedData) {
        
        JFrame frame = new JFrame("CSV Data Visualizer");

        
        JTable table = new JTable();

        
        DefaultTableModel model = new DefaultTableModel();

        
        if (!parsedData.isEmpty()) {
            for (String header : parsedData.get(0)) {
                model.addColumn(header);
            }
        }

        // Add data rows 
        for (int i = 1; i < parsedData.size(); i++) {
            model.addRow(parsedData.get(i));
        }

        
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
