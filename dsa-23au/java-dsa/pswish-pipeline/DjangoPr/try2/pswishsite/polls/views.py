from django.http import HttpResponse
from django.shortcuts import render
from .models import DriverData
from .DriverData import DriverToDriveData

# def index(request):
#     return HttpResponse("Hello, world. You're at the polls index.")

# myapp/views.py
def index(request):
    # Initialize DriverToDriveData
    driver_data = DriverToDriveData()
    driver_data.get_csv_file_data()
    driver_data.add_list_to_dict_by_index()
    driver_data.add_list_to_dict2_by_()

    # Example: Access data from the DriverData model
    all_driver_data = DriverData.objects.all()

    # Perform any additional operations as needed
    miles = calculate_total_miles(driver_data)
    mins = calculate_total_mins(driver_data)

    # Save or retrieve data from DriverData model
    save_data_to_model(driver_data)

    # Prepare context for rendering the template
    context = {
        'total_miles': miles,
        'driver_data': all_driver_data,
        'total_mins' : mins,
    }

    return render(request, 'myapp/index.html', context)

def calculate_total_miles(driver_data):
    
    total_miles = 0
    for key in driver_data.sorted_keys:
        compile = driver_data.DataStructure[key]["Distance"]
        x = int(compile.split()[0])
        total_miles += x
    miles = f"{total_miles:,}"
    return miles

def calculate_total_mins(driver_data):

    total_mins = 0
    for key in driver_data.sorted_keys:
        compile = driver_data.DataStructure[key]["Elapsed"]
        x = int(compile.split()[0])
        total_mins += x
    mins = f"{total_mins:,}"
    hours = total_mins / 60
    return mins 
    



def save_data_to_model(driver_data):
    # Example: Save data to DriverData model
    for key, value in driver_data.DataStructure.items():
        DriverData.objects.update_or_create(
            key=key,
            defaults={'value': value},
        )

