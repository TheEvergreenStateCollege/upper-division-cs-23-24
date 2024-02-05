
const express = require("express");
const app = express();
const port = 5000;
const path = require("path");

app.use(express.static("static"));
app.use(express.json());

const { PrismaClient } = require('@prisma/client');
const { parsed } = require('dotenv').config();

console.log(parsed['DATABASE_URL']);
console.log(process.env['DATABASE_URL']);
const prisma = new PrismaClient();

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
	console.log("created new user");
});

app.listen(port, () => {
	console.log("example app listening at port ?");
});
