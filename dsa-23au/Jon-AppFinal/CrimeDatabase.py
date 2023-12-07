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
        return f"CrimeData{{County='{self.County}', Year={self.Year}, Population={self.Population}, " \
               f"indexCount={self.indexCount}, indexRate={self.indexRate}, violentCount={self.violentCount}, " \
               f"violentRate={self.violentRate}, propertyCount={self.propertyCount}, " \
               f"propertyRate={self.propertyRate}, firearmCount={self.firearmCount}, firearmRate={self.firearmRate}}}"

class CrimeDatabase:
    def __init__(self):
        # Initialize an empty list to store CrimeData objects
        self.crimeDataList = []

    addCrimeDataAnalysis_txt = """

    Explanation:
    - The time complexity of adding an element to a list in Python is generally O(1).
      However, there might be cases where the list needs to be resized, resulting in a time complexity of O(n),
      where n is the number of elements in the list. In typical scenarios, especially with a relatively small number
      of elements, the `append` operation is considered constant time.

    - The space complexity of the `addCrimeData` method is O(1). This is because the method only adds a CrimeData object
      to the existing list, and the space required for the new object is constant.

    Summary:
    - Average-case time complexity: O(1) for adding a single element.
    - Space complexity: O(1) since it does not create additional data structures that grow with the input size.
    """
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
        data_for_county_and_year = [data for data in self.crimeDataList if data.County.lower() == county.lower() and data.Year == year]

        if not data_for_county_and_year:
            print(f"No crime data available for {county} County in {year}.")
            return

        print(f"\nCrime data for {county} County in {year}:")
        for crime_data in data_for_county_and_year:
            print(crime_data)

    yearlyAnalysis_text = """
    Time Complexity (Big O):

    The time complexity of this method is O(n), where n is the number of elements in `self.crimeDataList`. Here's the breakdown:

    - The list comprehension `data_for_county_and_years` iterates through each element in `self.crimeDataList` once, making it O(n).
    - The subsequent loop that prints the data for each crime_data is also O(n) because it iterates through the filtered list.

    Overall, the dominant factor is the single iteration through the data in `self.crimeDataList`.

    Space Complexity (Big O):

    The space complexity is also O(n) because the method uses additional space proportional to the number of elements in `self.crimeDataList`. The primary space-consuming operation is the creation of the `data_for_county_and_years` list, which can potentially hold all the elements from `self.crimeDataList`.

    Note: If the filtering operation in the list comprehension results in a smaller list than the original `self.crimeDataList`, the space complexity could be less than O(n) but still linear.

    In summary, the method has a linear time and space complexity based on the size of the input data in `self.crimeDataList`.
    """

    def yearlyAnalysis(self, county, year1, year2):
        # Filter data for the specified county and two years
        data_for_county_and_years = [data for data in self.crimeDataList if
                                      data.County.lower() == county.lower() and data.Year in [year1, year2]]

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

    def yearOverYearComparison(self, county, year1, year2):
        # Filter data for the specified county and two years
        data_for_county_and_years = [data for data in self.crimeDataList if
                                      data.County.lower() == county.lower() and data.Year in [year1, year2]]

        if not data_for_county_and_years:
            print(f"No crime data available for {county} County in {year1} or {year2}.")
            return

        print(f"\nYear-to-Year Analysis for {county} County ({year1} to {year2}):")
        for crime_data in data_for_county_and_years:
            print(f"\nData for {crime_data.Year}:")
            print(f"Firearm Count: {crime_data.firearmCount}")
            print("-" * 30)

        # Calculate YoY percentage change for firearms
        data_year1 = next((data for data in data_for_county_and_years if data.Year == year1), None)
        data_year2 = next((data for data in data_for_county_and_years if data.Year == year2), None)

        if data_year1 and data_year2:
            firearm_count_year1 = float(data_year1.firearmCount)  # Convert string to float
            firearm_count_year2 = float(data_year2.firearmCount)  # Convert string to float

            if firearm_count_year1 == 0:
                print("\nPercentage Change for Firearm Count: N/A (Base year has zero counts)")
            else:
                percentage_change_firearm = ((firearm_count_year2 - firearm_count_year1) / firearm_count_year1) * 100
                print(f"\nPercentage Change for Firearm Count: {round(percentage_change_firearm, 2)}%")
                print("-" * 30)

def convert_input_to_float(value):
    try:
        return float(value)
    except ValueError:
        print(f"Error: Unable to convert '{value}' to float. Defaulting to 0.")
        return 0.0

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

                # Convert relevant columns to appropriate types when adding crime data
                converted_row = [
                    row[0],  # County
                    int(row[1]),  # Year (convert to int)
                    convert_input_to_float(row[2]),  # Population (convert to int)
                    convert_input_to_float(row[3]),  # Index Count (convert to float)
                    convert_input_to_float(row[4]),  # Index Rate (convert to
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
                print("Placeholder message for displayCrimeDataList analysis. Fill in your analysis later.")
            elif sub_choice == 3:
                print("Placeholder message for displayDataForCountyAndYear analysis. Fill in your analysis later.")
            elif sub_choice == 4:
                print(cdd.yearlyAnalysis_text)
            elif sub_choice == 5:
                print("Placeholder message for yearOverYearComparison analysis. Fill in your analysis later.")
            else:
                print("Invalid sub-choice. Please enter a valid option.")
        elif choice == 4:
            print("Exiting the program.")
        else:
            print("Invalid choice. Please enter 1, 2, 3, or 4.")

if __name__ == "__main__":
    main()
