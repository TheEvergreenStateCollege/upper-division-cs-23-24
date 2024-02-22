const express = require("express");
const app = express();
const port = 5000;
const path = require("path");

app.use(express.static("static"));
app.use(express.json());
/* app.[method]([route], [route handler]  */

app.get("/", (req, res) => {
	//sends HTML file for web page
	res.sendFile(path.resolve("pages/index.html"));

});

//makes server
app.listen(port, () => {
	console.log(`Example app listening at who knows:${port}`);
});

//Return search hit
app.get("/search-hit/:hit", (req, res) => {
	res.sendFile(path.resolver(`pages/search-hits${req.params.hit}.html`)); //this line
});

//new things from paul 2/5/2024
const { PrismaClient } = require('@prisma/client');
const { parsed } = require('dotenv').config();

console.log(parsed['DATABASE_URL']);
console.log(process.env['DATABASE_URL']);
const prisma = new PrismaClient();

//new thing from paul part 2 2/5/2024
app.get("/users", async (req, res) => {
	const allUsers = await prisma.user.findMany();
	res.json(allUsers);
});

app.post("/user", async (req, res) => {
	const newUser = await prisma.user.create({
		data: {
			username: req.body.username,
			password: '',
		}
	});
	console.log("created");
	res.json(req.body);

});
