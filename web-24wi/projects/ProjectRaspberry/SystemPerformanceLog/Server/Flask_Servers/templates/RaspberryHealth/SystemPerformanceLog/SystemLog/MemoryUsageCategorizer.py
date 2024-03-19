# Method is O(n).

 
from datetime import datetime

class MemoryUsageCategorizer:
    def __init__(self, data):
        self.data = data
        self.categorized_data = {'Morning': [], 'Afternoon': [], 'Evening': []}
        self.date_info = {'Morning': [], 'Afternoon': [], 'Evening': []}

    @staticmethod
    def get_hour_from_datetime(date_time_str):
        try:
            return datetime.strptime(date_time_str, "%Y-%m-%d %H:%M:%S").hour
        except ValueError:
            print(f"Error parsing date time: {date_time_str}")
            return None  # Or some default value

    def categorize_by_time(self):
        for row in self.data:
            date_time_str = row.get('Date Time')
            if not date_time_str:
                print("Missing or invalid 'Date Time'")
                continue  # Skip this row if 'Date Time' is missing
            hour = self.get_hour_from_datetime(date_time_str)
            if hour is None:
                continue  # Skip this row if the hour could not be parsed
            if 5 <= hour < 7:
                self.categorized_data['Morning'].append(float(row['Memory Usage']))
                self.date_info['Morning'].append(row['Date Time'])
            elif 12 <= hour < 14:
                self.categorized_data['Afternoon'].append(float(row['Memory Usage']))
                self.date_info['Afternoon'].append(row['Date Time'])
            elif 18 <= hour < 20:
                self.categorized_data['Evening'].append(float(row['Memory Usage']))
                self.date_info['Evening'].append(row['Date Time'])
        return self.categorized_data

    # This method checks for deviations in memory usage from the mean. 
    # It iterates over each category and then over each data point in that category, 
    # comparing it to the mean.
    def check_deviations(self, means):
        deviations = {'Above' : [], 'Below': []}
        for category in self.date_info:
            for usage, date in zip(self.categorized_data[category], self.date_info[category]):
                if usage > means[category] + 10.00:
                    deviations['Above'].append((date, usage))
                elif usage < means[category] - 10.00:
                    deviations['Below'].append((date, usage))
        return deviations

    def calculate_means(self, categorized_data):
        means = {}
        for time_frame, usages in categorized_data.items():
            if usages:
                means[time_frame] = sum(usages) / len(usages)
            else:
                means[time_frame] = 0
        return means

    # This method computes the variation from the lowest and highest mean memory usages. 
    # It involves iterating over a fixed number of categories (constant) and performing arithmetic operations, 
    # which are constant time. 
    def calculate_variation(self, means):
        min_mean = min(means.values())
        max_mean = max(means.values())
        variation_from_lowest = {k: v - min_mean for k, v in means.items() if v != min_mean}
        variation_from_highest = {k: max_mean - v for k, v in means.items() if v != max_mean}
        return variation_from_lowest, variation_from_highest

    # This is the primary method that ties together the other methods.
    # It sequentially calls categorize_by_time, calculate_means, calculate_variation, and check_deviations. 
    # The complexity of this method is the sum of the complexities of these individual methods.  
    # However, since each method has a complexity of O(n) and they all operate on the same dataset, 
    # the overall complexity of analyze_memory_usage remains O(n).
    def analyze_memory_usage(self):
        categorized_data = self.categorize_by_time()
        means = self.calculate_means(categorized_data)
        variation_from_lowest, variation_from_highest = self.calculate_variation(means)

        for time_frame, mean in means.items():
            print(f"{time_frame} Mean Memory Usage: {mean:.2f}")

        print("Variation from Lowest Mean Memory Usage:")
        for time_frame, var in variation_from_lowest.items():
            print(f"{time_frame}: {var:.2f}")

        print("Variation from Highest Mean Memory Usage:")
        for time_frame, var in variation_from_highest.items():
            print(f"{time_frame}: {var:.2f}")

        deviations = self.check_deviations(means)
        if deviations['Above'] or deviations['Below']:
            response = input("Do you want to view the data for the days with significant deviations? (yes/no): ")
            if response.lower() == 'yes':
                if deviations['Above']:
                    print("Days with Memory Usage Greater than 10.00 Above the Mean:")
                    for date, usage in deviations['Above']:
                        print(f"Date: {date}, Memory Usage: {usage: .2f}")
                if deviations['Below']:
                    print("Days with Memory Usage Less Than 10.00 Below the Mean:")
                    for date, usage in deviations['Below']:
                        print(f"Date: {date}, Memory Usage: {usage: .2f}")