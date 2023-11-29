# Written by Paul Swisher @pswish, November 2023
# This is version 1.3
# This is a dependency of DriverMain and generates the data structure
# Time complexity of this script is O(m log m) where m is the number of lines in the csv file

import csv
import re
try:
    import DriverConfig
    from DriverConfig import theConfigurator
except: 
    from DriverFiles.DriverConfig import theConfigurator  # for pytest workaround

class DriverToDriveData():
        
    # This function takes two csv files from the config and puts them into a list : nested dictionary 
    def __init__(cls):  # declares important variables
        cls.DataStructure = {}   # Final data structure
        cls.presorted_keys = []
        cls.sorted_keys = []

        # get the csv filepaths from DriverConfig:
        cls.filepath1 = theConfigurator().get_filepath1()
        cls.filepath2 = theConfigurator().get_filepath2()

    def _add_list_to_dict_by_index(cls, filepath):
        _builder_dict = {}

        # Open the csv aka filepath in read mode
        with open(filepath, "r") as file:
            dict_reader = csv.DictReader(file)
            cls.Lines = []
            driver_keys = []

            for line in dict_reader:
                # Ensure required fields are present and not None
                date = line.get("Date", "")
                time = line.get("Time", "")
                driver = line.get("Driver", "")

                if None in (date, time, driver):
                    print("Error: Missing required fields in the CSV data.")
                    continue

                cls.Lines.append(line)
                # Generate the driver_keys, and format if needed
                driver = f"{date}_{time}_{driver.replace('P', 'Paul')}"
                driver_keys.append(driver)

            # DataStructure Constructor via update method per driver_key
            for index in range(len(driver_keys)):
                key = driver_keys[index]
                value = cls.Lines[index]
                _builder_dict[key] = value

            cls.DataStructure.update(_builder_dict)

            # Generate keys needed in a mode function
            [cls.presorted_keys.append(key) for key in driver_keys]

        cls.sorted_keys = sorted(cls.presorted_keys)  # final list of sorted keys


    def _compile_driver_specific_lists(cls):
        cls.driver1keys = []
        cls.driver2keys = []

        target_word = "Paul"
        target_word2 = "Nathan"

        for key in cls.DataStructure: # creating the driver specific key lists
            if re.search(target_word, key):
                cls.driver1keys.append(key)
            elif re.search(target_word2, key):
                cls.driver2keys.append(key)
                
        cls.driver1sorted = sorted(cls.driver1keys)  # creating variables to pass to outside early v1.2 methods
        cls.driver2sorted = sorted(cls.driver2keys)

    def run_mode(cls):  # interior class main to enable class objects
        # Step 1 and 2, run each csv separately
        # Step 3, after 1.2 refactor, need to generate lists to pass to mode functions
        
        cls._add_list_to_dict_by_index(cls.filepath1)
        cls._add_list_to_dict_by_index(cls.filepath2)
        cls._compile_driver_specific_lists()


def main():
    Driver = DriverToDriveData()
    Driver.run_mode()
    
if __name__ == main():
    main()
    