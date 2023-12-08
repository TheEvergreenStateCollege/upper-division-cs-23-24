# testLongest.py
import json
import unittest
import sys
import os
import io
from unittest.mock import patch

# Include the path to finalVersion-danteData in sys.path
sys.path.append('../finalVersion-danteData')

from LongestPlayed import find_best_match_max_ms_played

class TestLongestPlayed(unittest.TestCase):
    def test_find_best_match_max_ms_played(self):
        
        test_folder_path = "TestSpotifyData"


        with patch('sys.stdout', new_callable=io.StringIO) as mock_stdout:
            find_best_match_max_ms_played(test_folder_path)
            
            printed_output = mock_stdout.getvalue()

            self.assertEqual("Track Name: TEST LONG SONG", printed_output)
            self.assertIn("Album Artist: LONG SONG ARTIST", printed_output)
            self.assertIn("Time Played: 31536000000 (525600 minutes)", printed_output)

if __name__ == '__main__':
    unittest.main()