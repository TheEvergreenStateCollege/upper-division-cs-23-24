import os
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
import numpy as np

# create a relative path from the server to the os.log.csv file.
current_dir = os.path.dirname(os.path.abspath(__file__))
file_path = os.path.join(current_dir, 'SystemLogResources', 'os_log.csv')


# Load the CSV data
data = pd.read_csv(file_path)
cpu_temps = data['CPU Temp'].dropna()  # Ensure we drop any NaN values

# Determine the number of rows needed and the maximum row length
n_rows = 10
max_row_length = np.ceil(len(cpu_temps) / n_rows).astype(int)

# Reshape the data into a 2D format, padding with NaN if necessary
reshaped_temps = np.array_split(cpu_temps, n_rows)
heatmap_data = np.array([np.pad(temps, (0, max_row_length - len(temps)), constant_values=np.nan)
                         for temps in reshaped_temps])

# Set up the matplotlib figure
plt.figure(figsize=(12, 9))  # Increase figure size

# Generate a heatmap
sns.heatmap(heatmap_data, annot=True, cmap='coolwarm', cbar=True, fmt='.1f')

# Adjust layout
plt.title('CPU Temperature Heatmap')
plt.xlabel('Measurement Index')
plt.ylabel('CPU Temp Segment')

# Save the heatmap to a file
def generate_cpu_temp_heatmap():
    heatmap_save_path = 'static/assets/HeatMap.png'
    plt.savefig(heatmap_save_path)  
    plt.close()
    return 'assets/HeatMap.png'
