# Method is O(n) 

# We iterate through each key-value pair,
# The number of categories is fixed and does not depend on the size of the input data,
# For each category, we iterate through each row inside the inner loop, 
# We perform constant-time operations for each row, 
# Converting it to an integer, and arithmetic operations

class WiFiStrengthAverageCalculator:
    def __init__(self, categorized_strengths):
        self.categorized_strengths = categorized_strengths

    def calculate_averages(self):
        averages = {}
        for category, rows in self.categorized_strengths.items():
            total_strength = 0
            count = 0
            for row in rows:
                try:
                    strength = int(row['Wi-Fi -dBm'].split('/')[0])
                    total_strength += strength
                    count += 1
                except Exception as e:
                    print(f"Error processing row {row}: {e}")

            averages[category] = total_strength / count if count > 0 else 0

        return averages