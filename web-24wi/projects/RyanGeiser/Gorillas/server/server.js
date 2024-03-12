const express = require("express");
const app = express();
const port = 5000;
const path = require("path");
const { PrismaClient } = require('@prisma/client');
const { parsed } = require('dotenv').config();

console.log(parsed['DATABASE_URL']);
console.log(process.env['DATABASE_URL']);
const prisma = new PrismaClient();

const bcrypt = require('bcrypt');



// app.[method]([route], [route handler])
app.get("/", (req, res) => {
	// sending back an HTML file that a browser can render on the screen.
	res.sendFile(path.resolve("pages/index.html"));
});


// Return search hit given :hit  URL route parameters
app.get("/search-hit/:hit", (req, res) => {
	// sending back an HTML file that a browser can render on the screen.
	res.sendFile(path.resolve(`pages/search-hit-${req.params.hit}.html`));
});

// creates and starts a server for our API on a defined port
app.listen(port, () => {
	console.log(`Example app listening at http://localhost:${port}`);
});

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
  


app.get("/users", async (req, res) => {
	const allUsers = await prisma.user.findMany();
	res.json(allUsers);
});

app.get("/randomGraph", async (req, res) => {
	let results = []
	for (let i = 0; i < 10; i++) {
		results.push({ "day": i, "stepCount": Math.round(Math.random() * 1000) });
	}
	res.json({ results });
});


app.get("/login", async (req, res) => {
	res.sendFile(path.resolve("../login.html"));
});

app.get("/register", async (req, res) => {
	res.sendFile(path.resolve("../register.html"));
});

app.get("/game", (req, res) => {
	if (req.session && req.session.loggedIn) {
		// User is logged in, send the game page
		res.sendFile(path.resolve("../game.html"));
	} else {
		// User is not logged in, redirect to login page
		res.redirect("/login");
	}
});

app.use(express.static("static"));
app.use(express.json());