Program DriverToDriveData

    Class DriverToDriveData

        // Variables declaration
        Declare Line, Line2, driverKeys, driver1keys, driver2Keys as Lists
        Declare filepath1, filepath2 as Strings
        Declare DataStructure as a Map

        // Constructor initializes variables
        Method DriverToDriveData
            Initialize Line, Line2, driverKeys, driver1keys, driver2Keys as new ArrayLists
            Initialize DataStructure as a new HashMap
            Set filepath1 and filepath2

        // Method to read CSV files and store lines
        Method getCSVfile
            Try
                Open filepath1 with BufferedReader and FileReader
                Read lines into Line
                Close filepath1
                Open filepath2 with BufferedReader and FileReader
                Read lines into Line2
                Close filepath2
            Catch IOException
                Print error message

        // Method to create driver keys from first CSV file
        Method addCSVtoLists
            For each line in Line
                Split line and create a unique key
                Add key to driverKeys and driver1keys

        // Method to create driver keys from second CSV file
        Method addCSVtoList2
            For each line in Line2
                Split line and create a unique key
                Add key to driver2Keys

        // Method to add data from first CSV file to dictionary
        Method addListitemsToDict
            Call addCSVtoLists
            Open filepath1 with BufferedReader and FileReader
            Read data and add to DataStructure using driverKeys as keys

        // Method to add data from second CSV file to dictionary
        Method addListitemsToDict2
            Call addCSVtoList2
            Open filepath2 with BufferedReader and FileReader
            Read data and add to DataStructure using driver2Keys as keys
            Add driver2Keys to driverKeys

        // Method to print nested dictionary in a readable format
        Method print
            For each entry in DataStructure
                Print key and value in a readable format

        // Method for various data manipulation operations
        Method operationMode (sel)
            Try
                Sort driverKeys
                Get user selection (sel)
                Perform different operations based on selection
                    - Get and print data by key and value
                    - Print all keys (sorted and unsorted)
                    - Perform and print example calculation
                    - Pretty print all data
                    - Print total miles driven
                    - Select a range of data and calculate total miles
            Catch Exception
                Print error message
            Finally
                Prompt for another operation or exit

    // Main program execution
    Method main
        Create instance of DriverToDriveData
        Call getCSVfile
        Call other methods based on program logic

    // Entry point
    If program is main
        Call main method

End Program