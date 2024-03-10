from flask import Flask, render_template, request, redirect, url_for, flash, session
import os
import csv
import bcrypt  # Hash password to be stored
import re  # re handles regex opertions

from formHandler import form_handler

app = Flask(__name__)
app.register_blueprint(form_handler, url_prefix='/form')

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
def  main_menu():
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
    #hashed_password = bcrypt.hashpw(password.encode('utf-8'), bcrypt.gensalt()).decode('utf-8')

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

if __name__ == '__main__':
    app.run(debug=True, port=5000)