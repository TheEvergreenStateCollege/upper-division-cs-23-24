# Wi-Fi Signal Strength Data

import csv
import os
import SignalStrengthConfig


def load_data(csv_path):
    data = []
    with open(csv_path, mode='r') as file:
        csv_reader = csv.DictReader(file)
        for row in csv_reader:
            data.append(row)
    return data


def print_data(data):
    for row in data:
        print(f"{row['ESSID']}, {row['Quality']}, {row['Scan Time']}")


def get_unique_essids(data):
    return list({row['ESSID'] for row in data})


def filter_by_essid(data, essid):
    return [row for row in data if row['ESSID'] == essid]


def main_menu(data):
    while True:
        print("\nWi-Fi Signal Strength Menu")
        print("1. View all data")
        print("2. View unique ESSIDs")
        print("3. Filter by ESSID")
        print("4. Exit")
        choice = input("Enter your choice: ")

        if choice == '1':
            print_data(data)
        elif choice == '2':
            unique_essids = get_unique_essids(data)
            print(unique_essids)
        elif choice == '3':
            essid = input("Enter ESSID to filter by: ")
            filtered_data = filter_by_essid(data, essid)
            print_data(filtered_data)
        elif choice == '4':
            print("Exiting the program.")
            break
        else:
            print("Invalid choice. Please try again.")


# Load the data from CSV
data = load_data('D:\myPython\SignalStrength\SignalStrengthResources\wifi_signal_strength.csv')

# Run the main menu
main_menu(data)