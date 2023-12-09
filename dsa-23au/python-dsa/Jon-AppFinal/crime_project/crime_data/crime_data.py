# crime_data.py
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
        return f"CrimeData{{County='{self.County}', Year='{self.Year}', Population='{self.Population}', " \
               f"indexCount='{self.indexCount}', indexRate='{self.indexRate}', violentCount='{self.violentCount}', " \
               f"violentRate='{self.violentRate}', propertyCount='{self.propertyCount}', " \
               f"propertyRate='{self.propertyRate}', firearmCount='{self.firearmCount}', firearmRate='{self.firearmRate}'}}"

