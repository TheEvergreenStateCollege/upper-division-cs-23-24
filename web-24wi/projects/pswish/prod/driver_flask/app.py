from flask import Flask, render_template, request, send_from_directory, jsonify
from flask_sqlalchemy import SQLAlchemy
import psycopg2
from flask_cors import CORS
import _config
from database import User

# Required
app = Flask(__name__)
CORS(app)

CORS(app, origins=['localhost:3000']) # not sure if this is necessary

# PostgreSQL connection configuration
DB_HOST = _config.DB_HOST
DB_PORT = _config.DB_PORT
DB_NAME = _config.DB_NAME 
DB_USER = _config.DB_USER
DB_PASSWORD = _config.DB_PASSWORD

# Add in logic to serve React build files

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

# Connect to PostgreSQL database / original method
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
    
app.config['SQLALCHEMY_DATABASE_URI'] = f'postgresql://{DB_USER}:{DB_PASSWORD}@{DB_HOST}:{DB_PORT}/{DB_NAME}'
db = SQLAlchemy(app)

@app.route('/submit-form', methods=['POST'])
def submit_form():
    data = request.json  # Get JSON data from the request
    new_user = User(
        first_name=data['firstName'],
        last_name=data['lastName'],
        email=data['email'],
        contact=data['contact'],
        address1=data['address1'],
        address2=data['address2']
    )
    db.session.add(new_user)
    db.session.commit()
    return jsonify({'message': 'User created successfully'})

# Form route for React form (for testing).
# @app.route('/submit-form', methods=['POST'])
# def submit_form():
#     data = request.json  # Get JSON data from the request
#     print(data)  # For dev purposes
#     return jsonify({'message': 'Form submitted successfully'})


if __name__ == '__main__':
    app.run(debug=True, use_reloader=True)
