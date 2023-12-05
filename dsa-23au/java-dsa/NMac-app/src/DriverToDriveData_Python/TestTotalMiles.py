import unittest

from DriverToDriveData.driverToDriveData import DriverToDriveData


class MyTestCase(unittest.TestCase):

    # This method opens two CSV files and reads their contents.
    # Verify that the method correctly reads data from the files.
    def setUp(self):
        # Initialize the DriverToDriveData object
        self.driver_data = DriverToDriveData()

        # Setup mock file paths
        self.driver_data.filepath1 = 'DataSet_DSAau_pswish_test2.csv'
        self.driver_data.filepath2 = 'Time_Driving_Spreadsheet_test7.csv'

    def test_get_csv_file_data(self):
        self.driver_data.get_csv_file_data()
        self.assertIsNotNone(self.driver_data.Line)
        self.assertIsNotNone(self.driver_data.Line2)

    # This method processes the data from the first CSV file to create keys.
    # Ensure that the keys are created as expected.
    def test_create_driver1_keys(self):
        self.driver_data.get_csv_file_data()  # Ensure data is loaded
        self.driver_data.create_driver1_keys()
        self.assertIsNotNone(self.driver_data.driver1keys)
        self.assertGreater(len(self.driver_data.driver1keys), 0)

    # This method adds data for driver 1 to a dictionary.
    # Test if the data structure is correctly populated.
    def test_add_list_to_dict_by_index(self):
        self.driver_data.get_csv_file_data()
        self.driver_data.add_list_to_dict_by_index()
        self.assertIsNotNone(self.driver_data.DataStucture)
        self.assertIsInstance(self.driver_data.DataStucture, dict)


if __name__ == '__main__':
    unittest.main()
