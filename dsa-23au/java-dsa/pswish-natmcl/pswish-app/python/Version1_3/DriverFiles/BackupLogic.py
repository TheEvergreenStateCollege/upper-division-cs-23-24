# Main method for the DriverData program
# This logic does not require python 3.10
# This function is O(1)

import Average
import DriverModes
from DriverData2 import DriverToDriveData
import logging
import sys
from memory_profiler import profile

logger = logging.Logger

driver_data = DriverToDriveData()
driver_data.run_mode()
driver = driver_data.DataStructure
ReplayCounter = 1

@profile  # some memory profiling
class BackupMode:
    # The purpose of this function is to serve as backup DriverMain logic if python 3.10 is not installed
    def __init__(cls) -> None:
        cls.user_input = ""

    def user_instructions(cls):
        global ReplayCounter
        ReplayCounter += 1

        user_input = (input("""\nPlease select a mode of operation: \
                            \n 1. Enter 1 to view available keys. \
                            \n 2. Enter 2 to view all available data in readable format. \
                            \n 3. Enter 3 to view a sample calculation. \
                            \n 4. Enter 4 for single data viewing by key. \
                            \n 5. Enter 5 to search the data for a value.  \
                            \n 6. Enter 6 to view total combined miles driven for both drivers in all data and cost. \
                            \n 7. Enter 7 view total combined time driven. \
                            \n 8. Enter 8 to view a range of n to k sorted example miles data. \
                            \n 9. Enter 9 to run the numbers \
                            \n 10. Enter 10 to quit. \
                            \n\n Please type your selection and push enter:  """))
        try:
            cls.user_input = int(user_input)
        except:
            print("please enter a number from the list above")
            cls.user_input = None

    @profile
    def run_mode(cls):
        exit = False
        try: 
            if cls.user_input == 1:
                DriverModes.print_keys(driver_data)
            elif cls.user_input == 2:
                DriverModes.pretty_print(driver)
            elif cls.user_input == 3:
                DriverModes.sample_calculation(driver_data, driver)
            elif cls.user_input == 4:
                DriverModes.search_by_key_and_value(driver_data, driver)
            elif cls.user_input == 5:
                DriverModes.search_by_value(driver)
            elif cls.user_input == 6:
                DriverModes.total_miles(driver_data, driver)
            elif cls.user_input == 7:
                DriverModes.total_time(driver_data, driver)
            elif cls.user_input == 8:
                DriverModes.calculate_distances(driver_data)
            elif cls.user_input == 9:
                Average.averager(driver)
            elif cls.user_input == 10:
                print("Exiting ...")
                exit = True
            

        except Exception as e:
            print("Error in DriverModels, please make sure you typed a number: ", e)

        if ReplayCounter >= 10 or exit is True:
            print("You have reached the max retries for this program, 10. End of program...")
        
        else: 
            replay = input("Push enter to run another operation or type 'exit' to quit: ")

            if replay == "exit".lower():  # replay the program main loop or type exit to quit
                print("End of program...")
                sys.exit()
            else: 
                print("Replay counter", ReplayCounter, "out of 10")
                cls.inner_main()

    def inner_main(cls):
        cls.user_instructions()
        cls.run_mode()

def main():
    backup = BackupMode()
    backup.inner_main()

if __name__ == main():
    main()