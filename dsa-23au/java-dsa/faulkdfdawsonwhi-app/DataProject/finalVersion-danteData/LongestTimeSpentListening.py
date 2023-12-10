import json
import os

def find_longest_time_spent(folder_path):
    try:
        # Dictionary to store the total playtime for each track
        track_playtime = {}

        # Iterate over all files in the folder
        for filename in os.listdir(folder_path):
            if filename.endswith(".json"):
                file_path = os.path.join(folder_path, filename)
                with open(file_path, 'r') as file:
                    json_data = json.load(file)
                    track_list = json_data.get('list', [])

                    for entry in track_list:
                        track_name = entry.get('master_metadata_track_name')
                        ms_played = entry.get('ms_played', 0)

                        # Accumulate playtime for each track
                        if track_name and track_name != "null":
                            track_playtime[track_name] = track_playtime.get(track_name, 0) + ms_played

        # Find the track with the longest total playtime
        longest_track = max(track_playtime, key=track_playtime.get)
        longest_time = track_playtime[longest_track]

        # Convert milliseconds to minutes for readability
        longest_time_minutes = longest_time / (1000 * 60)

        # Print the results
        print(f"Longest time spent on a single track:")
        print(f"Track Name: {longest_track}")
        print(f"Total Time Played: {longest_time} milliseconds ({longest_time_minutes:.2f} minutes)")

    except FileNotFoundError:
        print(f"Folder not found: {folder_path}")
    except json.JSONDecodeError:
        print(f"Error decoding JSON in file: {file_path}")
    except ValueError as e:
        print(str(e))

if __name__ == "__main__":
    # Specify the path to the folder containing JSON files
    folder_path = "SpotifyData"

    # Call the find_longest_time_spent function
    find_longest_time_spent(folder_path)