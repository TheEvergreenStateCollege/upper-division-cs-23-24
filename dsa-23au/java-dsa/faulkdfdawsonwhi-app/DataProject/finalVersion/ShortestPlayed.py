import json
import os

# converting the milliseconds in the file to seconds
def convert_ms_to_minutes(milliseconds):
    return milliseconds / 60000

def find_best_match_min_ms_played(folder_path):
    best_entry = None
    best_ms_played = float('inf')

    try:
        # Iterate over all files in the folder
        for filename in os.listdir(folder_path):
            if filename.endswith(".json"):
                file_path = os.path.join(folder_path, filename)
                with open(file_path, 'r') as file:
                    json_data = json.load(file)
                    track_list = json_data.get('list', [])

                    for entry in track_list:
                        track_name = entry.get('master_metadata_track_name')
                        artist_name = entry.get('master_metadata_album_artist_name')
                        ms_played = entry.get('ms_played', 0)

                        if track_name is not None and artist_name is not None \
                                and track_name != "null" and artist_name != "null" \
                                and ms_played < best_ms_played:
                            best_ms_played = ms_played
                            best_entry = entry

    # File search error cases
    except FileNotFoundError:
        print(f"Folder not found: {folder_path}")
    except json.JSONDecodeError:
        print(f"Error decoding JSON in file: {file_path}")
    except ValueError as e:
        print(str(e))

    if best_entry is not None:
        # Convert ms_played to minutes for the best match
        minutes_played = convert_ms_to_minutes(best_entry['ms_played'])

        # Print the relevant information for the best match
        print(f"Best match with the least 'ms_played':")
        print(f"Track Name: {best_entry.get('master_metadata_track_name', 'N/A')}")
        print(f"Album Artist: {best_entry.get('master_metadata_album_artist_name', 'N/A')}")
        print(f"Time Played: {best_entry['ms_played']} milliseconds ({minutes_played:.2f} minutes)")
    else:
        print("No valid entry found in the track lists.")

if __name__ == "__main__":
    # Specify the path to the folder containing JSON files
    folder_path = "SpotifyData"

    find_best_match_min_ms_played(folder_path)
