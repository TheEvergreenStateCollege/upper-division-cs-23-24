import unittest
from unittest.mock import patch, mock_open
from DriverDatav1_1 import DriverToDriveData

class TestDriverToDriveData(unittest.TestCase):

    def setUp(self):
        # Set up any instance-specific fixtures or variables here
        self.mock_lines_file1 = [
            'Date,Time,Driver,OtherField1,OtherField2\n',
            '2023-01-01,12:00,P,Value1,Value2\n',
            '2023-01-02,14:00,P,Value3,Value4\n'
        ]

        self.mock_lines_file2 = [
            'Date,Time,Driver,OtherField1,OtherField2\n',
            '2023-01-01,12:00,Q,Value5,Value6\n',
            '2023-01-02,14:00,Q,Value7,Value8\n'
        ]

    def tearDown(self):
        # Clean up any resources created in setUp
        pass

    def test_get_csv_file_data(self):
        with patch('builtins.open', new_callable=mock_open, side_effect=[
            mock_open(read_data="".join(self.mock_lines_file1)).return_value,
            mock_open(read_data="".join(self.mock_lines_file2)).return_value
        ]):
            driver = DriverToDriveData()
            driver.get_csv_file_data()

            self.assertEqual(driver.Line, self.mock_lines_file1)
            self.assertEqual(driver.Line2, self.mock_lines_file2)

    @patch.object(DriverToDriveData, 'get_csv_file_data', return_value=None)
    def test_create_driver1_keys(self, mock_get_csv_file_data):
        driver = DriverToDriveData()
        driver.Line = self.mock_lines_file1
        driver.create_driver1_keys()

        # Assuming there are two lines in mock_lines_file1
        self.assertEqual(len(driver.driverKeys), 2)
        self.assertEqual(len(driver.driver1keys), 2)

    @patch.object(DriverToDriveData, 'get_csv_file_data', return_value=None)
    def test_create_driver2_keys(self, mock_get_csv_file_data):
        driver = DriverToDriveData()
        driver.Line2 = self.mock_lines_file2
        driver.create_driver2_keys()

        # Assuming there are two lines in mock_lines_file2
        self.assertEqual(len(driver.driver2Keys), 2)

    @patch.object(DriverToDriveData, 'create_driver2_keys', return_value=None)
    @patch('builtins.open', new_callable=mock_open)
    def test_add_list_to_dict2_by_(self, mock_open, mock_create_driver2_keys):
        mock_open.side_effect = [
            mock_open(read_data="".join(self.mock_lines_file1)).return_value,
            mock_open(read_data="".join(self.mock_lines_file2)).return_value
        ]

        driver = DriverToDriveData()
        driver.add_list_to_dict2_by_()

        # Assuming there are two lines in mock_lines_file2
        self.assertEqual(len(driver.DataStructure), 2)

    @patch.object(DriverToDriveData, 'add_list_to_dict_by_index', return_value=None)
    @patch.object(DriverToDriveData, 'add_list_to_dict2_by_', return_value=None)
    @patch.object(DriverToDriveData, 'operation_mode', return_value=None)
    @patch('builtins.input', side_effect=['3', 'exit'])
    def test_operation_mode(self, mock_input, mock_operation_mode, mock_add_list_to_dict2_by_, mock_add_list_to_dict_by_index):
        driver = DriverToDriveData()
        driver.operation_mode(input("Select mode: "))

        # Assuming the selected mode is 3 and the user types 'exit'
        self.assertEqual(driver.DataStructureFinal, driver.DataStructure)

if __name__ == '__main__':
    unittest.main()
