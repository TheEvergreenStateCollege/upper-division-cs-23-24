# This is a dependency of DriverMain

import pprint

def print_keys(driver): # Method for option 1, prints all keys sorted and unsorted, time complexity O(n log n)
    try:
        index = 0

        for s_key in sorted(driver.sorted_keys):
            print(index, s_key)
            index += 1
        print("\n--*-- End Sorted --*--\n")
    except Exception as e:
        print(e)

def pretty_print(data):  # option #2, prints out the nested dictionary in an easy to read format, time complexity is O(n)
    print("pretty print called")
    pp = pprint.PrettyPrinter(depth=4)
    pp.pprint(data)

def search_by_key_and_value(driver_data, driver):  # Option 4, This method puts the generated keys into a list with a numbered key for selection, which the user picks from. Time complexity is O(n)
    numbered_keys = {} 
    try:
        keys = list(sorted(driver.keys())) # sorting them to print them out in order
        index = 1  # simple counter start to create an index key
        for key in keys: # this for loop is the cause of the O(n) time complexity
            print(index, key)  # printing for selection purposes
            numbered_keys[index] = key
            index += 1
        
        index_key = input("Please enter an index, example: 7:\n ")
        index_key = int(index_key)
        if index_key > len(keys):
             print("You entered an input outside the range of the provided list, press enter to continue")

        driver_key = numbered_keys[int(index_key)]
        print("Accessing index:", index_key, "of", len(keys))
        print("You have selected: ", driver_key)
        print("\nChoose an available 'value' as a key:", *driver_data.Lines[0].keys())
        
        driver_value = input("Please enter a value name, example 'Distance': ")
        print("For key:", driver_key)
        print("The value is:", driver_value)

        if driver_key in driver and driver_value in driver[driver_key]:
            compile_result = driver[driver_key][driver_value]
            print("Result:", compile_result)
        else:
            print("Key or value not found in the driver data.")

    except Exception as e:
        print(e)

def sample_calculation(driver_data, driver): # Option #3, prints example calculation for miles * cost per mile. Time complexity is O(1)
    x = int((driver['10/19/2023_0850_Paul']['Distance']).split()[0]) 
    y = int((driver['10/19/2023_1700_Paul']['Distance']).split()[0])
    num = ((x + y)*.20)  # miles * cost per mile
    formatted_num = f'{num:.2f}'
    
    print('Distance in miles: ', x + y)
    print("Total cost for key 10/19/2023_0850_Paul (miles * cost per mile($0.20)): $", formatted_num)

def total_miles(driver_data, driver=dict):  # Option 6, prints out total miles driven for both drivers. Time complexity is O(n)
    total_miles = 0
    for key in driver_data.sorted_keys:
        compile = driver[key][str('Distance')]

        x = int(compile.split()[0])
        total_miles += x
    total_cost = total_miles *.20
    formatted_num = f'{total_cost:.2f}'

    print("Total combined miles: ", total_miles)
    print("Total cost (miles * cost per mile($0.20)): $", formatted_num)
    return total_miles

def calculate_distances(cls):  #Option 8, Choose 1, 2, both drivers for start and end data on distances, Time complexity is O(k) where k is the selection size
    total_miles = 0
    inputDriver = 3  # 3 to ensure any entry not 1 or 2 is 3 for all keys
    driver_input = input("For selection: enter 1 or 2, for driver 1, driver 2 or any other number for both drivers: ")
    try: 
        inputDriver = int(driver_input)
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

    elif inputDriver >= 3:
        print(cls.sorted_keys)
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
    
def total_time(driver_data, driver=dict): # Option 7, simple calculation to sum all time values. Time complexity is O(n)
    total_time = 0
    for key in driver_data.sorted_keys:
        compile = driver[key][str("Elapsed")]
        x = int(compile.split()[0])
        total_time += x
    print("Total combined minutes: ", total_time)

def search_by_value(driver):  # Option 5, Recursive search for all values in the nested dictionary and print out the number of occurrences. Time complexity is O(n)
    def search_nested_dict(dictionary, target_value): # This is a recursive search
        result_keys = []
        results_values  = []
        # Iterate over key-value pairs in the dictionary
        for key, value in dictionary.items():
            # Check if the current value is equal to the target value
            if value == target_value:
                result_keys.append(key)
                results_values.append(value)  # not yet implemented in v1.2

            # If the current value is a nested dictionary, recursively search it
            if isinstance(value, dict):
                nested_result = search_nested_dict(value, target_value)
                if nested_result:
                    result_keys.append((key, nested_result))

        return result_keys if result_keys else None 

    target_value1 = input("Please enter a value to search for:\n")
    
    if target_value1 == "Paul":  # input conditional
        target_value1 = "P"
    result2 = search_nested_dict(driver, target_value1)

    if result2 is not None:
        print(f"Value '{target_value1}' found at keys:")
        [print(*item) for item in sorted(result2)]
        print ("Total items found:",len(result2))
    else:
        print(f"Value '{target_value1}' not found in the nested dictionary.")                            

# **--------- End of calculation logic, 8 total as of v1.2 ------------**