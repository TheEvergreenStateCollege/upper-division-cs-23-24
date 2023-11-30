import pytest
from DriverFiles.DriverData2 import DriverToDriveData
# from .. import DriverConfig
# In tests/test_some_module.py
from DriverFiles.DriverConfig import theConfigurator


## TODO Add more assertions to each test, as needed
## TODO Fix the import in DriverData.py for DriverConfig to work in main method and pytest

@pytest.fixture
def driver_instance():
    return DriverToDriveData()

def test_filepath(driver_instance):
    test_filepath = theConfigurator().get_filepath1()
    driver_instance._add_list_to_dict_by_index(test_filepath)
    assert len(driver_instance.DataStructure) > 0   

def test_add_list_to_dict_by_index(driver_instance):
    pass


def test_compile_driver_specific_lists(driver_instance):
    driver_instance._add_list_to_dict_by_index(theConfigurator().get_filepath1())
    driver_instance._add_list_to_dict_by_index(theConfigurator().get_filepath2())
    driver_instance._compile_driver_specific_lists()
    assert len(driver_instance.driver1keys) > 0
    assert len(driver_instance.driver2keys) > 0

def test_run_mode(driver_instance):
    result = driver_instance.run_mode()
    assert len(result) > 0

if __name__ == '__main__':
    pytest.main()
