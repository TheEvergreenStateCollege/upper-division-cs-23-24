from flask import Blueprint, request, redirect, url_for, flash, render_template, session
from werkzeug.security import generate_password_hash, check_password_hash
import os
import csv
import bcrypt

# Define the blueprint
form_handler = Blueprint('form_handler', __name__, template_folder='templates')

resources_dir = os.path.join(os.getcwd(), 'resources')
csv_file_path = os.path.join(resources_dir, 'users.csv')

def ensure_resources_dir_exists():
    if not os.path.exists(resources_dir):
        os.makedirs(resources_dir)

def user_exists(email):
    """Check if user already exists"""
    with open(csv_file_path, 'r', newline='') as file:
        reader = csv.DictReader(file)
        for row in reader:
            if row['Email'] == email:
                return True
    return False

@form_handler.route('/create-profile', methods=['POST'])
def createprofile():
    ensure_resources_dir_exists()
    if not os.path.isfile(csv_file_path):
        with open (csv_file_path, 'w', newline='') as file:
            writer = csv.writer(file)
            writer.writerow(['First Name', 'Last Name', 'Email', 'Password'])

    email = request. form['email']
    if user_exists(email):
        flash('Email already exists. Please use a different email.', 'error')
        return redirect(url_for('form_handler.login'))

    password = request.form['password'].encode('utf-8')
    hashed_password = bcrypt.hashpw(password, bcrypt.gensalt()).decode('utf-8')

    with open(csv_file_path, 'a', newline='') as file:
        writer = csv.writer(file)
        writer.writerow([
            request.form['firstName'],
            request.form['lastName'],
            email,
            hashed_password
        ])

    flash('Profile created successfully!', 'success')
    return redirect(url_for('main_menu'))

@form_handler.route('/login', methods=['POST'])
def login_post():
    email = request.form['email']
    password = request.form['password'].encode('utf-8')

    authenticated = False  # Flag to track authentication status
    with open(csv_file_path, 'r', newline='') as file:
        reader = csv.DictReader(file)
        for row in reader:
            if row['Email'] == email and bcrypt.checkpw(password, row['Password'].encode('utf-8')):
                authenticated = True  # User is authenticated
                break  # Exit loop since user is found and authenticated

    if authenticated:
        # Implement user sessions
        session['user_email'] = email  # Store the user's email (or another identifier) in the session
        flash('Login successful!', 'success')
        return redirect(url_for('main_menu'))  # Ensure this is the correct endpoint, i think :|
    else:
        flash('Invalid email or password', 'error')
        return redirect(url_for('form_handler.login'))

@form_handler.route('/login', methods=['GET'])
def login():
    return render_template('login.html')

@form_handler.route('/logout', methods=['POST'])
def logout():
    session.pop('user_email', None)  # Remove the user Identifier from the session
    return redirect(url_for('index.html'))