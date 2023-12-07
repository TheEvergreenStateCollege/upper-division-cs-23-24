# testLongest.py
import unittest
import sys
import os

# Include the path to finalVersion-danteData in sys.path
sys.path.append('../finalVersion-danteData')

from LongestPlayed import find_long_played_song

def test_find_longest_played_song():
    test_json_data = {
            "ts": "2023-05-14T07:26:05Z",
            "username": "Test",
            "platform": "windows",
            "ms_played": 31536000000,
            "conn_country": "UK",
            "user_agent_decrypted": "unknown",
            "master_metadata_track_name": "TEST LONG SONG",
            "master_metadata_album_artist_name": "LONG SONG ARTIST",
            "master_metadata_album_album_name": "null",
            "spotify_track_uri": "spotify:track:5P9FXrER6wiRqG2E12rMO7",
            "episode_name": null,
            "episode_show_name": null,
            "spotify_episode_uri": null,
            "reason_start": "trackdone",
            "reason_end": "trackdone",
            "shuffle": false,
            "skipped": false,
            "offline": false,
            "offline_timestamp": 1684048881,
    }

    with open('test_data.json', 'w') as file:
        json.dump(test_json_data.json, file)

    longest_song = test_find_longest_played_song('.')

    assert longest_song['master_metadata_track_name'] == 'TEST LONG SONG'
    assert longest_song['ms_played'] == 31536000000

    os.remove('test_data.json')