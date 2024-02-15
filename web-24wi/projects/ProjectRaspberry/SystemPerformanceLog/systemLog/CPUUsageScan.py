# Method is O(n)
 
# We iterate through each category in categorized_data. 
# The number of categories is fixed and does not depend on the size of the input data

# For each category, we iterate through each row in rows. 
# The total number of rows across all categories is proportional to the size of the input data.

# Inside the inner loop, we perform constant-time operations for each row conversion to float and conduct arithmetic operations.

import datetime


class CPUScanCategorizer:
    def __init__(self, data):
        self.data = data
        self.time_ranges = {
            'Morning Scan': {'start': datetime.time(5, 0), 'end': datetime.time(7, 0)},
            'Afternoon Scan': {'start': datetime.time(12, 0), 'end': datetime.time(14, 0)},
            'Evening Scan': {'start': datetime.time(18, 0), 'end': datetime.time(20, 0)}
        }

    def categorize_time_range(self):
        categorized_data = {key: [] for key in self.time_ranges}

        for row in self.data:
            date_time_str = row.get('Date Time')
            if not date_time_str:
                print("Missing or invalid 'Date Time'")
                continue  # Skip this row if 'Date Time' is missing

            try:
                dt = datetime.datetime.strptime(date_time_str, '%Y-%m-%d %H:%M:%S')
            except ValueError:
                print(f"Error parsing 'Date Time': {date_time_str}")
                continue  # Skip this row if the date can't be parsed

            for range_name, times in self.time_ranges.items():
                if times['start'] <= dt.time() <= times['end']:
                    categorized_data[range_name].append(row)
                    break

        return categorized_data

    def calculate_averages(self, categorized_data):
        averages = {}
        for range_name, rows in categorized_data.items():
            total_cpu_usage = 0
            count = 0
            for row in rows:
                try:
                    cpu_usage = float(row['CPU Usage'])
                    total_cpu_usage += cpu_usage
                    count += 1
                except ValueError as e:
                    print(f"Error processing CPU Usage for row {row}: {e}")
            averages[range_name] = total_cpu_usage / count if count > 0 else 0

        return averages