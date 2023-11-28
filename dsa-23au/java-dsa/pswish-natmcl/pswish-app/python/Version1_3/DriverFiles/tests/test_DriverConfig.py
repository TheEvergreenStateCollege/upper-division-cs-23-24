import pytest
from DriverFiles.Average import search_nested_dict, averager  # Replace 'your_module' with the actual module name

@pytest.fixture
def sample_nested_dict():
    # Define a sample nested dictionary for testing
    return {
        "City1": {"Distance": "10 miles", "Elapsed": "5 hours"},
        "City2": {"Distance": "15 miles", "Elapsed": "7 hours"},
        "City3": {"Distance": "10 miles", "Elapsed": "5 hours"},
        "NestedCity": {"City4": {"Distance": "20 miles", "Elapsed": "10 hours"}},
    }

def test_search_nested_dict(sample_nested_dict):
    # Test search_nested_dict function
    target_value = "20 miles"
    result = search_nested_dict(sample_nested_dict, target_value)
    print(result)
    
    # assert result == [("NestedCity", [("City4", {"Distance": "20 miles", "Elapsed": "10 hours"})])]

def test_averager(sample_nested_dict, capsys):
    # Test averager function
    averager(sample_nested_dict)
    captured = capsys.readouterr()

    # Add assertions based on the expected output or behavior of your function
    # assert "Total combined miles" in captured.out
    # assert "Total trips from or to the destination" in captured.out
    # Add more assertions as needed

if __name__ == '__main__':
    pytest.main()
