# Disk Usage Analysis

# Method is O(n): 
# The DiskAnalysis class's overall complexity is determined by its most complex method, which is find_nearest_change.  
# Therefore, the Big-O complexity of the DiskAnalysis script is O(n), where n is the number of elements in the self.data list.

class DiskAnalysis:
    def __init__(self, data):
        self.data = data

    # This method accesses the last element of the self.data list and prints its disk usage. 
    # Accessing an element in a list by index is a constant time operation, O(1). 
    def print_current_disk_usage(self):
        if not self.data:
            print("No data available to analyze.")
            return

        try:
            current_usage = self.data[-1].get('Disk Usage', 'N/A')
            print(f"Current Logged Disk Usage: {current_usage}")
        except IndexError:
            print("Error: Data list is empty.")

    # This method,
    # It involves iterating through the self.data list in reverse (excluding the last element)
    # to find the disk usage that is closest to the current usage. 
    def find_nearest_change(self):
        if not self.data:
            print("No data available to analyze.")
            return

        try:
            current_usage_str = self.data[-1].get('Disk Usage', '0').rstrip('%')
            if not current_usage_str:
                print("Error: Current Disk Usage is not available.")
                return

            current_usage = float(current_usage_str)
            if current_usage == 0:
                print("Current Disk Usage is 0%. No valid comparison can be made.")
                return

            closest_diff = float('inf')
            closest_usage = None

            for row in reversed(self.data[:-1]):
                usage_str = row.get('Disk Usage', '0').rstrip('%')
                usage = float(usage_str)
                diff = abs(usage - current_usage)

                if diff < closest_diff:
                    closest_diff = diff
                    closest_usage = usage

                if diff > 0:  # Stop if a change is found
                    break

            if closest_usage is not None and current_usage != 0:
                deviation = ((closest_usage - current_usage) / current_usage) * 100
                print(f"Nearest changed Disk Usage: {closest_usage}%, Deviation: {deviation:.2f}%")
            else:
                print("No change in Disk Usage was found.")
        except ValueError:
            print("Error: Invalid data format for Disk Usage.")