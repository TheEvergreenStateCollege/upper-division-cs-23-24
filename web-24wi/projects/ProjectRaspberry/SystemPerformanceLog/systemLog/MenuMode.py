import csv
import logging
import sys
import traceback

from SystemLogConfig import os_log_path
from CPU_Temp_Averages import CPUTempAverages
from ESSIDAverageCalculator import WiFiStrengthAverageCalculator
from ESSIDCategorizer import WiFiCategorizer
from CPUUsageScan import CPUScanCategorizer
from MemoryUsageCategorizer import MemoryUsageCategorizer
from DiskAnalysis import DiskAnalysis
from NetworkTrafficFormat import NetworkTrafficFormat

logger = logging.getLogger(__name__)

ReplayCounter = 1


def load_data(os_log_path):
    data = []
    try:
        with open(os_log_path, mode='r', newline='') as csvfile:
            reader = csv.DictReader(csvfile)
            for row in reader:
                data.append(row)
    except FileNotFoundError:
        print(f"The file {os_log_path} was not found.")
        sys.exit(1)
    except Exception as e:
        print(f"An error occurred: {e}")
        sys.exit(1)
    return data


def print_data(data):
    for row in data:
        print(', '.join(f"{k}: {v}" for k, v in row.items()))


def get_unique_os_names(data):
    return set(row['OS Name'] for row in data)


def filter_by_os_name(data, os_name):
    return [row for row in data if row['OS Name'] == os_name]


def filter_by_date_time(data, date_time):
    return [row for row in data if row['Date Time'] == date_time]


class MenuMain:
    def __init__(self):
        self.user_input = ""
        self.data = load_data(os_log_path)  # Load data in the constructor

    def user_instructions(self):  # Define this method to show the menu
        self.main_menu()

    def main_menu(self):
        global ReplayCounter
        ReplayCounter += 1

        user_input = (input("""\n
                        \n System Performance Menu: \
                        \n Enter 1 to View All Data. \
                        \n Enter 2 to View Unique OS Names. \
                        \n Enter 3 to Filter by OS Name. \
                        \n Enter 4 to Filter by Date and Time. \
                        \n Enter 5 to View CPU Temperature. O(n) \
                        \n Enter 6 to Categorize Wi-Fi Strength. O(n) \
                        \n Enter 7 to  View Wi-Fi Average Quality. O(n) \
                        \n Enter 8 to View CPU Usage. O(n) \
                        \n Enter 9 to View Memory Usage. O(n) \
                        \n Enter 10 to View Disk Usage. O(n) \
                        \n Enter 11 to View Network Traffic Usage O(n) \
                        \n Enter 0 to Quit. \
                        \n\n Please type your selection and push enter: """))

        try:
            self.user_input = int(user_input)
            self.run_mode()
        except ValueError:
            print("Please enter a number from the list above")
            self.user_input = None

    def run_mode(self):
        global ReplayCounter
        try:
            user_choice = int(self.user_input)
            if user_choice == 1:
                print_data(self.data)
            elif user_choice == 2:
                unique_os_names = get_unique_os_names(self.data)
                print("\n OS Names:")
                for name in unique_os_names:
                    print(name)
            elif user_choice == 3:
                os_name = input("Enter OS Name to filter by: ")
                filtered_data = filter_by_os_name(self.data, os_name)
                print_data(filtered_data)
            elif user_choice == 4:
                date_time = input("Enter Date and Time to filter by (format: YYYY-MM-DD HH:MM:SS): ")
                filtered_data = filter_by_date_time(self.data, date_time)
                print_data(filtered_data)
            elif user_choice == 5:
                self.show_cpu_temp_stats()  # Call the new method for CPU temperature stats
            elif user_choice == 6:
                categorizer = WiFiCategorizer(self.data)
                categorized_strengths = categorizer.categorize_strengths()
                for category, rows in categorized_strengths.items():
                    print(f"{category}: {len(rows)} records")
            elif user_choice == 7:
                categorizer = WiFiCategorizer(self.data)
                categorized_strengths = categorizer.categorize_strengths()
                strength_calculator = WiFiStrengthAverageCalculator(categorized_strengths)
                averages = strength_calculator.calculate_averages()
                for category, avg in averages.items():
                    print(f"{category} Average Strength: {avg:.2f}")
            elif user_choice == 8:
                self.show_cpu_usage_by_time()
            elif user_choice == 9:
                self.analyze_memory_usage()
            elif user_choice == 10:
                self.disk_analysis = DiskAnalysis(self.data)
                self.disk_analysis.print_current_disk_usage()
                self.disk_analysis.find_nearest_change()
            elif user_choice == 11:
                self.network_traffic = NetworkTrafficFormat(self.data)
                # self.network_traffic.print_converted_network_traffic()
                self.network_traffic.prompt_daily_average()
            elif user_choice == 0:
                print("Exiting...")
                sys.exit()  # Exit the program
            else:
                print("Invalid option. Please try again.")

        except Exception as e:
            print("Error Somewhere: ", e)
            traceback.print_exc()

        if ReplayCounter >= 10:
            print("You have reached the max retries for this program, 10. End of program...")
            sys.exit()
        else:
            replay = input("Push enter to run another operation or type 'exit' to quit: ")

            if replay.lower() == "exit":
                print("End of program...")
                sys.exit()
            else:
                print("Replay counter", ReplayCounter, "out of 10")
                self.user_instructions()

    def show_cpu_temp_stats(self):
        cpu_temp_stats = CPUTempAverages(self.data)
        total_temp, average_temp = cpu_temp_stats.total_average()
        print(f"Average CPU Temp: {average_temp:.2f}\n")

    def show_cpu_usage_by_time(self):
        try:
            categorizer = CPUScanCategorizer(self.data)
            categorized_cpu_usages = categorizer.categorize_time_range()
            averages = categorizer.calculate_averages(categorized_cpu_usages)

            for category, rows in categorized_cpu_usages.items():
                print(f"{category}: {len(rows)} records")
                avg = averages[category]
                print(f"{category} Average CPU Usage: {avg:.2f}")
        except Exception as e:
            print("An error occurred in show_cpu_usage_by_time: ", e)
            traceback.print_exc()

    def analyze_memory_usage(self):
        categorizer = MemoryUsageCategorizer(self.data)
        categorizer.analyze_memory_usage()


def main():
    menu = MenuMain()
    menu.user_instructions()


if __name__ == "__main__":
    main()