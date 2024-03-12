# ESSID,Quality,Category
# NetworkName,68/70,Excellent
# NetworkName,52/70,Good
# NetworkName,42/70,Fair
# NetworkName,37/70,Weak
# NetworkName,28/70,Poor

# Method is O(n), This linear complexity, O(n),
# means the execution time of the method scales linearly with the size of the input dataset.

class WiFiCategorizer:
    def __init__(self, data):
        self.data = data

    def categorize_strengths(self):
        categories = {
            'Poor Range': [],
            'Weak Range': [],
            'Fair Range': [],
            'Good Range': [],
            'Excellent Range': []
        }

        for row in self.data:
            try:
                strength = int(row['Wi-Fi -dBm'].split('/')[0])
                if strength <= 20:
                    categories['Poor Range'].append(row)
                elif strength <= 40:
                    categories['Weak Range'].append(row)
                elif strength <= 60:
                    categories['Fair Range'].append(row)
                elif strength <= 80:
                    categories['Good Range'].append(row)
                else:
                    categories['Excellent Range'].append(row)
            except Exception as e:
                print(f"Error processing row {row}: {e}")

        return categories