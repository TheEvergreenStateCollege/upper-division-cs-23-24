import unittest
from unittest.mock import patch
from io import StringIO
from main import main

class TestMainFunctionality(unittest.TestCase):
    
    def setUp(self):
        self.mock_stdout = StringIO()

    def tearDown(self):
        self.mock_stdout.close()

    def run_main_with_input(self, input_values):
        with patch('builtins.input', side_effect=input_values), \
             patch('sys.stdout', new_callable=StringIO) as self.mock_stdout:
            main()

    def assert_stdout_equal(self, expected_output):
        self.assertEqual(self.mock_stdout.getvalue(), expected_output)


    @patch('builtins.input', side_effect=['1', 'New York', '2020', '6'])
    def test_display_data_for_county_and_year(self, mock_input):
        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            main()
            expected_output = "Enter County: Enter Year: The specific data for New York in 2020 is:\n"
            self.assertEqual(mock_stdout.getvalue(), expected_output)

    @patch('builtins.input', side_effect=['2', '1', 'New York', '2020', '2021'])
    def test_temporal_analysis_yearly(self, mock_input):
        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            main()
            expected_output = "\nEnter County: Enter the first year: Enter the second year: Performing Yearly Analysis for New York between 2020 and 2021:\n"
            self.assertEqual(mock_stdout.getvalue(), expected_output)

    @patch('builtins.input', side_effect=['2', '2', 'New York', '2020', '2021'])
    def test_temporal_analysis_year_over_year(self, mock_input):
        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            main()
            expected_output = "\nEnter County: Enter the first year: Enter the second year: Performing Year-over-Year Comparison for New York between 2020 and 2021:\n"
            self.assertEqual(mock_stdout.getvalue(), expected_output)

    @patch('builtins.input', side_effect=['3', '2020'])
    def test_temporal_analysis_lowest_population(self, mock_input):
        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            main()
            expected_output = "\nEnter the year: The county with the lowest population in 2020 is "
            self.assertIn(expected_output, mock_stdout.getvalue())

    @patch('builtins.input', side_effect=['4', '2020'])
    def test_find_county_with_largest_firearm_count(self, mock_input):
        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            main()
            expected_output = "Enter the year to find the county with the largest firearm count: "
            self.assertIn(expected_output, mock_stdout.getvalue())

    @patch('builtins.input', side_effect=['5', '1', 'New York', '2020', '5000', '1000', '500', '2000', '300', '100'])
    def test_update_population_and_index_data(self, mock_input):
        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            main()
            expected_output = "\nEnter the county to update: Enter the year to update: Enter the new population: Enter the new index count: Enter the new index rate: "
            self.assertEqual(mock_stdout.getvalue(), expected_output)

    @patch('builtins.input', side_effect=['5', '2', 'New York', '2020', '200', '10', '100', '5'])
    def test_update_violent_and_property_data(self, mock_input):
        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            main()
            expected_output = "\nEnter the county to update: Enter the year to update: Enter the new violent count: Enter the new violent rate: Enter the new property count: Enter the new property rate: "
            self.assertEqual(mock_stdout.getvalue(), expected_output)

    @patch('builtins.input', side_effect=['5', '3', 'New York', '2020', '50', '2'])
    def test_update_firearm_data(self, mock_input):
        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            main()
            expected_output = "\nEnter the county to update: Enter the year to update: Enter the new firearm count: Enter the new firearm rate: "
            self.assertEqual(mock_stdout.getvalue(), expected_output)

    @patch('builtins.input', side_effect=['5', '4'])
    def test_update_existing_data_back_to_main_menu(self, mock_input):
        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            main()
            expected_output = "\nUpdate Existing Data Menu:\n1. Update Population and Index Data\n2. Update Violent and Property Data\n3. Update Firearm Data\n4. Back to Main Menu\nEnter your sub-choice: Returning to the Main Menu.\n"
            self.assertEqual(mock_stdout.getvalue(), expected_output)

    @patch('builtins.input', side_effect=['6'])
    def test_exit_program(self, mock_input):
        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            main()
            expected_output = "\nNew York Criminal Justice Crime Database Menu:\n1. Display data for a specific county and year\n2. Temporal Analysis\n3. Big O Analysis\n4. Find county with the largest firearm count for a specific year\n5. Update Existing Data\n6. Exit\nEnter your choice: "
            self.assertIn(expected_output, mock_stdout.getvalue())
    
    @patch('builtins.input', side_effect=['1', 'New York', '2020', '6'])
    def test_display_data_for_county_and_year(self, mock_input):
        self.run_main_with_input(mock_input)
        expected_output = "Enter County: Enter Year: The specific data for New York in 2020 is:\n"
        self.assert_stdout_equal(expected_output)


if __name__ == '__main__':
    unittest.main()
