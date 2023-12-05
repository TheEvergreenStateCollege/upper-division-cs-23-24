import json
import os


def convert_ms_to_minutes(milliseconds):
    return milliseconds / 60000

def find_min_ms_played_entry(json_data):
    # Assuming json_data is a dictionary with a "list" key containing a list of dictionaries
    if not isinstance(json_data, dict) or 'list' not in json_data:
        raise ValueError("Input JSON data should be a dictionary with a 'list' key containing a list of dictionaries.")

    track_list = json_data['list']

    if not track_list:
        raise ValueError("The track list is empty.")

    # Initialize variables to store the minimum entry and values
    min_entry = None
    min_track_name = None
    min_artist_name = None

    # Iterate through the track list
    for entry in track_list:
        # Check if the required fields have data before considering the entry
        track_name = entry.get('master_metadata_track_name')
        artist_name = entry.get('master_metadata_album_artist_name')

        if track_name is not None and artist_name is not None and track_name != "null" and artist_name != "null":
            # Check if this entry has a lower ms_played value than the current minimum
            if min_entry is None or entry['ms_played'] < min_entry['ms_played']:
                min_entry = entry
                min_track_name = track_name
                min_artist_name = artist_name

    if min_entry is not None:
        # Convert ms_played to minutes for the minimum entry
        minutes_played = convert_ms_to_minutes(min_entry['ms_played'])

        # Print the relevant information from the entry with the least ms_played
        print(f"Entry with the least 'ms_played':")
        print(f"Track Name: {min_track_name}")
        print(f"Album Artist: {min_artist_name}")
        print(f"ms_played: {min_entry['ms_played']} milliseconds ({minutes_played:.2f} minutes)")
    else:
        print("No valid entry found in the track list.")

if __name__ == "__main__":
    # Specify the path to the folder containing JSON files
    folder_path = "SpotifyData"

    try:
        # Iterate over all files in the folder
        for filename in os.listdir(folder_path):
            if filename.endswith(".json"):
                file_path = os.path.join(folder_path, filename)
                with open(file_path, 'r') as file:
                    json_data = json.load(file)
                    find_min_ms_played_entry(json_data)
    except FileNotFoundError:
        print(f"Folder not found: {folder_path}")
    except json.JSONDecodeError:
        print(f"Error decoding JSON in file: {file_path}")
    except ValueError as e:
        print(str(e))
