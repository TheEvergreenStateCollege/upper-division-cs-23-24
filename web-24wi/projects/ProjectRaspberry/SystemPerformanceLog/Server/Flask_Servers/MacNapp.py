from flask import Flask, render_template, request, redirect, url_for, flash, session, jsonify
import os
import csv
import bcrypt  # Hash password to be stored
import re  # re handles regex operations
import sys

# This is to help the server find the RaspberryHealth MenuMode.py
current_dir = os.path.dirname(os.path.abspath(__file__))
menu_mode_path = os.path.join(current_dir, 'templates', 'RaspberryHealth', 'SystemPerformanceLog', 'SystemLog')
sys.path.append(menu_mode_path)

from SystemLogConfig import set_csv_log_path

# This is to help the server find the RaspberryHealth SystemLogConfig.py 
csv_file_path = os.path.join(current_dir, 'templates', 'RaspberryHealth', 'SystemPerformanceLog', 'SystemLog', 'SystemLogResources', 'os_log.csv')
set_csv_log_path(csv_file_path)

from contactFormHandler import contact_form_handler
from formHandler import form_handler
from MenuMode import load_data, csv_log_path

app = Flask(__name__)
app.register_blueprint(form_handler, url_prefix='/form')
app.register_blueprint(contact_form_handler, url_prefix='/')

app.secret_key = 'your_secret_key'


def validate_user_input(first_name, last_name, password):
    name_pattern = re.compile(r'^[A-Za-z]+$')
    password_pattern = re.compile(r'^[A-Za-z\d@$!%*?&]{10,30}$')

    if not re.match(name_pattern, first_name) or not re.match(name_pattern, last_name):
        return "Invalid password. Only alphabetical characters are allowed."

    if not re.match(password_pattern, password):
        return "Invalid password. Must be 10-30 characters long and can include common symbols."

    return "Valid"


@app.route('/')
def home():
    return render_template('index.html')


@app.route('/main-menu.html')
def main_menu():
    return render_template('mainMenu.html')


@app.route('/login')
def login():
    return render_template('login.html')


@app.route('/create-profile', methods=['POST'])
def create_profile():
    first_name = request.form.get('firstName', '')
    last_name = request.form.get('lastName', '')
    email = request.form.get('email', '')
    password = request.form.get('password', '')

    validation_result = validate_user_input(first_name, last_name, password)
    if validation_result != "Valid":
        flash(validation_result, 'error')
        return redirect(url_for('login'))

    resources_dir = os.path.join(os.getcwd(), 'resources')
    csv_file_path = os.path.join(resources_dir, 'users.csv')

    # Ensure the resources directory exists
    if not os.path.exists(resources_dir):
        os.makedirs(resources_dir)

    # Check if CSV exists, if not create
    if not os.path.isfile(csv_file_path):
        with open(csv_file_path, 'w', newline='') as file:
            writer = csv.writer(file)
            writer.writerow(['First Name', 'Last Name', 'Email', 'Password'])

    # Hash the password
    password = request.form['password'].encode('utf8')  # Encodes the password into bytes
    salt = bcrypt.gensalt(rounds=10)  # Generate salt rounds. I think I should experiment with the rounds...
    hashed_password = bcrypt.hashpw(password, salt)  # Hash the password
    # hashed_password = bcrypt.hashpw(password.encode('utf-8'), bcrypt.gensalt()).decode('utf-8')

    # Append the new user data to CSV
    try:
        with open(csv_file_path, 'a', newline='') as file:
            writer = csv.writer(file)
            writer.writerow([first_name, last_name, email, hashed_password])
        flash('Profile created successfully!', 'success')
    except Exception as e:
        print(f"Error writing to CSV: {e}")
        flash('An error occurred. Please try again.', 'error')

    # Redirect to the main menu after profile creation
    return redirect(url_for('main_menu'))


@app.route('/about')
def about():
    return render_template('about.html')


@app.route('/contact')
def contact():
    return render_template('contact.html')


def submit_contact():
    name = request.form.get('name', '')
    email = request.form.get('email', '')
    message = request.form.get('message', '')

    is_valid, error = validate_form_data(name, email, message)

    if not is_valid:
        return jsonify({'success': False, 'message': error}), 400

    try:
        save_to_csv(name, email, message)
        return jsonify({'success': True, 'message': 'Thank you for contacting us!'})
    except Exception as e:
        return jsonify({'success': False, 'message': 'An error occurred while processing your request.'}), 500


@app.route('/submit-contact', methods=['POST'])
def contact_submission():
    return submit_contact()

@app.route('/raspberryhealth')
def raspberry_health():
    return render_template('RaspberryHealth/landing.html')

@app.route('/raspberryhealth/about')
def raspberry_health_about():
    return render_template('RaspberryHealth/about.html')

@app.route('/raspberryhealth/privacy')
def raspberry_health_privacy():
    return render_template('RaspberryHealth/privacy.html')

@app.route('/raspberryhealth/terms')
def raspberry_health_terms():
    return render_template('RaspberryHealth/terms.html')

@app.route('/raspberryhealth/data-menu')
def raspberry_health_data_menu():
    return render_template('RaspberryHealth/RaspDataMenu.html')

@app.route('/raspberryhealth/view-all-data')
def view_all_data():
    data = load_data(csv_log_path)  
    return jsonify(data)    

if __name__ == '__main__':
    app.run(debug=True, port=5000)
