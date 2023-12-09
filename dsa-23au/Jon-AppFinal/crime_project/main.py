# main.py

import os
import csv
import sys
import importlib
from crime_data.crime_data import CrimeData
from crime_database.crime_database import CrimeDatabase
from analysis.temporal_analysis import TemporalAnalysis
from analysis.big_o_analysis import BigOAnalysis

# Add this function to handle float conversion
def convert_input_to_float(value):
    try:
        return float(value)
    except ValueError:
        print(f"Error converting value to float: {value}")
        return 0.0  # Return 0.0 when conversion fails

def printCountyList(cdd):
    all_counties = sorted(set(data.County.lower() for data in cdd.crimeDataList))
    if all_counties:
        print("\nList of Counties:")
        for i, county in enumerate(all_counties, 1):
            print(f"{i}. {county.capitalize()}")
    else:
        print("\nNo counties found in the data.")

def main():
    cdd = CrimeDatabase()
    script_directory = os.path.dirname(os.path.abspath(__file__))
    csv_file_path = os.path.join(script_directory, 'Crime.csv')

    # Dynamically import the module
    import crime_database.crime_database as crime_db_module
    importlib.reload(crime_db_module)

    # Create an instance of CrimeDatabase
    cdd = crime_db_module.CrimeDatabase()

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

    # Create an instance of BigOAnalysis
    big_o_analysis = BigOAnalysis()
    
    

    # Display menu
    choice = 0
    while choice != 6:
        print("\nNew York Criminal Justice Crime Database Menu:")
        print("1. Display data for a specific county and year")
        print("2. Temporal Analysis")
        print("3. Big O Analysis")
        print("4. Find county with the largest firearm count for a specific year")
        print("5. Update Existing Data")
        print("6. Exit")

        try:
            choice = int(input("Enter your choice: "))
        except ValueError:
            print("Invalid input. Please enter a number.")
            continue

        if choice == 1:
            # Print the list of counties
            printCountyList(cdd)

            # Prompt user for county and year
            county_input = input("Enter County: ").lower()
            year_input = int(input("Enter Year: "))

            # Display specific data for the specified county and year
            cdd.displayDataForCountyAndYear(county_input, year_input)
        elif choice == 2:
            print("\nTemporal Analysis Menu:")
            print("1. Yearly Analysis")
            print("2. Year over Year Comparison")
            print("3. County with lowest population for given year")
            print("4. Averages")
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
            
            elif sub_choice == 3:
            # Prompt user for the year
                year_input = int(input("Enter the year: "))
                cdd.bfsLowestPopulation(year)

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
            print("6. bfsLowestPopulation")
            print("7. findCountyWithLargestFirearmCount")
            print("8. updateExistingData")

            sub_choice = int(input("Enter your sub-choice: "))

            if sub_choice == 1:
                print(big_o_analysis.addCrimeDataAnalysis_txt)
            elif sub_choice == 2:
                print(big_o_analysis.displayCrimeDataList_txt)
            elif sub_choice == 3:
                print(big_o_analysis.displayDataForCountyAndYear_txt)
            elif sub_choice == 4:
                print(big_o_analysis.yearlyAnalysis_text)
            elif sub_choice == 5:
                print(big_o_analysis.yearToYearAnalysis_txt)
            elif sub_choice == 6:
                print(big_o_analysis.bfsLowestPopulation_txt)
            elif sub_choice == 7:
                print(big_o_analysis.findCountyWithLargestFirearmCount_txt)
            elif sub_choice == 8:
                print(big_o_analysis.updateExistingData_txt)
            else:
                print("Invalid sub-choice. Please enter a valid option.")

        elif choice == 4:
            year_for_largest_firearm_count = int(input("Enter the year to find the county with the largest firearm count: "))
            cdd.findCountyWithLargestFirearmCount(year_for_largest_firearm_count)
        
        elif choice == 5:
            # Sub-menu for updating data
            print("\nUpdate Existing Data Menu:")
            print("1. Update Population and Index Data")
            print("2. Update Violent and Property Data")
            print("3. Update Firearm Data")
            print("4. Back to Main Menu")

            sub_choice = int(input("Enter your sub-choice: "))

            if sub_choice == 1:
                county_to_update = input("Enter the county to update: ").lower()
                year_to_update = int(input("Enter the year to update: "))
                new_population = float(input("Enter the new population: "))
                new_index_count = float(input("Enter the new index count: "))
                new_index_rate = float(input("Enter the new index rate: "))

                # Update population and index data
                cdd.updateExistingData(county_to_update, year_to_update, new_population, new_index_count, new_index_rate, 0, 0, 0, 0, 0, 0)

            elif sub_choice == 2:
                county_to_update = input("Enter the county to update: ").lower()
                year_to_update = int(input("Enter the year to update: "))
                new_violent_count = float(input("Enter the new violent count: "))
                new_violent_rate = float(input("Enter the new violent rate: "))
                new_property_count = float(input("Enter the new property count: "))
                new_property_rate = float(input("Enter the new property rate: "))

                # Update violent and property data
                cdd.updateExistingData(county_to_update, year_to_update, 0, 0, 0, new_violent_count, new_violent_rate, new_property_count, new_property_rate, 0, 0)

            elif sub_choice == 3:
                county_to_update = input("Enter the county to update: ").lower()
                year_to_update = int(input("Enter the year to update: "))
                new_firearm_count = float(input("Enter the new firearm count: "))
                new_firearm_rate = float(input("Enter the new firearm rate: "))

                # Update firearm data
                cdd.updateExistingData(county_to_update, year_to_update, 0, 0, 0, 0, 0, 0, 0, new_firearm_count, new_firearm_rate)

            elif sub_choice == 4:
                print("Returning to the Main Menu.")
            else:
                print("Invalid sub-choice. Please enter a valid option.")

if __name__ == "__main__":
    main()
