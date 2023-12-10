# testShortest.py
import json
import unittest
import sys
import os
from io import StringIO
from unittest.mock import patch

# Include the path to finalVersion-danteData in sys.path
sys.path.append('../finalVersion-danteData')

from ShortestPlayed import find_best_match_min_ms_played


class TestLongestPlayed(unittest.TestCase):
    def test_find_best_match_min_ms_played(self):
        
        folder_path = "TestSpotifyData"
        
        expected_output = (
            "Best match with the least 'ms_played':\n"
            "Track Name: TEST SHORTEST SONG NAME\n"
            "Album Artist: TEST SHORTEST ARTIST\n"
            "Time Played: 1 milliseconds (0.00 minutes)\n"
        )


        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            find_best_match_min_ms_played(folder_path)

            printed_output = mock_stdout.getvalue()

            # Compare the captured output with the expected output
            self.assertEqual(expected_output, printed_output)


if __name__ == '__main__':
    unittest.main()