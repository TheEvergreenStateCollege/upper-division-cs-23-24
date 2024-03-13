const express = require("express");
const app = express();
const port = 5000;
const path = require("path"); // Import path module here
const { PrismaClient } = require('@prisma/client');
const { parsed } = require('dotenv').config();
const session = require("express-session");

console.log(parsed['DATABASE_URL']);
console.log(process.env['DATABASE_URL']);
const prisma = new PrismaClient();

const bcrypt = require('bcrypt');

// Session middleware configuration
app.use(session({
	secret: "your-secret-key",
	resave: false,
	saveUninitialized: false
}));

app.use(express.static(path.join(__dirname, "public")));
app.use(express.json());

app.post("/login", async (req, res) => {
	const { username, password } = req.body;

	try {
		const user = await prisma.user.findUnique({
			where: { username },
		});

		if (!user) {
			return res.status(401).send("Invalid username or password");
		}

		// Validate password (assuming hashed passwords)
		const passwordMatch = await bcrypt.compare(password, user.password); // Using bcrypt library
		if (!passwordMatch) {
			return res.status(401).send("Invalid username or password");
		}

		// Login successful
		req.session.loggedIn = true;
		req.session.username = username;
		res.redirect("/game");
	} catch (error) {
		console.error(error);
		res.status(500).send("Internal server error");
	}
});


app.post("/user", async (req, res) => {
	const { username, password } = req.body;

	// Implement password hashing using bcrypt before storing
	const hashedPassword = await bcrypt.hash(password, 10); // Example with cost factor of 10

	try {
		const newUser = await prisma.user.create({
			data: {
				username,
				password: hashedPassword, // Use the hashed password
			},
		});
		console.log("User created:", newUser);
		res.status(201).send("User created successfully!"); // Success message
	} catch (error) {
		console.error("Error creating user:", error);
		res.status(500).send("Error creating user"); // Error message
	}
});

app.get("/", (req, res) => {
	res.sendFile(path.join(__dirname, "public", "index.html"));
});

app.get("/search-hit/:hit", (req, res) => {
	res.sendFile(path.join(__dirname, "public", `search-hit-${req.params.hit}.html`));
});

app.listen(port, () => {
	console.log(`Example app listening at http://localhost:${port}`);
});

// Removed redundant declaration of 'path' variable

const publicPath = path.join(__dirname, "..", "public");

app.get("/login", async (req, res) => {
    res.sendFile(path.join(publicPath, "login.html"));
});

app.get("/register", async (req, res) => {
    res.sendFile(path.join(publicPath, "register.html"));
});

app.get("/game", (req, res) => {
    if (req.session && req.session.loggedIn) {
        res.sendFile(path.join(publicPath, "game.html"));
    } else {
        res.redirect("/login");
    }
});
