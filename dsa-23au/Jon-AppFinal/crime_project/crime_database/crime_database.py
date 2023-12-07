# crime_database.py

class CrimeDatabase:
    def __init__(self):
        # Initialize an empty list to store crime data objects
        self.crimeDataList = []

    def addCrimeData(self, crimeData):
        # Add crime data object to the list
        print(f"Adding crime data: {crimeData}")
        self.crimeDataList.append(crimeData)

    def displayCrimeDataList(self):
        # Display all crime data objects in the list
        for crimeData in self.crimeDataList:
            print(crimeData)

    def displayDataForCountyAndYear(self, county, year):
        # Filter data for a specific county and year
        data_for_county_and_year = [data for data in self.crimeDataList if data.County.lower() == county.lower() and int(data.Year) == year]

        if not data_for_county_and_year:
            # If no data is found, print a message
            print(f"No crime data available for {county} County in {year}.")
            return

        # Display crime data for the specified county and year
        print(f"\nCrime data for {county} County in {year}:")
        for crime_data in data_for_county_and_year:
            print(crime_data)

    def yearlyAnalysis(self, county, year1, year2):
        # Filter data for a specific county and range of years
        data_for_county_and_years = [data for data in self.crimeDataList if
                                      data.County.lower() == county.lower() and int(data.Year) in [year1, year2]]

        if not data_for_county_and_years:
            # If no data is found, print a message
            print(f"No crime data available for {county} County in {year1} or {year2}.")
            return

        # Perform yearly analysis and display the results
        print(f"\nYearly Analysis for {county} County:")
        for crime_data in data_for_county_and_years:
            print(f"\nData for {crime_data.Year}:")
            print(f"Index Count: {crime_data.indexCount}, Index Rate: {crime_data.indexRate}")
            print(f"Violent Count: {crime_data.violentCount}, Violent Rate: {crime_data.violentRate}")
            print(f"Property Count: {crime_data.propertyCount}, Property Rate: {crime_data.propertyRate}")
            print(f"Firearm Count: {crime_data.firearmCount}, Firearm Rate: {crime_data.firearmRate}")
            print("-" * 30)

    def yearToYearAnalysis(self, county, year1, year2):
        # Filter data for a specific county and range of years
        data_for_county_and_years = [data for data in self.crimeDataList if
                                      data.County.lower() == county.lower() and int(data.Year) in [year1, year2]]

        if not data_for_county_and_years:
            # If no data is found, print a message
            print(f"No crime data available for {county} County in {year1} or {year2}.")
            return

        # Perform year-to-year analysis and display the results
        print(f"\nYear-to-Year Analysis for {county} County ({year1} to {year2}):")
        for crime_data in data_for_county_and_years:
            print(f"\nData for {crime_data.Year}:")
            print(f"Index Count: {crime_data.indexCount}")
            print(f"Violent Count: {crime_data.violentCount}")
            print(f"Property Count: {crime_data.propertyCount}")
            print(f"Firearm Count: {crime_data.firearmCount}")
            print("-" * 30)

        # Extract data for the specified years
        data_year1 = next((data for data in data_for_county_and_years if data.Year == str(year1)), None)
        data_year2 = next((data for data in data_for_county_and_years if data.Year == str(year2)), None)

        if data_year1 and data_year2:
            # If data for both years is available, calculate and display percentage change
            print("\nPercentage Change:")
            print(f"Index Count: {calculate_percentage_change(data_year1.indexCount, data_year2.indexCount)}%")
            print(f"Violent Count: {calculate_percentage_change(data_year1.violentCount, data_year2.violentCount)}%")
            print(f"Property Count: {calculate_percentage_change(data_year1.propertyCount, data_year2.propertyCount)}%")
            print(f"Firearm Count: {calculate_percentage_change(data_year1.firearmCount, data_year2.firearmCount)}%")
            print("-" * 30)
