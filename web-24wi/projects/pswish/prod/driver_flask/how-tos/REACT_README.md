# React option #2
You can use React with Flask to create an interactive frontend for your web application. React is a powerful JavaScript library for building user interfaces, and it can be integrated with Flask by serving the React app as static files and communicating with the Flask backend through API calls.

Here's a simplified example of how you might structure a Flask app with a React frontend:

1. **Setup a React App:**

   First, set up a new React app using Create React App or any other method you prefer.

   ```bash
   npx create-react-app my-react-app
   cd my-react-app
   ```

2. **Create a React Component:**

   Create a React component that handles the user interface and communicates with the Flask backend.

   ```jsx
   // src/App.js

   import React, { useState } from 'react';

   function App() {
     const [inputValue, setInputValue] = useState('');
     const [result, setResult] = useState('');

     const handleSubmit = async () => {
       try {
         const response = await fetch('/get_result', {
           method: 'POST',
           headers: {
             'Content-Type': 'application/json',
           },
           body: JSON.stringify({ value: inputValue }),
         });

         const data = await response.json();
         setResult(`Result: ${data.result}`);
       } catch (error) {
         console.error('Error:', error);
       }
     };

     return (
       <div>
         <label htmlFor="inputValue">Enter a value:</label>
         <input
           type="text"
           id="inputValue"
           name="inputValue"
           value={inputValue}
           onChange={(e) => setInputValue(e.target.value)}
           required
         />
         <button onClick={handleSubmit}>Submit</button>

         <div>
           <p>{result}</p>
         </div>
       </div>
     );
   }

   export default App;
   ```

3. **Build the React App:**

   Build the React app to generate static files.

   ```bash
   npm run build
   ```

   This will create a `build` folder with the compiled static files.

4. **Serve React App with Flask:**

   Update your Flask app to serve the React static files and create an endpoint for the API calls.

   ```python
   # app.py

   from flask import Flask, render_template, request, jsonify
   from flask_cors import CORS  # Enable CORS for all routes, I may not need it

   app = Flask(__name__)
   CORS(app)  # Enable CORS for all routes, I may not need it

   @app.route('/')
   def index():
       return render_template('index.html')

   @app.route('/get_result', methods=['POST'])
   def get_result():
       data = request.get_json()
       value = data.get('value')

       # Perform some computation or use a Python function, see my current app.js for implementations
       result = perform_computation(value)

       return jsonify({'result': result})

   def perform_computation(value):
       # Replace this with your actual computation logic
       return f"You entered: {value}"

   if __name__ == '__main__':
       app.run(debug=True)
   ```

5. **Update the HTML Template:**

   Update the HTML template to include the React app.

   ```html
   <!-- templates/index.html -->

   <!DOCTYPE html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <title>React App with Flask</title>
   </head>
   <body>
    <!-- The root id is the call to the react app -->
       <div id="root"></div>
   </body>
   </html>
   ```

   Make sure to include the React static files (from the `build` folder) when deploying the Flask app.

6. **Run the Flask App:**

   Run your Flask app.

   ```bash
   gunicorn -w 4 -b 0.0.0.0:8080 app:app
   ```

   Visit `http://127.0.0.1:5000/` in your browser to see the React app integrated with Flask.

This example shows a basic setup, and you can expand on it to build more complex applications. Ensure that you have Node.js, npm, and Flask installed before following these steps.
