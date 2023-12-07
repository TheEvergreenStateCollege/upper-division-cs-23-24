import os
import csv
import sys

class CrimeData:
    def __init__(self, County, Year, Population, indexCount, indexRate, violentCount, violentRate, propertyCount, propertyRate, firearmCount, firearmRate):
        self.County = County
        self.Year = Year
        self.Population = Population
        self.indexCount = indexCount
        self.indexRate = indexRate
        self.violentCount = violentCount
        self.violentRate = violentRate
        self.propertyCount = propertyCount
        self.propertyRate = propertyRate
        self.firearmCount = firearmCount
        self.firearmRate = firearmRate

    def __str__(self):
        # String representation of the CrimeData object for easy debugging
        return f"CrimeData{{County='{self.County}', Year='{self.Year}', Population='{self.Population}', " \
               f"indexCount='{self.indexCount}', indexRate='{self.indexRate}', violentCount='{self.violentCount}', " \
               f"violentRate='{self.violentRate}', propertyCount='{self.propertyCount}', " \
               f"propertyRate='{self.propertyRate}', firearmCount='{self.firearmCount}', firearmRate='{self.firearmRate}'}}"

class CrimeDatabase:
    def __init__(self):
        # Initialize an empty list to store CrimeData objects
        self.crimeDataList = []

    def addCrimeData(self, crimeData):
        # Add a CrimeData object to the list
        print(f"Adding crime data: {crimeData}")  # Add this line for debugging
        self.crimeDataList.append(crimeData)

    def displayCrimeDataList(self):
        # Display all CrimeData objects in the list
        for crimeData in self.crimeDataList:
            print(crimeData)

    def displayDataForCountyAndYear(self, county, year):
        # Filter and display CrimeData objects for a specific county and year
        data_for_county_and_year = [data for data in self.crimeDataList if data.County.lower() == county.lower() and int(data.Year) == year]

        if not data_for_county_and_year:
            print(f"No crime data available for {county} County in {year}.")
            return

        print(f"\nCrime data for {county} County in {year}:")
        for crime_data in data_for_county_and_year:
            print(crime_data)
    
    def yearlyAnalysis(self, county, year1, year2):
        # Filter data for the specified county and two years
        data_for_county_and_years = [data for data in self.crimeDataList if
                                      data.County.lower() == county.lower() and int(data.Year) in [year1, year2]]

        if not data_for_county_and_years:
            print(f"No crime data available for {county} County in {year1} or {year2}.")
            return

        print(f"\nYearly Analysis for {county} County:")
        for crime_data in data_for_county_and_years:
            print(f"\nData for {crime_data.Year}:")
            print(f"Index Count: {crime_data.indexCount}, Index Rate: {crime_data.indexRate}")
            print(f"Violent Count: {crime_data.violentCount}, Violent Rate: {crime_data.violentRate}")
            print(f"Property Count: {crime_data.propertyCount}, Property Rate: {crime_data.propertyRate}")
            print(f"Firearm Count: {crime_data.firearmCount}, Firearm Rate: {crime_data.firearmRate}")
            print("-" * 30)
    def yearToYearAnalysis(self, county, year1, year2):
        # Filter data for the specified county and two years
        data_for_county_and_years = [data for data in self.crimeDataList if
                                      data.County.lower() == county.lower() and int(data.Year) in [year1, year2]]

        if not data_for_county_and_years:
            print(f"No crime data available for {county} County in {year1} or {year2}.")
            return

        print(f"\nYear-to-Year Analysis for {county} County ({year1} to {year2}):")
        for crime_data in data_for_county_and_years:
            print(f"\nData for {crime_data.Year}:")
            print(f"Index Count: {crime_data.indexCount}")
            print(f"Violent Count: {crime_data.violentCount}")
            print(f"Property Count: {crime_data.propertyCount}")
            print(f"Firearm Count: {crime_data.firearmCount}")
            print("-" * 30)

        # Calculate YoY percentage change
        data_year1 = next((data for data in data_for_county_and_years if data.Year == str(year1)), None)
        data_year2 = next((data for data in data_for_county_and_years if data.Year == str(year2)), None)

        if data_year1 and data_year2:
            print("\nPercentage Change:")
            print(f"Index Count: {calculate_percentage_change(data_year1.indexCount, data_year2.indexCount)}%")
            print(f"Violent Count: {calculate_percentage_change(data_year1.violentCount, data_year2.violentCount)}%")
            print(f"Property Count: {calculate_percentage_change(data_year1.propertyCount, data_year2.propertyCount)}%")
            print(f"Firearm Count: {calculate_percentage_change(data_year1.firearmCount, data_year2.firearmCount)}%")
            print("-" * 30)

def calculate_percentage_change(value1, value2):
    if value1 == 0:
        return "N/A"  # Avoid division by zero
    return round(((value2 - value1) / abs(value1)) * 100, 2)


def main():
    cdd = CrimeDatabase()

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

                # Add crime data
                cdd.addCrimeData(CrimeData(*row))

    # Print loaded data for debugging
    cdd.displayCrimeDataList()

    # Display menu
    choice = 0
    while choice != 4:
        print("\nNew York Criminal Justice Crime Database Menu:")
        print("1. Display data for a specific county and year")
        print("2. Temporal Analysis")
        print("3. Big 0 Analysis")
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

            if sub_choice == 1:
                # Implement Yearly Analysis logic
                pass
            elif sub_choice == 2:
                # Implement Year over Year Comparison logic
                pass
            elif sub_choice == 3:
                # Implement Comparative Analysis logic
                pass
            elif sub_choice == 4:
                # Implement Moving Averages logic
                pass
            elif sub_choice == 5:
                # Implement Firearm Analysis logic
                pass
            elif sub_choice == 6:
                print("Returning to the Main Menu.")
            else:
                print("Invalid sub-choice. Please enter a valid option.")
        elif choice == 3:
            print("Exiting the program. Goodbye!")
        else:
            print("Invalid choice. Please enter 1, 2, or 3.")

if __name__ == "__main__":
    main()
