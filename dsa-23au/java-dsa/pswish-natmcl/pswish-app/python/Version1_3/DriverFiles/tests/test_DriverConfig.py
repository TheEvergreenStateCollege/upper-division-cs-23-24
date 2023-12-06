import pytest
import os
from unittest.mock import patch
from DriverFiles.DriverConfig import theConfigurator

@pytest.fixture
def mock_find_filepath():
    # Need to revise this test since i killed the function
    return [
        "/home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/DataSet_DSAau_pswish.csv",
        "/workspace/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/DataSet_DSAau_pswish.csv",
        "java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/DataSet_DSAau_pswish.csv",
        "/home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/Time_Driving_Spreadsheet.csv",
        "/workspace/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/Time_Driving_Spreadsheet.csv",
        "java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/Time_Driving_Spreadsheet.csv"
    ]

# def test_find_filepath(mock_find_filepath):
#     with patch.object(theConfigurator, 'find_filepath', return_value=None):
#         config = theConfigurator()
#         config.find_filepath()
#         assert config.filepath1 is not None 
#         assert config.filepath2 is not None

def test_get_filepath1(monkeypatch, tmpdir):
    fake_path = str(tmpdir.join("fake_file.csv"))
    monkeypatch.setenv("PATH", fake_path)

    config = theConfigurator()
    filepath1 = config.get_filepath1()
    assert filepath1 is not None

def test_get_filepath2(monkeypatch, tmpdir):
    fake_path = str(tmpdir.join("fake_file.csv"))
    monkeypatch.setenv("PATH", fake_path)

    config = theConfigurator()
    filepath2 = config.get_filepath2()
    assert filepath2 is not None

# Add more tests as needed
