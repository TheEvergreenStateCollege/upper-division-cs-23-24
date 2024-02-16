const express = require("express");
const app = express();
const port = 5000;
app.use(express.static("static"));
app.use(express.json());
const http = require('http');
const fs = require('fs');
const { PrismaClient } = require('@prisma/client');
const { parsed } = require('dotenv').config();

console.log(parsed['DATABASE_URL']);
console.log(process.env['DATABASE_URL']);
const prisma = new PrismaClient();

/* API SERVER - Handles authentication and will possible handle other stuff*/

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

/* Static Website - Serves a static website, which will use client-side JS to interface with the API and the music server*/

app.get("/", function(req, res) {
        res.sendFile("/home/ubuntu/src/upper-division-cs/web-24wi/assignments/Gavin-Bowers/backend/api-server/index.html");
}); /*you could replace this with a relative path using a function*/


/*Music Server - Streams music files*/

const musicServer = http.createServer((req, res) => {
	//handle requests here
});

musicServer.on('request', (req, res) => {
	const filePath = '/home/ubuntu/src/media/test.mp4';
	fs.stat(filePath, (err, stats) => {
		if (err) {
			console.error(err);
			res.writeHead(404, {'Context-Type': 'text/plain'});
			res.end('File not found');
			return;
		}
	const range = req.headers.range;
	const fileSize = stats.size;
	const chunkSize = 1024 * 1024;
	const start = Number(range.replace(/\D/g, ""));
	const end = Math.min(start + chunkSize, fileSize - 1);

	const headers = {
		"Content-Type": "audio/mpeg",
		"Content-Length": end - start,
		"Content-Range": "bytes " + start + "-" + end + "/" + fileSize,
		"Accept-Ranges": "bytes",
	};

	res.writeHead(206, headers);
	const audioStream = fs.createReadStream(filePath, { start, end });
	audioStream.pipe(res);
	});
});
	
	

musicServer.listen(3000, () => {
	console.log("Music server running on port 3000");
});

app.listen(port, () => {
	console.log("Web app listening at port 5000");
});
