def search_nested_dict(dictionary, target_value):
    # Iterate over key-value pairs in the dictionary
    for key, value in dictionary.items():
        # Check if the current value is equal to the target value
        if value == target_value:
            return key  # Return the key where the value was found

        # If the current value is a nested dictionary, recursively search it
        if isinstance(value, dict):
            nested_result = search_nested_dict(value, target_value)
            if nested_result is not None:
                return key, nested_result  # Return both current key and nested key
    return None

mainDict = DriverData

# Example usage:
nested_dict = {
    'key1': 'value1','key2': {'key3': 'value3','key4': {'key5': 'value5','key6': 'value6'}},
    'key7': 'value7'}

dict1 = {'09/26/2023_1640_Paul': 
            {'Time': '1640', 'Date': '09/26/2023', 'Elapsed': '31 Mins', 'Orig': 'Evergreen', 'Dest': 'Millersylvania', 'Detour enroute': 'yes', 'Driver': 'P', 'Distance': '11 miles'}, 
         '09/27/2023_1130_Paul': 
            {'Time': '1130', 'Date': '09/27/2023', 'Elapsed': '21 mins', 'Orig': 'Millersylvania', 'Dest': 'Evergreen', 'Detour enroute': 'yes', 'Driver': 'P', 'Distance': '10 miles'}}

target_value = 'value5'
target_value1 = "Millersylvania"
result = search_nested_dict(nested_dict, target_value)
result1 = search_nested_dict(dict1, target_value1)


if result1 is not None:
    print(f"Value '{target_value1}' found at keys: {result1}")
else:
    print(f"Value '{target_value1}' not found in the nested dictionary.")
