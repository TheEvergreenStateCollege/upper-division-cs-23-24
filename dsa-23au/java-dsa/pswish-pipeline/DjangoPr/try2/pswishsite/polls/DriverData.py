# Written by Paul Swisher @pswish, November 2023
# This function takes two csv files and puts them into a list : nested dictionary 
# Several functions are included to calculate different outputs 

import csv
import pprint
from polls import DriverConfig

class DriverToDriveData:

    def __init__(cls):  # declares important variables
        cls.Line = []
        cls.dates = []
        cls.driver = []
        cls.driverAndDates = {}
        cls.sorted_keys = []
        cls.filepath1 = DriverConfig.filepath1
        cls.filepath2 = DriverConfig.filepath2

    def get_csv_file_data(cls):  # Opens both csv files and adds them to a variable 
        try:
            with open (cls.filepath1, "r") as cls.file, open (cls.filepath2, "r") as cls.file2:  # sets each in file line to a list, maybe refactor this
                cls.Line = [line for line in cls.file]
                cls.Line2 = [line2 for line2 in cls.file2]
        except Exception as e:
            print("fault at getCSVfilewith error ", e)
            

    def create_driver1_keys(cls):  # creates unique keys for driver 1 data construction
        try:
            lineLength = cls.Line.__len__()
        
            cls.driverKeys = []
            cls.driver1keys = []
            i = 1
            while i < lineLength:
                cls.Linelist = cls.Line[i].split(",")

                # Date, time and driver constructed into a unique key
                driver1Key = (f"{cls.Linelist[1]}_{cls.Linelist[0]}_{cls.Linelist[6].replace('P', 'Paul')}")
                
                cls.driverKeys.append(driver1Key)  # main driver key list
                cls.driver1keys.append(driver1Key) # driver1 key list
                i+=1
        except Exception as e:
            print(e)

    def create_driver2_keys(cls): # creates unique keys for driver 2 data construction

        lineLength2 = cls.Line2.__len__()

        cls.driver2Keys = []
        j = 1

        while j < lineLength2:
            cls.Line2list = cls.Line2[j].split(",")
            driver2Key = (f"{cls.Line2list[1]}_{cls.Line2list[0]}_{cls.Line2list[6]}")
            cls.driver2Keys.append(driver2Key)
            j+=1

    def add_list_to_dict_by_index(cls):  # Add the data for driver 1 to a dictionary
        cls.create_driver1_keys()
        dict_1 = {}
        
        with open (cls.filepath1, "r") as f:  # Open csv file with a class object
            dict_reader = csv.DictReader(f)
            i = 0
            lines = []

                # add lines from dic_reader to a list for scripting
            for line in dict_reader:
                lines.append(line)

                # construct the data structure 
            for i in range(len(cls.driverKeys)):
                key = cls.driverKeys[i]
                value = lines[i]
                dict_1[key] = value
                cls.DataStructure = dict_1  # set class wide data object

    def add_list_to_dict2_by_(cls): # Add the data for driver 2 to a dictionary
        cls.create_driver2_keys()
        dict_2 = {}
        
        with open (cls.filepath2, "r") as f:  # Open csv file with a class object
            dict_reader = csv.DictReader(f)
            i = 0
            lines = []

                # add lines from dic_reader to a list for scripting
            for line in dict_reader:
                lines.append(line)

                # construct the data structure 
            for i in range(len(cls.driver2Keys)):
                key = cls.driver2Keys[i]
                value = lines[i]
                dict_2[key] = value
                cls.DataStructure.update(dict_2)  # set class wide data object
        
        for key2 in cls.driver2Keys:  # adds driver 2 keys to main driverkeys list
            cls.driverKeys.append(key2)
        
            cls.sorted_keys = sorted(cls.driverKeys)
            cls.driver1sorted = sorted(cls.driver1keys)
            cls.driver2sorted = sorted(cls.driver2Keys)



def main():
    Driver = DriverToDriveData()
    Driver.get_csv_file_data()
    Driver.add_list_to_dict_by_index()
    Driver.add_list_to_dict2_by_()
    # Driver.operation_mode()

if __name__ == "__main__":
    main()