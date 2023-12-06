package driverToDriveData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;

public class DriverToDriveData {
    private List<String> time;
    private List<String> date;
    private List<String> Elapsed;
    private List<String> Orig;
    private List<String> Dest;
    private Map<String, String> Detour_enroute;
    private String Driver;
    private String Distance;


    public DriverToDriveData() {
        time = new ArrayList<>();
        date = new ArrayList<>();
        Elapsed = new ArrayList<>();
        Orig = new ArrayList<>();
        Dest = new ArrayList<>();
        Detour_enroute = new HashMap<>();
        Driver = "DataSet_DSAau_pswish_test1.csv";
        Distance = "Time_Driving_Spreadsheet_test5.csv";
    }
    // Method to read data from both CSV files
    public void getCSVfile() {
        try (BufferedReader br = new BufferedReader(new FileReader(Driver))) {
            String s;
            while ((s = br.readLine()) != null) {
                time.add(s);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        try (BufferedReader br2 = new BufferedReader(new FileReader(Distance))) {
            String s;
            while ((s = br2.readLine()) != null) {
                date.add(s);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    // Method to extract keys from the first CSV file
    public void addCSVtoLists() {
        for (String s : time) {
            String[] parts = s.split(",");
            // Assuming the key is created by combining certain parts of the line
            String key = parts[0] + "_" + parts[1]; // Modify as needed
            Elapsed.add(key);
            Orig.add(key);
        }
    }
    // Method to extract keys from the second CSV file
    public void addCSVtoList2() {
        for (String s : date) {
            String[] parts = s.split(",");
            String key = parts[0] + "_" + parts[1]; // Modify as needed
            Dest.add(key);
        }
    }
    // Method to populate the dataStructure map from the first CSV file
    public void addListitemsToDict() {
        addCSVtoLists();
        try (BufferedReader br = new BufferedReader(new FileReader(Driver))) {
            String line;
            br.readLine(); // Skip the header line if there is one
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                // Assuming the first column is the key and the second column is the value
                String key = parts[0]; // Modify this according to how you create keys in addCSVtoLists
                String value = parts[1]; // build a more complex object from parts
                Detour_enroute.put(key, value);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    // Method to populate the dataStructure map from the second CSV file
    public void addListitemsToDict2() {
        addCSVtoList2();
        try (BufferedReader br = new BufferedReader(new FileReader(Distance))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                // Adjust the key and value creation based on the structure of the CSV
                String key = parts[0] + "_" + parts[1]; // Modify this as needed
                String value = parts[1]; // Or build a more complex object from parts

                // Decide how to handle existing keys: overwrite, merge, etc.
                if (Detour_enroute.containsKey(key)) {
                    // Handle existing key (merge, overwrite, etc.)
                    String existingValue = Detour_enroute.get(key);
                    // Concatenate values
                    Detour_enroute.put(key, existingValue + ", " + value);
                } else {
                    Detour_enroute.put(key, value);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    // Method to print all key-value pairs
    public void print() {
        for (Map.Entry<String, String> entry : Detour_enroute.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // Method handling different operations based on user selection
    public void operationMode(int sel) {
        try {
            // Sort driverKeys if needed
            Collections.sort(Elapsed);

            switch (sel) {
                case 1:
                    // Perform operation 1 (print all keys)
                    printAllKeys();
                    break;
                case 2:
                    // Perform operation 2 (search for a specific key)
                    searchByKey();
                    break;
                case 3:
                    // Perform operation 3 (calculate and display some statistics)
                    calculateStatistics();
                    break;
                case 4:
                    // Perform operation 4 (pretty print all data)
                    prettyPrintData();
                    break;
                // ... other cases for different selections
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error in operation mode: " + e.getMessage());
        } finally {
            // Prompt for another operation or exit
            promptForNextAction();
        }
    }


private void printAllKeys() {
    for (String key : Elapsed) {
        System.out.println(key);
    }
}

    private void searchByKey() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the key to search for: ");
        String key = scanner.nextLine();

        if (Detour_enroute.containsKey(key)) {
            System.out.println("Value: " + Detour_enroute.get(key));
        } else {
            System.out.println("Key not found.");
        }
    }

    private void calculateStatistics() {
        Map<String, Integer> countMap = new HashMap<>();
        for (String key : Detour_enroute.keySet()) {
            String driver = key.split("_")[0]; // Assuming driver's name is the first part of the key
            countMap.put(driver, countMap.getOrDefault(driver, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            System.out.println("Driver " + entry.getKey() + " has " + entry.getValue() + " entries.");
        }
    }

    private void prettyPrintData() {
        for (Map.Entry<String, String> entry : Detour_enroute.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    private void promptForNextAction() {
        System.out.println("Enter any key to continue...");
        new Scanner(System.in).nextLine();
    }


        public static void main(String[] args) {
            DriverToDriveData dtdd = new DriverToDriveData();
            dtdd.getCSVfile();

            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    // User interface for operation selection
                    System.out.println("\nSelect an operation:");
                    // Menu options
                    System.out.println("1. Print all keys");
                    System.out.println("2. Search for a specific key");
                    System.out.println("3. Calculate and display some statistics");
                    System.out.println("4. Pretty print all data");
                    System.out.println("5. Exit");
                    System.out.print("Enter your choice: ");

                    int choice = scanner.nextInt();

                    if (choice == 5) {
                        System.out.println("Exiting program.");
                        break;
                    }

                    dtdd.operationMode(choice);
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }



}
