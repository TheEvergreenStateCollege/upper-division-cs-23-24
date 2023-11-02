import csv
import pprint

class DriverToDriveData:

    def __init__(cls):
        cls.Line = []
        cls.dates = []
        cls.driver = []
        cls.driverAndDates = {}
        cls.filepath1 = "dsa-23au/java-dsa/pswish_insertion_app/scratch/DataSet_DSAau_pswish.csv"
        cls.filepath2 = "dsa-23au/java-dsa/pswish_insertion_app/scratch/Time_Driving_Spreadsheet.csv"

    def getCSVfile(cls):
        try:
            with open (cls.filepath1, "r") as cls.file:
                cls.Line = [line for line in cls.file]  # sets each line to a list
            with open (cls.filepath2, "r") as cls.file2:
                cls.Line2 = [line2 for line2 in cls.file2]
        except Exception as e:
            print("fault at getCSVfilewith error ", e)


    def addCSVtoLists(cls):
        lineLength = cls.Line.__len__()
        
        cls.driverKeys = []
        i = 1
        while i < lineLength:
            cls.Linelist = cls.Line[i].split(",")

            # Date, time and Driver constructed into a key
            driver1Key = (f"{cls.Linelist[1]}_{cls.Linelist[0]}_{cls.Linelist[6].replace('P', 'Paul')}")
            cls.driverKeys.append(driver1Key)
            i+=1

    def addCSVtoList2(cls):

        lineLength2 = cls.Line2.__len__()

        cls.driver2Keys = []
        j = 1

        while j < lineLength2:
            cls.Line2list = cls.Line2[j].split(",")
  
            driver2Key = (f"{cls.Line2list[1]}_{cls.Line2list[0]}_{cls.Line2list[6]}")
            cls.driver2Keys.append(driver2Key)
            j+=1

    def addListitemsToDict(cls):
        cls.addCSVtoLists()
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
                cls.DataStucture = dict_1  # set class wide data object

    def addListitemsToDict2(cls):
        cls.addCSVtoList2()
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
                cls.DataStucture.update(dict_2)  # set class wide data object
        cls.driverKeys.append(cls.driver2Keys)

    def print(cls):
        pp = pprint.PrettyPrinter(depth=4)
        pp.pprint(cls.DataStucture)
        pp.pprint(cls.driverKeys)

        # Accessing the info
        # with the key, and vlaue name, you can get the value
        # ["9/29/2023_1807_Nathan"]["Distance"])

    def operationMode(cls, selection, *kwargs) -> int:  # Basic Retrieval operation
        if selection == 1:
            key = input("Please enter a key, example '9/29/2023_1807_Nathan': ")
            value = input("please enter a value name, example 'Distance': ")
            print("For key :", key)
            print("The value is: ", value)
            compile = cls.DataStucture[key][value]
            print(compile)

        elif selection == 2:
            for keys in cls.driverKeys:
                print(keys) 
        
        elif selection == 3:
            x = int((cls.DataStucture['10/19/2023_0850_Paul']['Distance']).split()[0]) 
            y = int((cls.DataStucture['10/19/2023_1700_Paul']['Distance']).split()[0])
            print ('Distance in miles: ', x + y)
            num = ((x + y)*.14)
            formatted_num = f'{num:.2f}'
            print("Total cost (miles * cost per mile): $", formatted_num)

        elif selection == 4:
            cls.print()

      # elif selection == 5:
        #   Maybe a filterd date range for me from date1-date5, total miles
        #   Maybe a date range of 30 days, total miles
        #   Maybe with tone of thos edata points...  
        #   cls.DataStucture[key][value])
        
        #  '10/9/2023_1830_Paul': {'Date': '10/9/2023',
        #              'Dest': 'Millersylvania',
        #              'Detour enroute': 'no',
        #              'Distance': '11 miles',
        #              'Driver': 'P',
        #              'Elapsed': '22 mins',
        #              'Orig': 'Evergreen',
        #              'Time': '1830'},


def main():
    DTDD = DriverToDriveData()
    DTDD.getCSVfile()
    DTDD.addListitemsToDict()
    DTDD.addListitemsToDict2()
    DTDD.operationMode(int(input("Please select a mode of operation: \
                            \n 1. Enter 1 for data viewing by key, \
                            \n 2. Enter 2 to view avaialble keys, \
                            \n 3. Enter 3 to view a sample calculation \
                            \n 4. Enter 4 to view all avialable data in readable format. \
                            \n\n Please type your selection and push enter:  ")))

main()