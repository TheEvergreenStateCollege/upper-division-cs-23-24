# Main method for the DriverData program
# This logic requires python 3.10

import DriverModes
from DriverDatav1_2 import DriverToDriveData
import logging

logger = logging.Logger

driver_data = DriverToDriveData()
driver_data.run_mode()
driver = driver_data.DataStructure
debug = False  # set to True to disable replay


def main():
    try:
        user_input = (input("""\nPlease select a mode of operation: \
                            \n 1. Enter 1 to view available keys. \
                            \n 2. Enter 2 to view all available data in readable format. \
                            \n 3. Enter 3 to view a sample calculation. \
                            \n 4. Enter 4 for single data viewing by key. \
                            \n 5. Enter 5 to search the data for a value.  \
                            \n 6. Enter 6 to view total combined miles driven for both drivers in all data. \
                            \n 7. Enter 7 view total combined time driven. \
                            \n 8. Enter 8 to view a range of n to k sorted example miles data. \
                            \n\n Please type your selection and push enter:  """))

        Modes = DriverModes
        match int(user_input):   
            case 1:
                Modes.print_keys(driver_data)
            case 2:
                Modes.pretty_print(driver)
            case 3:
                Modes.sample_calculation(driver_data, driver)
            case 4:
                Modes.search_by_key_and_value(driver_data, driver)
            case 5:
                Modes.search_by_value(driver)
            case 6:
                Modes.total_miles(driver_data, driver)
            case 7:
                Modes.total_time(driver_data, driver)
            case 8:
                Modes.calculate_distances(driver_data)

    except Exception as e:
        print("Error in DriverModels, please make sure you typed a number: ", e)
    finally:
        if not debug:
            replay = input("Push enter to run another operation or type 'exit' to quit: ")

            if replay == "exit".lower():  # replay the program main loop or type exit to quit
                print("End of program...")
            else: 
                main()
if __name__ == main():
    main()

# TODO need to add if/then logic as backup to match in python 3.10