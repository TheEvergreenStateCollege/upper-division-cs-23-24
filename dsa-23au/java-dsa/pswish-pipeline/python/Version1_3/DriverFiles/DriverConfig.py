# This is a dependency of DriverMain
import subprocess
import sys

# Filename output should be close to this:
filepath1ec2 = "/home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/datasets/DriverData/DataSet_DSAau_pswish.csv"
filepath2ec2 = "/home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/datasets/DriverData/Time_Driving_Spreadsheet.csv"

class theConfigurator:
    def __init__(self) -> None:
        self.filepath1 = ""
        self.filepath2 = ""

    def run_command(self, commands_with_args):
        # This function controls the running of shell commands
        # Returns output and err to the calling function
        p = subprocess.Popen(commands_with_args, shell=True, bufsize=8 * 1024, stdout=subprocess.PIPE, stderr=subprocess.PIPE) 
        (output, err) = p.communicate()
        read = output.decode().split("\n")[0]
        return read

    def get_filepath1(self):
        find_driver1_csv_command = "find / -type f -name '*DataSet_DSAau_pswish*' | awk 'NR==2 {print $NF}'"
        self.filepath1 = self.run_command(find_driver1_csv_command)
        return self.filepath1

    def get_filepath2(self):
        find_driver2_csv_command = "find / -type f -name '*Time_Driving_Spreadsheet*' | awk 'NR==2 {print $NF}'"
        self.filepath2 = self.run_command(find_driver2_csv_command)
        return self.filepath2

    def update_path(self):
        update_path_command = "export PYTHONPATH=$PYTHONPATH:/home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/java-dsa/pswish-pipeline/python/Version1_2/DriverFiles"
        self.run_command(update_path_command)
        sys.path.append(update_path_command)

    def print_to_start(self):
        print ("\nEnabling Filepath 1: ", self.filepath1)
        print ("Enabling Filepath 2: ", self.filepath2)


def main():
    config = theConfigurator()
    filepath1 = config.get_filepath1()
    filepath2 = config.get_filepath2()
    if not filepath1:
        filepath1 == filepath1ec2
    elif not filepath2:
        filepath2 == filepath1ec2

    # config.update_path() # might not be needed
    config.print_to_start()

if __name__ == main():
    main()