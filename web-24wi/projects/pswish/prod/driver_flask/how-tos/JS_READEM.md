# Design Option 1
To create an interactive website in Flask where users can click a button and get a result from a Python function on the server, you can use JavaScript along with Flask. Here's a basic example:

1. **HTML Template:**
   Create an HTML template with a button, an input field, and a placeholder for displaying the result.

```html
<!-- templates/interactive.html -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Interactive Website</title>

    <!-- Link to your static CSS file -->
    <link rel="stylesheet" href="{{ url_for('static', filename='style.css') }}">
</head>
<body>
    <div>
        <label for="inputValue">Enter a value:</label>
        <input type="text" id="inputValue" name="inputValue" required>
        <button id="submitButton">Submit</button>
    </div>

    <div id="resultContainer">
        <!-- The result will be displayed here -->
    </div>

    <!-- Link to your static JavaScript file -->
    <script src="{{ url_for('static', filename='script.js') }}"></script>
</body>
</html>
```

2. **CSS Stylesheet:**
   Create a CSS stylesheet to style the input and button.

```css
/* static/style.css */

input {
    padding: 5px;
    margin-right: 10px;
}

button {
    padding: 5px;
    cursor: pointer;
}
```

3. **JavaScript File:**
   Create a JavaScript file to handle the interaction with the Flask server.

```javascript
// static/script.js

document.addEventListener('DOMContentLoaded', function() {
    const inputValue = document.getElementById('inputValue');
    const submitButton = document.getElementById('submitButton');
    const resultContainer = document.getElementById('resultContainer');

    submitButton.addEventListener('click', function() {
        const value = inputValue.value;

        // Make a POST request to the Flask server
        fetch('/get_result', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ value: value })
        })
        .then(response => response.json())
        .then(data => {
            // Update the result container with the received data
            resultContainer.innerHTML = `<p>Result: ${data.result}</p>`;
        })
        .catch(error => console.error('Error:', error));
    });
});
```

4. **Flask App:**
   Update your Flask application to handle the button click on the server side.

```python
# app.py

from flask import Flask, render_template, request, jsonify

app = Flask(__name__)

@app.route('/')
def home():
    return render_template('interactive.html')

@app.route('/get_result', methods=['POST'])
def get_result():
    data = request.get_json()
    value = data.get('value')

    # Perform some computation or use a Python function
    result = perform_computation(value)

    return jsonify({'result': result})

def perform_computation(value):
    # Replace this with your actual computation logic
    return f"You entered: {value}"

if __name__ == '__main__':
    app.run(debug=True)
```

Now, when a user enters a value, clicks the "Submit" button, the JavaScript code sends a POST request to the `/get_result` route on the Flask server. The server processes the request, performs some computation, and sends the result back to the client, which updates the result container on the webpage.

Make sure to run your Flask application (`python app.py`) and access the webpage (`http://127.0.0.1:5000/`) to see the interactive features in action.