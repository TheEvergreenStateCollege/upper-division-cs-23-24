# The purpose of this algorithm is to calculate the total of the CPU Temperature column
# Next the program will average the total and print the data.

# Method is O(n), This is a linear complexity, 
# meaning the time it takes to execute the method scales linearly with the size of the input dataset.


class CPUTempAverages:
    def __init__(self, data):
        self.data = data

    def total_average(self):
        total_temp = 0
        count = 0
        for row in self.data:
            try:
                temp = float(row['CPU Temp'])
                total_temp += temp
                count += 1
            except ValueError as e:
                print(f"Error converting CPU Temp to float: {e}")
        average_temp = total_temp / count if count else 0
        return total_temp, average_temp