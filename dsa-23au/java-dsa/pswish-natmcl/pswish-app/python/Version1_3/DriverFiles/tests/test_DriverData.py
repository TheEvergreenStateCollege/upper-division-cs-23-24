import pytest
from DriverFiles.DriverData2 import DriverToDriveData
from DriverFiles.DriverConfig import theConfigurator
from unittest.mock import mock_open, patch

@pytest.fixture
def driver_instance():
    return DriverToDriveData()

@pytest.fixture
# Mocking some data that might be in the csv
def mock_csv_data():
    return """Date,Time,Driver,OtherField1,OtherField2
11/01/2023,1200,P,Value1,Value2
10/22/2023,1300,Nathan,Value3,Value4"""

# Testing the filepath 
def test_filepath(driver_instance):
    test_filepath = theConfigurator().get_filepath1()
    driver_instance._add_list_to_dict_by_index(test_filepath)
    assert len(driver_instance.DataStructure) > 0   

# Testing main function 
def test_add_list_to_dict_by_index(mock_csv_data):
    with patch("builtins.open", new_callable=mock_open, read_data=mock_csv_data):
        instance = DriverToDriveData()
        filepath = "/path/to/fake/file.csv"
        instance._add_list_to_dict_by_index(filepath)

        print(instance.Lines)
        assert instance.Lines == [
            {"Date": "11/01/2023", "Time": "1200", "Driver": "P", "OtherField1": "Value1", "OtherField2": "Value2"},
            {"Date": "10/22/2023", "Time": "1300", "Driver": "Nathan", "OtherField1": "Value3", "OtherField2": "Value4"}
        ]

        assert instance.DataStructure == {
            "11/01/2023_1200_Paul": {"Date": "11/01/2023", "Time": "1200", "Driver": "P", "OtherField1": "Value1", "OtherField2": "Value2"},
            "10/22/2023_1300_Nathan": {"Date": "10/22/2023", "Time": "1300", "Driver": "Nathan", "OtherField1": "Value3", "OtherField2": "Value4"}
        }

        assert instance.presorted_keys == ["11/01/2023_1200_Paul", "10/22/2023_1300_Nathan"]
        assert instance.sorted_keys == ["10/22/2023_1300_Nathan", "11/01/2023_1200_Paul"]

# Testing driverKeys list exists
def test_compile_driver_specific_lists(driver_instance):
    driver_instance._add_list_to_dict_by_index(theConfigurator().get_filepath1())
    driver_instance._add_list_to_dict_by_index(theConfigurator().get_filepath2())
    driver_instance._compile_driver_specific_lists()
    assert len(driver_instance.driver1keys) > 0
    assert len(driver_instance.driver2keys) > 0

def test_run_mode(driver_instance):
    result = driver_instance.run_mode()
    my_instance = DriverToDriveData()

    result = my_instance.run_mode()
    # run node testing doesn't offer much value 

if __name__ == '__main__':
    pytest.main()
