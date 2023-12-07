import json
import os

def search_conn_country(folder_path, target_country):
    try:
        # Convert the target country to lowercase for case-insensitive comparison
        target_country_lower = target_country.lower()

        # Initialize a counter for matching entries
        matching_entries = 0

        # Iterate over all files in the folder
        for filename in os.listdir(folder_path):
            if filename.endswith(".json"):
                file_path = os.path.join(folder_path, filename)
                with open(file_path, 'r') as file:
                    json_data = json.load(file)
                    track_list = json_data.get('list', [])

                    for entry in track_list:
                        country = entry.get('conn_country')

                        # Convert the country from the JSON data to lowercase for comparison
                        if country and country.lower() == target_country_lower:
                            matching_entries += 1

        # Print the number of matching entries
        print(f"Tracks listened to in '{target_country}': {matching_entries}")

    except FileNotFoundError:
        print(f"Folder not found: {folder_path}")
    except json.JSONDecodeError:
        print(f"Error decoding JSON in file: {file_path}")
    except ValueError as e:
        print(str(e))

if __name__ == "__main__":
    # Specify the path to the folder containing JSON files
    folder_path = "SpotifyData"

    # Prompt the user for the conn_country they want to search
    target_country = input("Enter two letter country code: ")

    # Call the search_conn_country function
    search_conn_country(folder_path, target_country)

