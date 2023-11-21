# This is a dependency of DriverMain
import subprocess

# filepath1 = "/home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/datasets/DriverData/DataSet_DSAau_pswish.csv"
# filepath2 = "/home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/datasets/DriverData/Time_Driving_Spreadsheet.csv"

def run_command(commands_with_args):
    # This function controls the running of shell commands
    # Returns output and err to the calling function
    p = subprocess.Popen(commands_with_args, shell=True, bufsize=8 * 1024, stdout=subprocess.PIPE, stderr=subprocess.PIPE) 
    (output, err) = p.communicate()
    read = output.decode().split("\n")[0]
    # print(read)
    return read

def get_filepath1():
    find_driver1_csv_command = "find /home -type f -name '*DataSet_DSAau_pswish*' | awk 'NR==2 {print $NF}'"
    out1 = run_command(find_driver1_csv_command)
    # print(out1)
    return out1

def get_filepath2():
    find_driver2_csv_command = "find /home -type f -name '*Time_Driving_Spreadsheet*' | awk 'NR==2 {print $NF}'"
    out2 = run_command(find_driver2_csv_command)
    # print(out2)
    return out2

filepath1 = get_filepath1()
filepath2 = get_filepath2()

print ("\nEnabling Filepath 1: ", filepath1)
print ("Enabling Filepath 2: ", filepath2)