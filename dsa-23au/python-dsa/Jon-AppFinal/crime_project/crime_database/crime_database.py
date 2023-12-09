# crime_database.py

class CrimeDatabase:
    def __init__(self):
        # Initialize an empty list to store crime data objects
        self.crimeDataList = []

    def addCrimeData(self, crimeData):
        # Add crime data object to the list
        print(f"Adding crime data: {crimeData}")
        self.crimeDataList.append(crimeData)
     
    def findCountyWithLargestFirearmCount(self, year):
        county_dict = self.recursive_search(0, year, {})
    
        if county_dict:
            max_county = max(county_dict, key=county_dict.get)
            max_county_count = county_dict[max_county]
            print(f"The county with the largest firearm count in {year} is {max_county.capitalize()} with {max_county_count} incidents.")
        else:
            print(f"No data found for {year}.")

    def recursive_search(self, index, year, county_dict):
        if index == len(self.crimeDataList):
            return county_dict

        data = self.crimeDataList[index]

        if data.Year == year and data.FirearmCount is not None and data.FirearmCount >= 0:
            county = data.County.lower()
            firearm_count = data.FirearmCount

            if county in county_dict:
                county_dict[county] += firearm_count
            else:
                county_dict[county] = firearm_count

        return self.recursive_search(index + 1, year, county_dict)
    
     
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
    #measures the rate of growth in a specific metric over two comparable periods, such as the current and prior period.
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
    
   
    def updateExistingData(self, county, year, population, index_count, index_rate, violent_count, violent_rate, property_count, property_rate, firearm_count, firearm_rate):
        # Update existing data for a specific county and year using the dictionary
        key = (county.lower(), str(year))
        
        if key in self.crimeDataMap:
            # Update data fields if the key exists
            data = self.crimeDataMap[key]
            data.Population = population
            data.IndexCount = index_count
            data.IndexRate = index_rate
            data.ViolentCount = violent_count
            data.ViolentRate = violent_rate
            data.PropertyCount = property_count
            data.PropertyRate = property_rate
            data.FirearmCount = firearm_count
            data.FirearmRate = firearm_rate

            print(f"Data updated for {county} County in {year}.")
        else:
            print(f"No data found for {county} County in {year}.")
    
    def bfsLowestPopulation(self, year):
        # Dictionary to store visited counties and their populations
        visited_counties = {}

        # Queue for BFS
        queue = deque()

        # Enqueue all counties for initial level (root)
        for data in self.crimeDataList:
            county = data.County.lower()
            if county not in visited_counties:
                visited_counties[county] = data.Population
                queue.append(county)

        # BFS
        while queue:
            current_county = queue.popleft()

            # Get neighbors (counties with the same year)
            neighbors = [data.County.lower() for data in self.crimeDataList if data.Year == year]

            for neighbor in neighbors:
                if neighbor not in visited_counties:
                    # Enqueue unvisited neighbors
                    visited_counties[neighbor] = self.getPopulation(neighbor, year)
                    queue.append(neighbor)

        # Find the county with the lowest population
        lowest_population_county = min(visited_counties, key=visited_counties.get)
        lowest_population = visited_counties[lowest_population_county]

        print(f"The county with the lowest population in {year} is {lowest_population_county.capitalize()} with a population of {lowest_population}.")

    def getPopulation(self, county, year):
        # Helper function to get the population of a county for a specific year
        for data in self.crimeDataList:
            if data.County.lower() == county and int(data.Year) == year:
                return data.Population
        return None

 
