from flask import Blueprint, request, jsonify
import re
import csv
import os

contact_form_handler = Blueprint('contact_form_handler', __name__)

@contact_form_handler.route('/submit-contact', methods=['POST'])
def submit_contact():
    name = request.form.get('name', '')
    email = request.form.get('email', '')
    message = request.form.get('message', '')
    
    is_valid, error = validate_form_data(name, email, message)
    
    if not is_valid:
        # Use AJAX to submit the form, return a JSON response.
        return jsonify({'success': False, 'message': error}), 400
    
    try:
        save_to_csv(name, email, message)
        # I couldt implement sending an email to myself here.
        return jsonify({'success': True, 'message': 'Thank you for contacting us!'})
    except Exception as e:
        return jsonify({'success': False, 'message': 'An error occurred while processing your request.'}), 500


def validate_form_data(name, email, message):
    # Name validation
    if not name or not re.match(r'^[A-Za-z\s]+$', name):
        return False, "Invalid name. Only alphabetical characters and spaces are allowed."

    # Email validation
    if not email or not re.match(r'^\S+@\S+\.\S+$', email):
        return False, "Invalid email format."

    # Message validation (example: check if message is not empty)
    if not message:
        return False, "Message cannot be empty."

    # Message length validation
    if len(message) > 200:
        return False, "Message connot exceed 200 characters."

    return True, ""

def save_to_csv(name, email, message):
    resources_dir = os.path.join(os.getcwd(), 'resources')
    csv_file_path = os.path.join(resources_dir, 'contact_messages.csv')
    
    if not os.path.exists(resources_dir):
        os.makedirs(resources_dir)
    
    with open(csv_file_path, 'a', newline='') as file:
        writer = csv.writer(file)
        writer.writerow([name, email, message])
