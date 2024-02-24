const express = require("express");
const helmet = require("helmet");
const path = require("path");
const fs = require("fs");
const fsPromises = require('fs').promises;
const app = express();
const port = 5000;
const bcrypt = require('bcryptjs');
const saltRounds = 10; // For bcrypt password hashing


// Apply Helmet middleware for security
app.use(helmet());

// Specific Helmet configurations
app.use(helmet.contentSecurityPolicy({
    directives: {
        defaultSrc: ["'self'"],
        scriptSrc: ["'self'"],
        objectSrc: ["'none'"],
        upgradeInsecureRequests: [],
    }
}));

app.use(helmet.noSniff());
app.use(helmet.xssFilter());

// Path to the pages directory
const pagesPath = path.join(__dirname, 'pages');
const resourcesPath = path.join(__dirname, 'Resources', 'profileCreation.csv');

// Middleware to parse incoming requests with JSON payloads
app.use(express.json());

// Middleware to parse urlencoded bodies (for form submissions)
app.use(express.urlencoded({ extended: true }));

// Server static files from the 'pages' directory
app.use(express.static(pagesPath));

// Sanitize input function
function sanitizedInput(input) {
    const pattern = /<.*?>|script/gi; // Regular expression to match unwanted characters or sequences
    return input.replace(pattern, ''); // Replace unwanted sequences with an empty string
}

// Middleware to sanitize all incoming query and body parameters
app.use((req, res, next) => {
    if (req.query) {
        Object.keys(req.query).forEach(key => {
            req.query[key] = sanitizedInput(req.query[key]);
        });
    }

    if (req.body) {
        Object.keys(req.body).forEach(key => {
            req.body[key] = sanitizedInput(req.body[key]);
        });
    }

    next();
});

app.use((req, res, next) => {
    // Check only for routes that need validation
    if (['/submit', '/login'] .includes(req.path)) {
        const contentType = req.headers['content-type'];
        if (!contentType || !contentType.includes('application/x-www-form-urlencoded')) {
            return res.status(415).send('Unsupported Media Type');
        }
    }
    next();
});

// Tell the server that this directory can be used to server static images
app.use('/Resources', express.static('Resources'));

app.get("/", (req, res) => {
    res.sendFile(path.join(pagesPath, 'index.html'));
});

// Modified /submit route to log user data
app.post("/submit", async (req, res) => {
    const { firstName, lastName, email, password } = req.body; // Destructure the user data
    const timestamp = new Date().toISOString(); // Get the current time in ISO format
    
    try {
        // Check if the email already exists
        const emailExists = await checkEmailExists(email);
        if (emailExists) {
            return res.status(400).send("Email already exists. Please use a different email.");
        }

        // Hash the password
        const hashedPassword = await bcrypt.hash(password, saltRounds);

        // Ensure the Resources directory exists
        const resourcesDir = path.dirname(resourcesPath);
        await fsPromises.mkdir(resourcesDir, { recursive: true });

        // Format the data as a CSV line including the hashed password
        const csvLine = `${firstName},${lastName},${email},${hashedPassword},${timestamp}\n`;

        // Append the new user data to the profileCreation.csv file
        await fsPromises.appendFile(resourcesPath, csvLine);

        console.log("User data logged:", csvLine);
        res.json({ success: true, redirect: "/mainMenu.html" });
    } catch (error) {
        console.error("Error in /submit route:", error);
        res.status(500).send("Server error processing your request");
    }
});

    

async  function checkEmailExists(email) {
    return new Promise((resolve, reject) => {
        fs.readFile(resourcesPath, 'utf8', (err, data) => {
            if (err) {
                reject(err);
                return;
            }

            const lines = data.split('\n');
            for (let line of lines) {
                const [,, userEmail] = line.split(',');
                if (userEmail === email) {
                    resolve(true);
                    return;
                }
            }
            resolve(false);
        });
    });
}

// Login route
app.post("/login", async (req, res) => {
    const { email, password } = req.body;

    // Read the CSV file to find the user by email
    fs.readFile(resourcesPath, 'utf8', async (err, data) => {
        if (err) {
            console.error("Error reading the CSV file:", err);
            return res.status(500).send("Server error");
        }

        const users = data.split('\n').map(line => line.split(','));
        const user = users.find(([,, userEmail]) => userEmail === email);

        if (!user) {
            return res.status(401).send("User not found.");
        }

        // User is found, now compare the password
        // Assuming I store hashed passwords in another file or method to retrieve it
        // For this example, we're just checking if a user exists, and I'm not sure...lawd
        const hashedPassword = user[3]; // Placeholder for where you'd get the actual hashed password

        const passwordMatch = await bcrypt.compare(password, hashedPassword);
        if (passwordMatch) {
            // Password matches, log the user in
            res.json({ success: true, redirect: "/mainMenu.html" });
        } else {
            // Password does not match
            res.status(401).send("Invalid credentials.");
        }
    });
});

// Create and Start the server for the API on the defined port
app.listen(port, () => {
    console.log(`MacN app is listening at http://localhost:${port}`);
});
