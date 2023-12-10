# testSearchByCountry.py
import json
import unittest
import sys
import os
from io import StringIO
from unittest.mock import patch

# Include the path to finalVersion-danteData in sys.path
sys.path.append('../finalVersion-danteData')

from SearchCountry import search_conn_country

class TestSearchCountry(unittest.TestCase):
    def test_search_conn_country(self):
        folder_path = "TestSpotifyData"
        target_country = "US"

        expected_output = f"Tracks listened to in 'US': 2\n"

        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            search_conn_country(folder_path, target_country)
            printed_output = mock_stdout.getvalue()

            # Compare the captured output with the expected output
            self.assertEqual(expected_output, printed_output)

        target_country = "IT"
        expected_output = f"Tracks listened to in 'IT': 1\n"

        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            search_conn_country(folder_path, target_country)
            printed_output = mock_stdout.getvalue()

            # Compare the captured output with the expected output
            self.assertEqual(expected_output, printed_output)

if __name__ == '__main__':
    unittest.main()