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

# instantiate the data structure in DriverData
driver_data = DriverToDriveData()
driver_data.get_csv_file_data()
driver_data.add_list_to_dict_by_index()
driver_data.add_list_to_dict2_by_()
driver = driver_data.DataStructure

target_value1 = input("Please enter a value to search for:\n")
result2 = search_nested_dict(driver, target_value1)

if result2 is not None:
    print(f"Value '{target_value1}' found ", len(result2), f" times at keys: {result2}")
else:
    print(f"Value '{target_value1}' not found in the nested dictionary.")
