import os

def run_script(script_number, scripts_path):
    scripts = [
        "LongestPlayed.py",
        "ShortestPlayed.py",
        "MostPlayed.py",
        "SearchCountry.py"
    ]

    if 1 <= script_number <= len(scripts):
        script_name = scripts[script_number - 1]
        script_path = os.path.join(scripts_path, script_name)

        # Check if the script file exists
        if os.path.exists(script_path) and os.path.isfile(script_path):
            # Get the current working directory
            original_directory = os.getcwd()

            try:
                os.chdir(scripts_path)  # Change the current working directory
                os.system(f"python3 {script_name}")
            finally:
                os.chdir(original_directory)  # Change back to the original directory
        else:
            print(f"Script file not found: {script_path}")
    else:
        print("Invalid choice. Please enter a number between 1 and 4.")

if __name__ == "__main__":
    # Set the path to the directory containing your Python scripts
    scripts_path = "finalVersion-danteData"

    while True:
        print("\nSelect a script to run:")
        print("1. Show Longest Played Track")
        print("2. Show Shortest Played Track")
        print("3. Show Most Played Track")
        print("4. Search Tracks Per Country")
        print("0. Exit\n")

        choice = input("Enter the number of the script to run (or 0 to exit): ")

        try:
            script_number = int(choice)

            if script_number == 0:
                print("Exiting...")
                break  # Exit the loop

            run_script(script_number, scripts_path)
        except ValueError:
            print("Invalid input. Please enter a number.")
