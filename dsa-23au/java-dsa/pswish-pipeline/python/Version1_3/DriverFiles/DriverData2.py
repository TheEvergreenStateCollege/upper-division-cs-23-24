# Written by Paul Swisher @pswish, November 2023
# This is version 1.29, 1.3 might soon include logging
# This is a dependency of DriverMain and generates the data structure
# This function takes two csv files from the config and puts them into a list : nested dictionary 

import csv
import re
try:
    from DriverConfig import theConfigurator
except: 
    from DriverFiles.DriverConfig import theConfigurator  # for test workaround

class DriverToDriveData():
        
    def __init__(cls):  # declares important variables
        cls.DataStructure = {}
        cls.presorted_keys = []
        cls.sorted_keys = []

        # load the config files with the csv data
        cls.filepath1 = theConfigurator().get_filepath1()
        cls.filepath2 = theConfigurator().get_filepath2()

    def _add_list_to_dict_by_index(cls, filepath):  # Add the data for driver 1 to a dictionary
        _builder_dict = {}
        
        with open (filepath, "r") as file:  # Open csv file with a class object
            dict_reader = csv.DictReader(file)
            cls.Lines = []
            driver_keys = []

            # add lines from dic_reader to a list for scripting, Lines needed for scripting dic_reader
            [cls.Lines.append(line) for line in dict_reader]
            
            # generate the driver keys
            for line in cls.Lines:
                driver = line["Date"] + "_" + line["Time"] + "_" + line["Driver"].replace("P", "Paul")
                driver_keys.append(driver)

            # construct via update the data structure per driver
            index = 0
            for index in range(len(driver_keys)):
                key = driver_keys[index]
                value = cls.Lines[index]
                _builder_dict[key] = value
                cls.DataStructure.update(_builder_dict) 
            
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
