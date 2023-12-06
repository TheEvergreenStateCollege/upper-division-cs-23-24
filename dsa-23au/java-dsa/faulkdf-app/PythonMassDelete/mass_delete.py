import json

def remove_entry(json_file, key_to_remove, ):
    # Read the JSON file
  with open(json_file, 'r') as file:
        data = json.load(file)
        write_data = []

    # Identify and remove the entry
        for obj in data["list"]:
            print(obj)
            if key_to_remove in obj:
                del obj[key_to_remove]

        # Write the updated data back to the JSON file
        with open(json_file + '.out', 'w') as file:
            json.dump(data, file, indent=2)

# Example usage:
json_file_path = 'Streaming_History_Audio_2023_10.json'
key_to_remove = 'ip_addr_decrypted'  # Specify the key to identify the entry
# value_to_match = 'ip_addr_decrypted'  # Specify the value to match for removal

remove_entry(json_file_path, key_to_remove, )
