import unittest
from DriverToDriveData import DriverToDriveData  # Replace with your actual module name


def total_miles(driver_data, driver):
    total = 0
    for key in driver_data['sorted_keys']:
        distance_str = driver[key]['Distance']
        # Assuming distance is always in the format "number miles"
        distance = int(distance_str.split()[0])  # Split the string and convert the first part to an integer
        total += distance
    return total


class TestTotalMiles(unittest.TestCase):
    def test_total_miles(self):
        # Setup
        driver_data = {'sorted_keys': ['key1', 'key2']}
        driver = {
            'key1': {'Distance': '100 miles'},
            'key2': {'Distance': '150 miles'}
        }
        # Action
        result = total_miles(driver_data, driver)
        # Assert
        self.assertEqual(result, 250)  # Assuming the total should be 250


if __name__ == '__main__':
    unittest.main()
