# Network Traffic

# Method is O(n)


class NetworkTrafficFormat:
    def __init__(self, data):
        # Constructor: Initializes the class with the provided data.
        self.data = data

    @staticmethod
    def convert_readable_format(value):
        # Static method to convert a numeric value into a human-readable format for data sizes.
        value = int(value)
        if value >= 10 ** 9:  # If the value is in gigabytes range.
            return f"{value / 10 ** 9: .2f} GB"
        elif value >= 10 ** 6:  # If the value is in megabytes range.
            return f"{value / 10 ** 6: .2f} MB"
        elif value >= 10 ** 3:  # If the value is in kilobytes range.
            return f"{value / 10 ** 3: .2f} KB"
        else:  # If the value is less than a kilobyte.
            return f"{value} Bytes"

    def print_converted_network_traffic(self):
        # Prints the network traffic data in both original and converted formats.
        print("Net Traffic Rx (Original) | Converted Rx | Net Traffic Tx (Original) | Converted Tx")
        print("-" * 80)  # Prints a separator line for visual clarity.
        for row in self.data:
            rx = row.get('Net Traffic Rx', '0')  # Get received (Rx) traffic.
            tx = row.get('Net Traffic Tx', '0')  # Get transmitted (Tx) traffic.
            rx_converted = self.convert_readable_format(rx)  # Convert Rx to readable format.
            tx_converted = self.convert_readable_format(tx)  # Convert Tx to readable format.

            # Print the original and converted values with alignment for readability.
            print(f"{rx:>20} | {rx_converted:>10} | {tx:>20} | {tx_converted:>10}")

    def calculate_daily_average(self):
        # Calculates and prints the daily average of network traffic.
        daily_data = {}  # Dictionary to store aggregated daily data.
        for row in self.data:
            date = row['Date Time'].split()[0]  # Extract date from 'Date Time'.
            rx_bytes = int(row.get('Net Traffic Rx', 0))  # Get Rx bytes.
            tx_bytes = int(row.get('Net Traffic Tx', 0))  # Get Tx bytes.

            # Aggregate data by date.
            if date in daily_data:
                daily_data[date]['rx_total'] += rx_bytes
                daily_data[date]['tx_total'] += tx_bytes
                daily_data[date]['count'] += 1
            else:
                daily_data[date] = {'rx_total': rx_bytes, 'tx_total': tx_bytes, 'count': 1}

        # Calculate and print the daily average for each date.
        for date, totals in daily_data.items():
            avg_rx = totals['rx_total'] / totals['count']  # Calculate average Rx.
            avg_tx = totals['tx_total'] / totals['count']  # Calculate average Tx.
            avg_rx_converted = self.convert_readable_format(avg_rx)  # Convert average Rx.
            avg_tx_converted = self.convert_readable_format(avg_tx)  # Convert average Tx.
            print(f"{date}: Avg Net Traffic Rx: {avg_rx_converted}, Avg Net Traffic Tx: {avg_tx_converted}")

    def prompt_daily_average(self):
        # Method to prompt the user and display daily average network traffic.
        self.print_converted_network_traffic()  # Print the converted network traffic first.
        user_response = input("Do you want to view the Network Traffic daily average? (yes/no): ")
        if user_response.lower() == 'yes':
            self.calculate_daily_average()  # If user confirms, calculate and print daily average.