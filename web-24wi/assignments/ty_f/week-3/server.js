const express = require("express");
const app = express();
const port = 5000;
const path = require("path");
const { PrismaClient } = require('@prisma/client');
const { parsed } = require('dotenv').config();

app.use(express.static("static"));
app.use(express.json());

/* app.[method]([route], [route handler]) */
app.get("/", (req, res) => {
	// sending back an HTML file that a browser can render on the screen.
	res.sendFile(path.resolve("pages/index.html"));
});
	
// creates and starts a server for our API on a defined port
app.listen(port, () => {console.log(`Example app listening at http://localhost:${port}`);});
// Return search hit given :hit  URL route parameters
app.get("/search-hit/:hit", (req, res) => {
// sending back an HTML file that a browser can render on the screen.
	res.sendFile(path.resolve(`pages/search-hit-${req.params.hit}.html`));
});
	
app.post("/login", (req, res) => {
	// sending back an HTML file that a browser can render on the screen.
	console.log(`${req.body}`);
	const bodyJSON = JSON.parse(req.body);
	res.json(bodyJSON);
});
	
console.log(parsed['DATABASE_URL']);
console.log(process.env['DATABASE_URL']);
const prisma = new PrismaClient();
	
app.get("/users", async (req, res) => {
	const allUsers = await prisma.user.findMany();
	res.json(allUsers);
});
	
app.post("/user", async (req, res) => {
	try {
	    const newUser = await prisma.user.create({
	    data: {
	        username: req.body.username,
	        password: '',
	    },
		});
		console.log("User created:", newUser);
		res.status(201).json({ message: "User created successfully", user: newUser });
	} catch (error) {
	    console.error("Error creating user:", error);
	    res.status(500).json({ message: "Failed to create user" });
	}
});
	                                                                                                               
