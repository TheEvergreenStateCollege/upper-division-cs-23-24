import unittest
from unittest.mock import patch
from io import StringIO
from . import DriverModes
from .DriverDatav1_2 import DriverToDriveData

class TestDriverModes(unittest.TestCase):

    def setUp(self):
        self.driver_data = DriverToDriveData()
        self.driver_data.run_mode()
        self.driver = self.driver_data.DataStructure

    def test_print_keys(self):
        with patch("sys.stdout", new_callable=StringIO) as mock_stdout:
            DriverModes.print_keys(self.driver_data)
            output = mock_stdout.getvalue().strip()
            print(output)
            # Add assertions based on expected output

    def test_pretty_print(self):
        with patch("sys.stdout", new_callable=StringIO) as mock_stdout:
            DriverModes.pretty_print(self.driver)
            output = mock_stdout.getvalue().strip()
            print(output)
            # Add assertions based on expected output

    # WIP tests
    # def test_sample_calculation(self):
    #     with patch("sys.stdout", new_callable=StringIO) as mock_stdout:
    #         DriverModes.sample_calculation(self.driver)
    #         output = mock_stdout.getvalue().strip()
    #         print(output)
            # Add assertions based on expected output

    # def test_search_by_key_and_value(driver_data, driver):
    #     pass
    # def test_search_by_value(driver):
    #     pass
    # def test_total_miles(driver_data, driver):
    #     pass
    # def test_total_time(driver_data, driver):
    #     pass
    # def test_calculate_distances(driver_data):
    #     pass
    # TODO Add more for the remaining functions.

if __name__ == "__main__":
    unittest.main()
