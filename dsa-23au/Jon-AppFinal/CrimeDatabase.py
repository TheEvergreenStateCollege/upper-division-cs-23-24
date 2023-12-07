import csv

class CrimeDatabase:
    def __init__(self):
        self.crimeDataList = []

    def addCrimeData(self, crimeData):
        print(f"Adding crime data: {crimeData}")  # Add this line for debugging
        self.crimeDataList.append(crimeData)

    def displayCrimeDataList(self):
        for crimeData in self.crimeDataList:
            print(crimeData)

    def displayDataForCountyAndYear(self, county, year):
        data_for_county_and_year = [data for data in self.crimeDataList if data.County.lower() == county.lower() and data.Year == str(year)]

        if not data_for_county_and_year:
            print(f"No crime data available for {county} County in {year}.")
            return

        print(f"\nCrime data for {county} County in {year}:")
        for crime_data in data_for_county_and_year:
            print(crime_data)

# Assuming you have the CrimeData class defined somewhere in your code

def main():
    cdd = CrimeDatabase()

    # Specify the path to your CSV file
    csv_file_path = '/workspace/upper-division-cs/dsa-23au/Jon-AppFinal/Crime.csv'

    # Load data from CSV file
    with open(csv_file_path, 'r') as file:
        csv_reader = csv.reader(file)
        next(csv_reader)  # Skip header row

        for row in csv_reader:
            if len(row) >= 12:
                print(row)  # Add this line for debugging

                # Add crime data
                cdd.addCrimeData(CrimeData(*row))

    # Print loaded data for debugging
    cdd.displayCrimeDataList()

    # Display menu
    choice = 0
    while choice != 4:
        print("\nNew York Criminal Justice Crime Database 
