# Written by Paul Swisher @pswish, November 2023
# This is version 1.29, 1.3 might soon include logging
# This is a dependency of DriverMain and generates the data structure
# This function takes two csv files from the config and puts them into a list : nested dictionary 

import csv
import re
import DriverConfig

class DriverToDriveData:
        
    def __init__(cls):  # declares important variables
        cls.dates = []
        cls.driver = []
        cls.DataStructure = {}
        cls.presorted_keys = []
        cls.sorted_keys = []

        # load the config files with the csv data
        cls.filepath1 = DriverConfig.filepath1
        cls.filepath2 = DriverConfig.filepath2

    def _add_list_to_dict_by_index(cls, filepath):  # Add the data for driver 1 to a dictionary
        _builder_dict = {}
        
        with open (filepath, "r") as file:  # Open csv file with a class object
            dict_reader = csv.DictReader(file)
            cls.Lines = []
            LineList = []
            driver_keys = []

            # add lines from dic_reader to a list for scripting
            for line in dict_reader:
                cls.Lines.append(line)
            
            for line in cls.Lines:
                # print(line)
                LineList.append(line.items())
                driver = line["Date"] + "_" + line["Time"] + "_" + line["Driver"].replace("P", "Paul")
                driver_keys.append(driver)

            # # construct the data structure first half
            i = 0
            for i in range(len(driver_keys)):
                key = driver_keys[i]
                value = cls.Lines[i]
                _builder_dict[key] = value
                cls.DataStructure.update(_builder_dict) 
            
            for key in driver_keys:
                cls.presorted_keys.append(key)
            
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

    def run_mode(cls):  # interior class main to enable cls objects
        cls._add_list_to_dict_by_index(cls.filepath1)
        cls._add_list_to_dict_by_index(cls.filepath2)
        cls._compile_driver_specific_lists()

        return cls.DataStructure  # Final data structure

def main():
    Driver = DriverToDriveData()
    Driver.run_mode()
    
if __name__ == main():
    main()
