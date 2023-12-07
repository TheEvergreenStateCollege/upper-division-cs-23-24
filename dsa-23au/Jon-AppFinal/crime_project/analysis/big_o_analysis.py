# big_o_analysis.py

class BigOAnalysis:
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
    yearToYearAnalysis_txt = """
        Time Complexity (Big O):

        The time complexity of this method is O(n), where n is the number of elements in `crime_data_list`. Here's the breakdown:

        - The list comprehension `data_for_county_and_years` iterates through each element in `crime_data_list` once, making it O(n).
        - The subsequent loop that prints the data for each `crime_data` is also O(n) because it iterates through the filtered list.

        Overall, the dominant factor is the single iteration through the data in `crime_data_list`. Therefore, the time complexity of the `yearToYearAnalysis` method is O(n), where n is the number of elements in `crime_data_list`.

        Space Complexity (Big O):

        The space complexity is also O(n) because the method uses additional space proportional to the number of elements in `crime_data_list`. The primary space-consuming operation is the creation of the `data_for_county_and_years` list, which can potentially hold all the elements from `crime_data_list`.

        In summary, the method has a linear time and space complexity based on the size of the input data in `crime_data_list`.
    """
    # Big O Analysis for displayCrimeDataList
    displayCrimeDataList_txt = """
        Time Complexity (Big O):

        The time complexity of this method is O(n), where n is the number of elements in `self.crimeDataList`. The method iterates through each element in the list once to display the data, resulting in a linear time complexity.

        Space Complexity (Big O):

        The space complexity is O(1) since the method does not create additional data structures that grow with the input size. It only uses a constant amount of space to store the current element during iteration.

        In summary, the method has a linear time complexity based on the size of the input data and constant space complexity.
    """

    # Big O Analysis for displayDataForCountyAndYear
    displayDataForCountyAndYear_txt = """
        Time Complexity (Big O):

        The time complexity of this method is O(n), where n is the number of elements in `self.crimeDataList`. The method filters the list based on the specified county and year, resulting in a linear time complexity.

        Space Complexity (Big O):

        The space complexity is O(m), where m is the number of elements that match the specified county and year. The method creates a new list, `data_for_county_and_year`, to store the filtered elements. In the worst case, this list can hold all elements matching the criteria.

        In summary, the method has a linear time complexity based on the size of the input data and linear space complexity based on the number of elements that match the specified county and year.
    """

