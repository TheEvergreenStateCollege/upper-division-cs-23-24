# testMostPlayed.py
import json
import unittest
import sys
import os
from io import StringIO
from unittest.mock import patch

# Include the path to finalVersion-danteData in sys.path
sys.path.append('../finalVersion-danteData')

from MostPlayed import find_most_common_track

class TestMostPlayedTrack(unittest.TestCase):
    def test_find_most_common_track(self):
        folder_path = "TestSpotifyData"

        expected_output = (
            "Most played track:\n"
            "Track Name: TEST MOST PLAYED TRACK NAME\n"
            "Artist: TEST MOST PLAYED TRACK ARTIST\n"
            "Count: 2\n"
        )

        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            find_most_common_track(folder_path)

            printed_output = mock_stdout.getvalue()

            # Compare the captured output with the expected output
            self.assertEqual(expected_output, printed_output)


if __name__ == '__main__':
    unittest.main()