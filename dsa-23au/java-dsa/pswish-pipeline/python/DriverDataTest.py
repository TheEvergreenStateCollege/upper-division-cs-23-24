import unittest
from unittest.mock import mock_open, patch
import DriverData

class TestDriverToDriveData(unittest.TestCase):

    def setUp(self):
        self.dtdd = DriverData.DriverToDriveData()

    def test_addCSVtoLists(self):
        self.dtdd.Line = [
            "Header1,Header2,Header3\n",
            "Value1,Value2,Value3\n"
        ]
        self.dtdd.addCSVtoLists()
        self.assertEqual(self.dtdd.driverKeys, ["Value2_Value1_Paul"])
        self.assertEqual(self.dtdd.driver1keys, ["Value2_Value1_Paul"])

    # def test_addCSVtoList2(self):
    #     self.dtdd.Line2 = [
    #         "Header1,Header2,Header3\n",
    #         "Value1,Value2,Value3\n"
    #     ]
    #     self.dtdd.addCSVtoList2()
    #     self.assertEqual(self.dtdd.driver2Keys, ["Value2_Value1_Value3"])

    @patch('builtins.open', new_callable=mock_open, read_data='Value1,Value2,Value3\n')
    def test_addListitemsToDict(self, mock_open_file):
        self.dtdd.filepath1 = "dummy_file_path.csv"
        self.dtdd.addListitemsToDict()
        self.assertEqual(self.dtdd.DataStucture, {"Value2_Value1_Paul": {"Header1": "Value1", "Header2": "Value2", "Header3": "Value3"}})

    @patch('builtins.open', new_callable=mock_open, read_data='Value1,Value2,Value3\n')
    def test_addListitemsToDict2(self, mock_open_file):
        self.dtdd.filepath2 = "dummy_file_path2.csv"
        self.dtdd.addListitemsToDict2()
        self.assertEqual(self.dtdd.DataStucture, {"Value2_Value1_Value3": {"Header1": "Value1", "Header2": "Value2", "Header3": "Value3"}})

    def test_operationMode(self):
        # TODO: Implement test cases for the operationMode method
        pass

if __name__ == '__main__':
    unittest.main()
