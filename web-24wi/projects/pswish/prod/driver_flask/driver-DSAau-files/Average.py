# This is a search function for DriverData and depends on DriverMain
# Big O for this script is O(n)

# Step 1, define the recursive search logic
# Step 2, Provide a list of cities to check for (hardcoded to skip Evergreen campus as a source/dest)
# Step 3, perform calculations and output results

def search_nested_dict(dictionary, target_value):
    try:
        result_keys = []
        # results_values  = [] Not yet in 1.3
        # Iterate over key-value pairs in the dictionary
        for key, value in dictionary.items():
            # Check if the current value is equal to the target value
            if value == target_value:
                result_keys.append(key)
                # results_values.append(value)  # not implement

            # If the current value is a nested dictionary, recursively search it
            if isinstance(value, dict):
                nested_result = search_nested_dict(value, target_value)
                if nested_result:
                    result_keys.append((key, nested_result))

        return result_keys if result_keys else None 
    except Exception as e:
        print(e)

def averager(driver): # searching is O(n)
    try:
        print("\n**--- Printing out Averages ---**\n")
        # set the target values to search for, version 1.3, 4 cities measured.
        target_value1 = ["Millersylvania", "Gardiner", "Lakewood", "Federal Way"]

        for city in target_value1:
            result2 = search_nested_dict(driver, city) # this is the search function that performs the heavy lifting.
            search_results = sorted(result2)

            # Format the results into another list
            search_results_formatted = []
            [search_results_formatted.append(str(item).split("'")[1]) for item in search_results]

            # Set up variables for next steps
            select =  search_results_formatted
            total_miles = 0
            entries = len(select)
            
            # ----- Calculations ------ # 
            for key in select:  # Takes the selection data from above and calculates a hardcoded distance.
                compile = driver[key]["Distance"]
                x = int(compile.split()[0])
                total_miles += x
            print(f"\033[3m{city}\033[0m : \033[1mTotal combined miles:\033[0m ", total_miles)
            print(" Total trips from or to the destination:", entries)
            
            # Takes data from above and calculates a hardcoded cost per mile
            dollars_per_mile = .20
            total_cost = int(float(total_miles) * dollars_per_mile)
            formatted_num = f'{total_cost:.2f}'
            print(" Total cost (miles * cost per mile($0.20)): $", formatted_num)

            total_time = 0
            # Takes the data from above and calculates a hardcoded totals and prints it all out
            # searches the data structure 'driver' for time metric and combines them.
            for key in select:
                compile = driver[key][str("Elapsed")]
                x = int(compile.split()[0])
                total_time += x
            print(" Total minutes for this destination: ", total_time)
            print(" Average miles for this City: ", total_miles // entries)
            print(" Average time for each City: ", total_time // entries)
            
            quotient, remainder = divmod(total_cost, entries)
            average_cost = (f"{quotient}.{remainder:02d}")
            print(f" Average cost per trip ~ ${average_cost}")
            weekly_cost = (float(average_cost) * 5.00)
            print (f" Average per week (if cerberus paribus 5 days a week) ${weekly_cost:.2f}")
            print("**   --   **")
            print()

    except Exception as e:
        print(e)
