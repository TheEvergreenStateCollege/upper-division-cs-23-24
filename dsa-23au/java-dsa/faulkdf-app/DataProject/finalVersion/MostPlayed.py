import json
import os
from collections import Counter

def find_most_common_track(folder_path):
    try:
        # Initialize a Counter to count occurrences of track names
        track_counter = Counter()

        # Dictionary to store corresponding artist names for each track
        track_artist_dict = {}

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

                        # Count the occurrence of the track name
                        if track_name and track_name != "null":
                            track_counter[track_name] += 1

                            # Store corresponding artist name for the track
                            if track_name not in track_artist_dict:
                                track_artist_dict[track_name] = artist_name

        # Find the most common track name and its count
        most_common_track, count = track_counter.most_common(1)[0]

        # Retrieve the corresponding artist name for the most common track
        most_common_artist = track_artist_dict.get(most_common_track, 'N/A')

        # Print the results
        print(f"Most played track:")
        print(f"Track Name: {most_common_track}")
        print(f"Artist: {most_common_artist}")
        print(f"Count: {count}")

    except FileNotFoundError:
        print(f"Folder not found: {folder_path}")
    except json.JSONDecodeError:
        print(f"Error decoding JSON in file: {file_path}")
    except ValueError as e:
        print(str(e))

if __name__ == "__main__":
    # Specify the path to the folder containing JSON files
    folder_path = "SpotifyData"

    # Call the find_most_common_track function
    find_most_common_track(folder_path)

