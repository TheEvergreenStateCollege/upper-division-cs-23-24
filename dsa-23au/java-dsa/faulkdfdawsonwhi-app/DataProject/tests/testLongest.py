# testLongest.py
import json
import unittest
import sys
import os
from io import StringIO
from unittest.mock import patch

# Include the path to finalVersion-danteData in sys.path
sys.path.append('../finalVersion-danteData')

from LongestPlayed import find_best_match_max_ms_played


class TestLongestPlayed(unittest.TestCase):
    def test_find_best_match_max_ms_played(self):
        
        folder_path = "TestSpotifyData"
        
        expected_output = (
            "Best match with the highest 'ms_played':\n"
            "Track Name: TEST LONGER SONG\n"
            "Album Artist: LONGER SONG ARTIST\n"
            "Time Played: 31536001000 milliseconds (525600.02 minutes)\n"
        )


        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            find_best_match_max_ms_played(folder_path)

            printed_output = mock_stdout.getvalue()

            # Compare the captured output with the expected output
            self.assertEqual(expected_output, printed_output)


if __name__ == '__main__':
    unittest.main()