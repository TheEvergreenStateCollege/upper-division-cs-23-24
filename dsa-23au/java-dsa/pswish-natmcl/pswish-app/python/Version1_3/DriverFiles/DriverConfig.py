# This is a dependency of DriverMain
# Time Complexity of this script is O(3n)

import os
import subprocess
import sys

# You can run this independently of DriverMain to check the csv file capture

class theConfigurator:
    # The purpose of this function is to check if the file path exists, 
    # search for one if it does not and pass it to DriverMain 
    def __init__(self) -> None:
        self.filepath1 = ""
        self.filepath2 = ""

    def find_filepath(self):
        # Due to using multiple platforms used to create and run this code, this method checks 
        # each hard coded filepath for existence before assigning it to filepath1 and filepath2
        filepath1ec2 = "/home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/DataSet_DSAau_pswish.csv"
        filepath2ec2 = "/home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/Time_Driving_Spreadsheet.csv"
        
        filepath1gitpod = "/workspace/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/DataSet_DSAau_pswish.csv"
        filepath2gitpod = "/workspace/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/Time_Driving_Spreadsheet.csv"
        
        relative1 = "java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/DataSet_DSAau_pswish.csv"
        relative2 = "java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverResources/Time_Driving_Spreadsheet.csv"

        file1paths = filepath1ec2, filepath1gitpod, relative1
        file2paths = filepath2ec2, filepath2gitpod, relative2
        
        for file_path in file1paths:
            if os.path.exists(file_path):
                self.filepath1 = file_path
        for file_path in file2paths:
            if os.path.exists(file_path):
                self.filepath2 = file_path

        if self.filepath1 and self.filepath2 == "":
            self.filepath1 = self.get_filepath1()
            self.filepath2 = self.get_filepath2()
        
        return self.filepath1, self.filepath2

    def run_command(self, commands_with_args):
        # This function controls the running of shell commands
        # Returns output and err to the calling function
        p = subprocess.Popen(commands_with_args, shell=True, bufsize=8 * 1024, stdout=subprocess.PIPE, stderr=subprocess.PIPE) 
        (output, err) = p.communicate()
        read = output.decode().split("\n")[0]
        return read

    # The following two methods search the root directory for the last modified file with regex
    def get_filepath1(self):
        find_driver1_csv_command = "find / -type f -name '*DataSet_DSAau_pswish*' | awk 'NR==2 {print $NF}'"
        self.filepath1 = self.run_command(find_driver1_csv_command)
        return self.filepath1

    def get_filepath2(self):
        find_driver2_csv_command = "find / -type f -name '*Time_Driving_Spreadsheet*' | awk 'NR==2 {print $NF}'"
        self.filepath2 = self.run_command(find_driver2_csv_command)
        return self.filepath2

    # Sometimes the python path needs to be updated, not currently used
    def update_path(self):
        update_path_command = "export PYTHONPATH=$PYTHONPATH:Version1_3/DriverFiles"
        self.run_command(update_path_command)
        sys.path.append(update_path_command)

    # Print out the results of the filepath find and prints it during normal operation for data validation
    def print_to_start(self):
        print ("\nEnabling Filepath 1: ", self.filepath1)
        print ("Enabling Filepath 2: ", self.filepath2)


def main():
    config = theConfigurator()
    config.find_filepath()
    config.print_to_start()

if __name__ == main():
    main()