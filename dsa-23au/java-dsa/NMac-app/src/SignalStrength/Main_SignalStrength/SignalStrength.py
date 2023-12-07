# Wi-Fi Signal Strength Data

import csv
import logging
import sys
from SignalStrengthConfig import csv_file_path

logger = logging.getLogger(__name__)
ReplayCounter = 1


def load_data(csv_path):
    local_data = []  # Use a local variable instead of global
    with open(csv_path, mode='r') as file:
        csv_reader = csv.DictReader(file)
        for row in csv_reader:
            local_data.append(row)
    return local_data


def print_data(datas):
    for row in datas:
        print(f"{row['ESSID']}, {row['Quality']}, {row['Scan Time']}")


def get_unique_essids(datas):
    return list({row['ESSID'] for row in datas})


def filter_by_essid(datas, essid):
    return [row for row in datas if row['ESSID'] == essid]


class MenuMode:
    def __init__(self) -> None:
        self.user_input = ""
        self.data = load_data(csv_file_path)  # Load data in the constructor

    def user_instructions(self):  # Define this method to show the menu
        self.main_menu()

    def main_menu(self):
        global ReplayCounter
        ReplayCounter += 1

        user_input = (input("""\nWi-Fi Signal Strength Menu: \
                            \n 1. Enter 1 to view all data. \
                            \n 2. Enter 2 to View unique ESSIDs. \
                            \n 3. Enter 3 to Filter by ESSID. \
                            \n 4. Enter 4 do something. \
                            \n 5. Enter 5 Quit.  \
                            \n\n Please type your selection and push enter: """))

        try:
            self.user_input = int(user_input)
            self.run_mode()
        except ValueError:
            print("please enter a number from the list above")
            self.user_input = None

    def run_mode(self):
        try:
            if self.user_input == 1:
                print_data(self.data)
            elif self.user_input == 2:
                unique_essids = get_unique_essids(self.data)
                print(unique_essids)
            elif self.user_input == 3:
                essid = input("Enter ESSID to filter by: ")
                filtered_data = filter_by_essid(self.data, essid)
                print_data(filtered_data)
            elif self.user_input == 4:
                # You need to define what "do something" does
                print("Option 4 selected. Define this action.")
            elif self.user_input == 5:
                print("Exiting...")
                sys.exit()  # Exit the program

        except Exception as e:
            print("Error somewhere: ", e)

        if ReplayCounter >= 10 or exit is True:
            print("You have reached the max retries for this program, 10. End of program...")
            sys.exit()
        else:
            replay = input("Push enter to run another operation or type 'exit' to quit: ")

            if replay.lower() == "exit":  # replay the program main loop or type exit to quit
                print("End of program...")
                sys.exit()
            else:
                print("Replay counter", ReplayCounter, "out of 10")
                self.user_instructions()  # Changed from inner_main to user_instructions


# def inner_main(self):
# self.user_instructions()
# self.run_mode()


def main():
    menu = MenuMode()
    global data  # Declare data as a global variable
    data = load_data(csv_file_path)
    menu.user_instructions()


if __name__ == "__main__":
    main()
