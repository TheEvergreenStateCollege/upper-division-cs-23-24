from flask import Flask, render_template, request, send_from_directory, jsonify
import psycopg2
from flask_cors import CORS

# DriverData specifics
# from Average import averager
# from DriverData2 import DriverToDriveData
# from DriverModes import print_keys, pretty_print, sample_calculation, \
#     search_by_key_and_value, search_by_value, total_miles, total_time, \
#     calculate_distances

# Required
app = Flask(__name__)
CORS(app)

CORS(app, origins=['localhost:3000'])

# Add in logic to serve react build files

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


# PostgreSQL connection configuration (suspect)
DB_HOST = '0.0.0.0' 
DB_PORT = '5432'
DB_NAME = 'mydb'
DB_USER = 'postgres'
DB_PASSWORD = 'passphrase'

# Connect to PostgreSQL database
def connect_to_db():
    try:
        connect = psycopg2.connect(
            host=DB_HOST,
            database=DB_NAME,
            port=DB_PORT,
            user=DB_USER,
            password=DB_PASSWORD
        )
        return connect
    except psycopg2.Error as e:
        print("Error occured connecting to PostgreSQL database:", e)
    
# Fetch trip data from database
def fetch_trip_data():
    conn = connect_to_db()
    if conn:
        cur = conn.cursor()
        cur.execute("SELECT * FROM \"Trip\";")
        trip_data = cur.fetchall()
        cur.close()
        conn.close()
        return trip_data

@app.route('/trip')
def get_trips():
    trip_data = fetch_trip_data()
    if trip_data:
        return jsonify(trip_data)
    else:
        return jsonify({"error": "Failed to fetch trip data!"}), 500

if __name__ == '__main__':
    app.run(debug=True, use_reloader=True)
