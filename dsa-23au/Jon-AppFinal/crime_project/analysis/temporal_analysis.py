class TemporalAnalysis:
    @staticmethod
    def calculate_percentage_change(value1, value2):
        if value1 == 0:
            return "N/A"  # Avoid division by zero
        return round(((value2 - value1) / abs(value1)) * 100, 2)

def yearlyAnalysis(crime_data_list, county, year1, year2):
    data_for_county_and_years = [data for data in crime_data_list if
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

def yearToYearAnalysis(crime_data_list, county, year1, year2): #FIXME
    data_for_county_and_years = [data for data in crime_data_list if
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

    data_year1 = next((data for data in data_for_county_and_years if data.Year == str(year1)), None)
    data_year2 = next((data for data in data_for_county_and_years if data.Year == str(year2)), None)

    if data_year1 and data_year2:
        print("\nPercentage Change:")
        print(f"Index Count: {TemporalAnalysis.calculate_percentage_change(data_year1.indexCount, data_year2.indexCount)}%")
        print(f"Violent Count: {TemporalAnalysis.calculate_percentage_change(data_year1.violentCount, data_year2.violentCount)}%")
        print(f"Property Count: {TemporalAnalysis.calculate_percentage_change(data_year1.propertyCount, data_year2.propertyCount)}%")
        print(f"Firearm Count: {TemporalAnalysis.calculate_percentage_change(data_year1.firearmCount, data_year2.firearmCount)}%")
        print("-" * 30)
    
     