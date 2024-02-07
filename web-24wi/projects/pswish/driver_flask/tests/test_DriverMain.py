import unittest
from unittest.mock import patch
from io import StringIO
from DriverFiles import DriverModes
from DriverFiles.DriverData2 import DriverToDriveData

class TestDriverModes(unittest.TestCase):

    def setUp(self):
        self.driver_data = DriverToDriveData()
        self.driver_data.run_mode()
        self.driver = self.driver_data.DataStructure
    
    def test_driver_data_creation(cls):
        driver_data = DriverToDriveData()
        assert isinstance(driver_data, DriverToDriveData)

    def test_print_keys(self):
        with patch("sys.stdout", new_callable=StringIO) as mock_stdout:
            DriverModes.print_keys(self.driver_data)
            output = mock_stdout.getvalue().strip()
            print(output)

    def test_pretty_print(self):
        with patch("sys.stdout", new_callable=StringIO) as mock_stdout:
            DriverModes.pretty_print(self.driver)
            output = mock_stdout.getvalue().strip()
            print(output)

if __name__ == "__main__":
    unittest.main()
