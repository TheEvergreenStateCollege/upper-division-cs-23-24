from flask import Flask, render_template, request, send_from_directory

# DriverData specifics
from Average import averager
from DriverData2 import DriverToDriveData
from DriverModes import print_keys, pretty_print, sample_calculation, \
    search_by_key_and_value, search_by_value, total_miles, total_time, \
    calculate_distances

# Required
app = Flask(__name__)

# Load in DSA driver data function for POC
try:
    driver_data = DriverToDriveData()
    driver_data.run_mode()
    driver = driver_data.DataStructure
except Exception as e:
    print(e)

# Add in logic to serve react dashboard
@app.route('/')
def serve_react_app():
    return send_from_directory('../react-prod/build', 'index.html')

@app.route('/static/js/<path:path>')
def serve_static_js(path):
    return send_from_directory('../react-prod/build/static/js', path)

@app.route('/static/css/<path:path>')
def serve_static_css(path):
    return send_from_directory('../react-prod/build/static/css', path)

@app.route('/<path:filename>')
def serve_manifest(filename):
    return send_from_directory('../react-prod/build', filename)


# //  End Handiling React dashboard loading


@app.route('/menu',)
def index():
    return render_template("menu.html")

@app.route('/menu/<int:selection>', methods=['POST'])
def handle_menu_selection(selection):
    result = process_selection(selection)
    return render_template('result.html', result=result)

def process_selection(selection):
    Modes = {
        1: print_keys,
        2: pretty_print,
        3: sample_calculation,
        4: search_by_key_and_value,
        5: search_by_value,
        6: total_miles,
        7: total_time,
        8: calculate_distances,
        9: averager,
        10: lambda: "Exiting..."
    }

    try:
        mode_function = Modes[selection]
        return mode_function(driver_data, driver) if selection != 10 else mode_function()
    except KeyError:
        return "Invalid selection"

if __name__ == '__main__':
    app.run(debug=True, use_reloader=True)
