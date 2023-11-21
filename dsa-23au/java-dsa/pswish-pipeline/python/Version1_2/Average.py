def search_nested_dict(dictionary, target_value):
    try:
        result_keys = []
        results_values  = []
        # Iterate over key-value pairs in the dictionary
        for key, value in dictionary.items():
            # Check if the current value is equal to the target value
            if value == target_value:
                result_keys.append(key)
                results_values.append(value)

            # If the current value is a nested dictionary, recursively search it
            if isinstance(value, dict):
                nested_result = search_nested_dict(value, target_value)
                if nested_result:
                    result_keys.append((key, nested_result))

        return result_keys if result_keys else None 
    except Exception as e:
        print(e)

def averager(driver):
    try:
        print("\n**--- Printing out Averages ---**\n")
        target_value1 = ["Millersylvania", "Gardiner", "Lakewood", "Federal Way"]

        for city in target_value1:
            result2 = search_nested_dict(driver, city) ## this is the search function
            search_results = sorted(result2)
            search_results_formatted = []

            for item in search_results:
                search_results_formatted.append(str(item).split("'")[1])

            select =  search_results_formatted
            total_miles = 0
            entries = len(select)
            for key in select:  # Takes the selection data from above and calculates a hardcoded distance
                compile = driver[key]["Distance"]
                x = int(compile.split()[0])
                total_miles += x
            print(city, ": Total combined miles: ", total_miles)
            print(" Total trips from or to the destination:", entries)
            
            
            dollars_per_mile = .20
            total_cost = int(float(total_miles) * dollars_per_mile)
            formatted_num = f'{total_cost:.2f}'
            print(" Total cost (miles * cost per mile($0.20)): $", formatted_num)

            total_time = 0
            for key in select:
                compile = driver[key][str("Elapsed")]
                x = int(compile.split()[0])
                total_time += x
            print(" Total minutes for this destination: ", total_time)
            print("**   --   **")
            print(" Average miles for this City: ", total_miles // entries)
            print(" Average time for each City: ", total_time // entries)
            
            quotient, remainder = divmod(total_cost, entries)
            average_cost = (f"{quotient}.{remainder:02d}")
            print(f" Average cost per trip ~ ${average_cost}")
            # week = entries // 5
            weekly_cost = (float(average_cost) * 5.00)
            print (f" Average per week (if searbus perabus 5 days a week) ${weekly_cost:.2f}")
            print()

    except Exception as e:
        print(e)
