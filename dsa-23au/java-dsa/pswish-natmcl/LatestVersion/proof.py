# under development

def search_nested_dict(dictionary, target_value):
    # Initialize an empty list to store the keys where the target value is found
    result_keys = []

    # Iterate over key-value pairs in the dictionary
    for key, value in dictionary.items():
        # Check if the current value is equal to the target value
        if value == target_value:
            result_keys.append(key)

        # If the current value is a nested dictionary, recursively search it
        if isinstance(value, dict):
            nested_result = search_nested_dict(value, target_value)
            # If the nested result is not empty, append the current key and nested keys to the result
            if nested_result:
                result_keys.append((key, nested_result))

    return result_keys if result_keys else None  # Return the list of keys or None if not found

# Your dictionary
dict1 = {'09/26/2023_1640_Paul': 
            {'Time': '1640', 'Date': '09/26/2023', 'Elapsed': '31 Mins', 'Orig': 'Evergreen', 'Dest': 'Millersylvania', 'Detour enroute': 'yes', 'Driver': 'P', 'Distance': '11 miles'}, 
         '09/27/2023_1130_Paul': 
            {'Time': '1130', 'Date': '09/27/2023', 'Elapsed': '21 mins', 'Orig': 'Millersylvania', 'Dest': 'Evergreen', 'Detour enroute': 'yes', 'Driver': 'P', 'Distance': '10 miles'}}

# Target value
target_value1 = "Millersylvania"
result1 = search_nested_dict(dict1, target_value1)

if result1 is not None:
    print(f"Value '{target_value1}' found at keys: {result1}")
else:
    print(f"Value '{target_value1}' not found in the nested dictionary.")
