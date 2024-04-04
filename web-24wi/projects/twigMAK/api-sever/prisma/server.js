const express = require("express");
const app = express();
const port = 5000;
const path = require("path");

const{PrismaClient} = require('@prisma/client');
const{parsed} = require('dotenv').config();

console.log(parsed['DATABASE_URL']);
console.log(process.env['DATABASE_URL']);
const prisma = new PrismaClient();

//app.use(express.static("static"));
app.use(express.json);
/**
 *  * app.[method]([route], [route handler])
 *   */
app.get("/", (req, res) => {
	  // sending back an HTML file that a browser can render on the screen.
	res.sendFile(path.resolve("../../index.html"));
});
	
	// creates and starts a server for our API on a defined port
app.listen(port, () => {
	//console.log('Example app listening at http://localhost:${port}');
	console.log('Example app listening at http://localhost:',port);
})

app.get("/users", async (req, res) =>{
	const allUsers = await prisma.user.findMany();
	res.json(allUsers);
});

app.post("/users", async (req, res) =>{
	const newUser = await prisma.user.create({
		data:{
			username: req.body.username,
			password: req.body.password,
		},
	});
	
	res.json(req.body);
});

app.get("/cities", async (req, res) => {
    const allCities = await prisma.uSCity.findMany();
    res.json(allCities);
});

app.post("/city", async (req, res) => {
    const result = await prisma.uSCity.create({
        data: {
            name: req.body.cityName,
            longitude: Number(req.body.longitude),
            latitude: Number(req.body.latitude),
            authorId: Number(req.body.authorId),
        }
    });
    res.json(result);
});

