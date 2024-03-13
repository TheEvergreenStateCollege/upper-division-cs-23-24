const express = require("express");
const app = express();
const port = 5000;
const path = require("path");

const { PrismaClient } = require('@prisma/client');
const { parsed } = require('dotenv').config();

console.log(parsed['DATABASE_URL']);
console.log(process.env['DATABASE_URL']);
const prisma = new PrismaClient();

app.use(express.static("static"));
app.use(express.json());

/**
 * app.[method]([route], [route handler])
 */
app.get("/", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve("pages/index.html"));
});

app.get("/users", async (req, res) => {
	const allUsers = await prisma.user.findMany();
	res.json(allUsers);
});

app.post("/user", async (req, res) => {
	const newUser = await prisma.user.create({
		data: {
			username: req.body.username,
			password: '',
		},
	});
	console.log("created");
	res.json(req.body);
});

app.get("/words", async (req, res) => {
	const allWords = await prisma.word.findMany();
	res.json(allWords);
});

app.post("/word", async (req, res) => {
	const result = await prisma.word.create({
		data: {
			// name: word name
			// definition: String(req.body.definition)
		}
	});
	res.json(result);
});

// creates and starts a server for our API on a defined port
app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});
