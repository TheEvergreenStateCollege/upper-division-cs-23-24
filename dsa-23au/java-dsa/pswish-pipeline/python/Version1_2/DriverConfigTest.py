import unittest
from unittest.mock import patch
from io import StringIO
from . import DriverConfig as my_script

class TestMyScript(unittest.TestCase):

    @patch("subprocess.Popen")
    def test_run_command(self, mock_popen):
        mock_process = mock_popen.return_value
        mock_process.communicate.return_value = (b"Mocked Output", b"Mocked Error")

        result = my_script.run_command("mock_command")

        self.assertEqual(result, "Mocked Output")

if __name__ == "__main__":
    unittest.main()
