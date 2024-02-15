# This is a dependency of DriverMain
# Time Complexity of this script is O(3n)

import os
import subprocess
import sys
import time

# You can run this independently of DriverMain to check the csv file capture

class theConfigurator:
    # The purpose of this function is to check if the file path exists, 
    # search for one if it does not and pass it to DriverMain 
    def __init__(cls) -> None:
        cls.filepath1 = ""
        cls.filepath2 = ""

        # Due to using multiple platforms used to create and run this code, this method checks 
        # each hard coded filepath for existence before assigning it to filepath1 and filepath2
        filepath1ec2 = "/home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/DataSet_DSAau_pswish.csv"
        filepath2ec2 = "/home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/Time_Driving_Spreadsheet.csv"
        filepath3ec2 = "/home/ubuntu/Workspace/upper-division-cs/web-24wi/projects/pswish/driver_flask/DriverResources/DataSet_DSAau_pswish.csv"
        filepath4ec2 = "/home/ubuntu/Workspace/upper-division-cs/web-24wi/projects/pswish/driver_flask/DriverResources/Time_Driving_Spreadsheet.csv"
        
        filepath1gitpod = "/workspace/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/DataSet_DSAau_pswish.csv"
        filepath2gitpod = "/workspace/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/Time_Driving_Spreadsheet.csv"
        
        relative1 = "java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/DataSet_DSAau_pswish.csv"
        relative2 = "java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/Time_Driving_Spreadsheet.csv"

        cls.file1paths = filepath3ec2, filepath1gitpod, relative1
        cls.file2paths = filepath4ec2, filepath2gitpod, relative2

    def run_command(cls, commands_with_args):
        # This function controls the running of shell commands
        # Returns output and err to the calling function
        p = subprocess.Popen(commands_with_args, shell=True, bufsize=8 * 1024, stdout=subprocess.PIPE, stderr=subprocess.PIPE) 
        (output, err) = p.communicate()
        read = output.decode().split("\n")[0]
        error = err
        print(read, error)
        return read, error

    # The following two methods search the root directory for the last modified file with regex
    def get_filepath1(cls):
        for file_path in cls.file1paths:
            if os.path.exists(file_path):
                cls.filepath1 = file_path
        if cls.filepath1 == "":
            cls.filepath1 = "/workspace/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/DataSet_DSAau_pswish.csv"
        
        # find_driver1_csv_command = "find / -type f -name '*DataSet_DSAau_pswish.csv*' | awk 'NR==2 {print $NF}'"
        # cls.filepath1 = cls.run_command(find_driver1_csv_command)
        return cls.filepath1

    def get_filepath2(cls):
        for file_path in cls.file2paths:
            if os.path.exists(file_path):
                cls.filepath2 = file_path
        if cls.filepath2 == "":
            cls.filepath2 = "/workspace/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/Time_Driving_Spreadsheet.csv"
        
        # find_driver2_csv_command = "find / -type f -name '*Time_Driving_Spreadsheet.csv*' | awk 'NR==2 {print $NF}'"
        # cls.filepath2 = cls.run_command(find_driver2_csv_command)
        return cls.filepath2

    # Sometimes the python path needs to be updated, not currently used
    def update_path(cls):
        # update_path_command = 'export PATH="$HOME/.local/bin:$PATH"'
        # cls.run_command(update_path_command)
        # sys.path.append(update_path_command)
        print("run pytest with >>> /home/gitpod/.local/bin/pytest")
    
    def install_pytest(cls):
        pytest_install_command = "pip3 install pytest"
        cls.run_command(pytest_install_command)

    # Print out the results of the filepath find and prints it during normal operation for data validation
    def print_to_start(cls):
        print ("\nEnabling Filepath 1: ", cls.filepath1)
        print ("Enabling Filepath 2: ", cls.filepath2)

def pytest_setup():
    print("Starting pytest install")
    config = theConfigurator()
    config.install_pytest()
    time.sleep(2)
    config.update_path()

def main():
    config = theConfigurator()
    filepath1 = config.get_filepath1()
    filepath2 = config.get_filepath2()
    config.print_to_start()


if __name__ == main():
    main()