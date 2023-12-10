# testLongestListen.py
import json
import unittest
import sys
import os
from io import StringIO
from unittest.mock import patch

# Include the path to finalVersion-danteData in sys.path
sys.path.append('../finalVersion-dawsonData')

from LongestTimeSpentListening import find_longest_time_spent

class testLongestTimeSpentListening(unittest.TestCase):
    def test_LongestTimeSpentListening(self):
        folder_path = "TestSpotifyData"

        expected_output = (
            "Longest time spent on a single track:\n"
            "Track Name: TEST LONGER SONG\n"
            "Artist: LONGER SONG ARTIST\n"
            "Total Time Played: 31536001000 milliseconds (525600.02 minutes)\n"
        )

        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            find_longest_time_spent(folder_path)

            printed_output = mock_stdout.getvalue()

            # Compare the captured output with the expected output
            self.assertEqual(expected_output, printed_output)


if __name__ == '__main__':
    unittest.main()