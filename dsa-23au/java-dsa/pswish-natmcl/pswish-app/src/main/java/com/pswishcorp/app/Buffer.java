package com.pswishcorp.app;

// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.Arrays;

// public class Buffer {
// public static void main (String[] args) {
//     // Set some variables for the data files 1 and 2 (not combined)
// 	String path1 = "dsa-23au/java-dsa/pswish-natmcl/pswish-app/src/main/java/com/pswishcorp/app/DataSet_DSAau_pswish.csv";
//     String path2 = "dsa-23au/java-dsa/pswish-natmcl/pswish-app/src/main/java/com/pswishcorp/app/Time_Driving_Spreadsheet.csv";
// 	String line = "";

//     // Use a bufferedreader to read the csv with a try block
// 	try {
// 		BufferedReader reader = new BufferedReader (new FileReader (path1));

//         // Read each line in file1 when not null in file 1;
// 		while ((line = reader.readLine ()) != null) {

//             // Add the values split by commas to an array
// 			String[] values = line.split (",");
            
//             // Print for testing locally
// 			System.out.println (Arrays.toString (values));
// 		}
//         reader.close();

//         BufferedReader reader2 = new BufferedReader (new FileReader (path2));
//         // Read each line in file2 when not null in file 1;
// 		while ((line = reader2.readLine ()) != null) {

//             // Add the values split by commas to an array
// 			String[] values2 = line.split (",");

//             // Print for testing locally
// 			System.out.println (Arrays.toString (values2));
// 		}
//         reader2.close();
 
// 	} catch (IOException e) {
// 		e.printStackTrace ();
// 	    } 
//     }
// }

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Buffer {
    public static List<String[]> readCSVFile(String path) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        }
        return data;
    }

    public static void main(String[] args) {
        // Set some variables for the data files 1 and 2 (not combined)
        String path1 = "dsa-23au/java-dsa/pswish-natmcl/pswish-app/src/main/java/com/pswishcorp/app/DataSet_DSAau_pswish.csv";
        String path2 = "dsa-23au/java-dsa/pswish-natmcl/pswish-app/src/main/java/com/pswishcorp/app/Time_Driving_Spreadsheet.csv";

        try {

            List<String[]> data1 = Buffer.readCSVFile(path1);
            for (String[] values : data1) {
                System.out.println(Arrays.toString(values));
            }

            List<String[]> data2 = Buffer.readCSVFile(path2);
            for (String[] values : data2) {
                System.out.println(Arrays.toString(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
