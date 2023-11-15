# Written by Paul Swisher @pswish, November 2023
# This function takes two csv files and puts them into a list : nested dictionary 
# Several functions are included to calculate different outputs 

import csv
import pprint
import DriverConfig

class DriverToDriveData:

    def __init__(cls):  # declares important variables
        cls.Line = []
        cls.dates = []
        cls.driver = []
        cls.driverAndDates = {}
        cls.DataStructure = {}
        
        cls.filepath1 = DriverConfig.filepath1
        cls.filepath2 = DriverConfig.filepath2

    def get_csv_file_data(cls):  # Opens both csv files and adds them to a variable 
        try:
            with open (cls.filepath1, "r") as cls.file, open (cls.filepath2, "r") as cls.file2:  # sets each in file line to a list, maybe refactor this
                cls.Line = [line for line in cls.file]
                cls.Line2 = [line2 for line2 in cls.file2]
        except Exception as e:
            print("fault at get_csv_file_data error ", e)
            

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

        cls.DataStructureFinal = cls.DataStructure

    def pretty_print(cls):  # prints out the nested dictionary in an easy to read format
        pp = pprint.PrettyPrinter(depth=4)
        pp.pprint(cls.DataStructureFinal)
        pp.pprint(cls.driverKeys)

        # Accessing the info
        # with the key, and value name, you can get the value of value
        # ["9/29/2023_1807_Nathan"]["Distance"])

    def operation_mode(cls, sel, *kwargs) -> int:  # Data manipulation operation
        try:
            cls.sorted_keys = sorted(cls.driverKeys)
            cls.driver1sorted = sorted(cls.driver1keys)
            cls.driver2sorted = sorted(cls.driver2Keys)
            selection = int(sel)

            if selection == 1:  # get data by key and value entry
                key = input("Please enter a key, example '9/29/2023_1807_Nathan': ")
                print("Here are the available keys:", cls.Line[0].replace(",", ", "))
                value = input("please enter a value name, example 'Distance': ")
                print("For key :", key)
                print("The value is: ", value)
                compile = cls.DataStructure[key][value]
                print(compile)

            elif selection == 2:  # Prints all keys sorted and unsorted 
                print("Being Unsorted:")
                for keys in cls.driverKeys:
                    print(keys) 
                print("\n--*-- End unsorted --*\n")
                
                print("Begin sorted:\n")
                i = 0
                for s_key in cls.sorted_keys:
                    print(i, s_key)
                    i += 1
                print("\n--*-- End Sorted --*--\n")
            
            elif selection == 3:  # prints example calculation for miles * cost per mile
                x = int((cls.DataStructure['10/19/2023_0850_Paul']['Distance']).split()[0]) 
                y = int((cls.DataStructure['10/19/2023_1700_Paul']['Distance']).split()[0])
                num = ((x + y)*.16)  # miles * cost per mile
                formatted_num = f'{num:.2f}'
                
                print('Distance in miles: ', x + y)
                print("Total cost (miles * cost per mile($0.16)): $", formatted_num)

            elif selection == 4:  # pretty prints all data
                cls.pretty_print()

            elif selection == 5:  # prints out total miles driven for both drivers
                total_miles = 0
                for key in cls.sorted_keys:
                    compile = cls.DataStructure[key]["Distance"]

                    x = int(compile.split()[0])
                    total_miles += x
                print("Total combined miles: ", total_miles)
                return total_miles

            elif selection == 6:  # Choose 1, 2, both drivers for start and end data on distances
                total_miles = 0
                driver = input("For selection: enter 1 or 2, for driver 1, driver 2 or any other number for both drivers: ")
                try: 
                    inputDriver = int(driver)
                except Exception as e:
                    print ("error in input", e)
                if inputDriver == 1:
                    print(cls.driver1sorted)  # Prints out Driver1 keys to pick a range manually

                    inputStart = input("Enter a start: ")
                    inputStop =  input("Enter an end: ")
                    start = cls.driver1sorted.index(inputStart)
                    end = cls.driver1sorted.index(inputStop)
                    select = cls.driver1sorted[start:end]
    
                elif inputDriver == 2:  # Prints out Driver2 keys to pick a range manually
                    print(cls.driver2sorted)
                    
                    inputStart = input("Enter a start: ")
                    inputStop =  input("Enter an end: ")
                    start = cls.driver2sorted.index(inputStart)
                    end = cls.driver2sorted.index(inputStop)
                    select = cls.driver2sorted[start:end]
                else:
                    inputStart = input("Enter a start: ")
                    inputStop =  input("Enter an end: ")
                    start = cls.sorted_keys.index(inputStart)
                    end = cls.sorted_keys.index(inputStop)
                    select = cls.sorted_keys[start:end]

                for i in select:  # Takes the selection data from above and calculates a hardcoded distance
                    compile = cls.DataStructure[i]["Distance"]
                    x = int(compile.split()[0])
                    total_miles += x
                    print(i, compile)
                print("Total combined miles: ", total_miles)
            
            elif selection == 7:
                total_time = 0
                for key in cls.sorted_keys:
                    compile = cls.DataStructure[key]["Elapsed"]
                    x = int(compile.split()[0])
                    total_time += x
                print("Total combined minutes: ", total_time)
                return total_time
            else: 
                print("Nothing to do")


            cls.DataStructureFinal = cls.DataStructure
        except Exception as e:
            print("Error in operation mode, please make sure you typed a number: ", e)
        finally:
            replay = input("Push enter to run another operation or type 'exit' to quit: ")

            if replay == "exit".lower():  # replay the program main loop or type exit to quit
                print("End of program...")
            else: 
                main()


        #   TODO      
        #   DONE: Move csv file paths to a config file 
        #   DONE: Maybe a filtered date range for me from date1-date5, total miles (done_)
        #   DONE: Maybe a date range of 30 days, total miles (done by key)
        #   XXX: Maybe with ton of those data points... (forget what this was for)  
        #   DONE: Add a print keys with index to the individual driver and then make the selection take the number from the printed list for application
        #   TODO add functions for each value than can be calculated upon (Miles, time spent driving)
            # Total time for a given range
            #     is this range more or less than all ranged averages
        #   TODO add a search the data function in the operation mode
        #   TODO prep for backend/web interfacing.
        
        #   TODO v1.2 break the large function up into separate files 
        #   cls.DataStructure[key][value])  # the nice way for accessing data

        # Example:
        #  '10/9/2023_1830_Paul': {'Date': '10/9/2023',
        #              'Dest': 'Millersylvania',
        #              'Detour enroute': 'no',
        #              'Distance': '11 miles',
        #              'Driver': 'P',
        #              'Elapsed': '22 mins',
        #              'Orig': 'Evergreen',
        #              'Time': '1830'},

def main():
    Driver = DriverToDriveData()
    Driver.get_csv_file_data()
    Driver.add_list_to_dict_by_index()
    Driver.add_list_to_dict2_by_()
    Driver.operation_mode(input("Please select a mode of operation: \
                            \n 1. Enter 1 for data viewing by key. \
                            \n 2. Enter 2 to view available keys. \
                            \n 3. Enter 3 to view a sample calculation \
                            \n 4. Enter 4 to view all available data in readable format. \
                            \n 5. Enter 5 to view total combined miles driven for both drivers in all data. \
                            \n 6. Enter 6 to view a range of n to k sorted example miles data. \
                            \n 7. Enter 7 view total combined time driven. \
                            \n\n Please type your selection and push enter:  "))

if __name__ == "__main__":
    main()