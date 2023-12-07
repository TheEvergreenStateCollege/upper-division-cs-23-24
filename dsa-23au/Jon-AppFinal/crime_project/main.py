# main.py
import os
import csv
import sys
from crime_data.crime_data import CrimeData
from crime_database.crime_database import CrimeDatabase
from analysis.temporal_analysis import TemporalAnalysis
from analysis.big_o_analysis import BigOAnalysis

def convert_input_to_float(value):
    try:
        return float(value)
    except ValueError:
        print(f"Error converting value to float: {value}")
        return None

def main():
    cdd = CrimeDatabase()
C
    # Get the absolute path to the directory containing your script
    script_directory = os.path.dirname(os.path.abspath(__file__))

    # Specify the path to your CSV file using the absolute path
    csv_file_path = os.path.join(script_directory, 'Crime.csv')

    # Load data from CSV file
    with open(csv_file_path, 'r') as file:
        csv_reader = csv.reader(file)
        header = next(csv_reader)  # Skip header row

        # Check if the CSV file has the expected header
        if header != ['County', 'Year', 'Population', 'Index Count', 'Index Rate', 'Violent Count', 'Violent Rate', 'Property Count', 'Property Rate', 'Firearm Count', 'Firearm Rate']:
            print("Invalid CSV format. Please check the header.")
            sys.exit(1)

        for row in csv_reader:
            if len(row) == 11:
                print(row)  # Add this line for debugging

                # Convert relevant columns to appropriate types when adding crime data
                converted_row = [
                    row[0],  # County
                    int(row[1]),  # Year (convert to int)
                    convert_input_to_float(row[2]),  # Population (convert to float)
                    convert_input_to_float(row[3]),  # Index Count (convert to float)
                    convert_input_to_float(row[4]),  # Index Rate (convert to float)
                    convert_input_to_float(row[5]),  # Violent Count (convert to float)
                    convert_input_to_float(row[6]),  # Violent Rate (convert to float)
                    convert_input_to_float(row[7]),  # Property Count (convert to float)
                    convert_input_to_float(row[8]),  # Property Rate (convert to float)
                    convert_input_to_float(row[9]),  # Firearm Count (convert to float)
                    convert_input_to_float(row[10])  # Firearm Rate (convert to float)
                ]

                # Add crime data
                cdd.addCrimeData(CrimeData(*converted_row))

    cdd.displayCrimeDataList()

    # Display menu
    choice = 0
    while choice != 4:
        print("\nNew York Criminal Justice Crime Database Menu:")
        print("1. Display data for a specific county and year")
        print("2. Temporal Analysis")
        print("3. Big O Analysis")
        print("4. Exit")

        try:
            choice = int(input("Enter your choice: "))
        except ValueError:
            print("Invalid input. Please enter a number.")
            continue

        if choice == 1:
            # Prompt user for county and year
            county_input = input("Enter County: ").lower()
            year_input = int(input("Enter Year: "))

            # Display specific data for the specified county and year
            cdd.displayDataForCountyAndYear(county_input, year_input)
        elif choice == 2:
            print("\nTemporal Analysis Menu:")
            print("1. Yearly Analysis")
            print("2. Year over Year Comparison")
            print("3. Comparative Analysis")
            print("4. Moving Averages")
            print("5. Firearm Analysis")
            print("6. Back to Main Menu")

            try:
                sub_choice = int(input("Enter your sub-choice: "))
            except ValueError:
                print("Invalid input. Please enter a number.")
                continue

            if sub_choice == 1:
                # Prompt user for county and two years
                county_input = input("Enter County: ").lower()
                year1_input = int(input("Enter the first year: "))
                year2_input = int(input("Enter the second year: "))

                # Perform Yearly Analysis
                cdd.yearlyAnalysis(county_input, year1_input, year2_input)
            elif sub_choice == 2:
                # Prompt user for county and two years
                county_input = input("Enter County: ").lower()
                year1_input = int(input("Enter the first year: "))
                year2_input = int(input("Enter the second year:"))

                # Perform Year-over-Year Comparison
                cdd.yearOverYearComparison(county_input, year1_input, year2_input)
            elif sub_choice == 6:
                print("Returning to the Main Menu.")
            else:
                print("Invalid sub-choice. Please enter a valid option.")
        elif choice == 3:
            print("\n Big O Analysis:")
            print("1. addCrimeData")
            print("2. displayCrimeDataList")
            print("3. displayDataForCountyAndYear")
            print("4. yearlyAnalysis")
            print("5. yearOverYearComparison")

            sub_choice = int(input("Enter your sub-choice: "))

            if sub_choice == 1:
                print(cdd.addCrimeDataAnalysis_txt)
            elif sub_choice == 2:
                print(displayCrimeDataList_txt)
            elif sub_choice == 3:
                print(displayDataForCountyAndYear_txt)
            elif sub_choice == 4:
                print(cdd.yearlyAnalysis_text)
            elif sub_choice == 5:
                print(cdd.yearToYearAnalysis_txt)
            else:
                print("Invalid sub-choice. Please enter a valid option.")
        elif choice == 4:
            print("Exiting the program.")
        else:
            print("Invalid choice. Please enter 1, 2, 3, or 4.")

if __name__ == "__main__":
    main()
