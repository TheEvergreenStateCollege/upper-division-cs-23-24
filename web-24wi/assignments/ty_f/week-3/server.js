const express = require("express");
const app = express();
const port = 5000;
const path = require("path");

app.use(express.static("static"));

/**
 * app.[method]([route], [route handler])
 */
app.get("/", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve("pages/index.html"));
});

// creates and starts a server for our API on a defined port
app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});
const {PrismaClient} = require('@prisma/client');
const {parsed} = require('dotenv').config();

console.log(parsed['DATABASE_URL']);
const prisma = PrismaClient();
app.get("/user",async (req, res) => {
	const allusers = await prisma.user.findMany();
	res.json(allUsers);
});

app.post ("/user", async (req, res) => {
	const newUser = await prisma.user.creat({
		data:{
			username: reqbody.username,
			password: '',
		},
	});
});
	
