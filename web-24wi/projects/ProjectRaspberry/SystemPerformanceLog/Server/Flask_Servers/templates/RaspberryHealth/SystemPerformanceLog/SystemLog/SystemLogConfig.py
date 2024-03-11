# Path to the CSV file
#csv_log_path = ('templates\RaspberryHealth\SystemPerformanceLog\SystemLog\SystemLogResources\os_log.csv')

csv_log_path = None

def set_csv_log_path(path):
    global csv_log_path
    csv_log_path = path