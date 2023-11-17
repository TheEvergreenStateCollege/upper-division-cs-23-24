from DriverData import DriverToDriveData

def search_nested_dict(dictionary, target_value):
    result_keys = []
    results_values  = []
    # Iterate over key-value pairs in the dictionary
    for key, value in dictionary.items():
        # Check if the current value is equal to the target value
        if value == target_value:
            result_keys.append(key)
            results_values.append(value)

        # If the current value is a nested dictionary, recursively search it
        if isinstance(value, dict):
            nested_result = search_nested_dict(value, target_value)
            if nested_result:
                result_keys.append((key, nested_result))

    return result_keys if result_keys else None 

driver_data = DriverToDriveData()
driver_data.get_csv_file_data()
driver_data.add_list_to_dict_by_index()
driver_data.add_list_to_dict2_by_()
p = driver_data.DataStructure

dict1 = {'09/26/2023_1640_Paul': 
            {'Time': '1640', 'Date': '09/26/2023', 'Elapsed': '31 Mins', 'Orig': 'Evergreen', 'Dest': 'Millersylvania', 'Detour enroute': 'yes', 'Driver': 'P', 'Distance': '11 miles'}, 
         '09/27/2023_1130_Paul': 
            {'Time': '1130', 'Date': '09/27/2023', 'Elapsed': '21 mins', 'Orig': 'Millersylvania', 'Dest': 'Evergreen', 'Detour enroute': 'yes', 'Driver': 'P', 'Distance': '10 miles'}}

target_value1 = input("Please enter a value to search for:\n")
result2 = search_nested_dict(p, target_value1)

search_nested_dict(p, target_value1 )
if result2 is not None:
    # print(f""len(result2))
    print(f"Value '{target_value1}' found ", len(result2), f" times at keys: {result2}")
    
else:
    print(f"Value '{target_value1}' not found in the nested dictionary.")
