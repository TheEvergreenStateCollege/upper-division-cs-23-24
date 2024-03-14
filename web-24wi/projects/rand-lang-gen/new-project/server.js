const express = require("express");
const app = express();
const port = 5000;
const path = require("path");
const router = require('./src/router');

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

app.use('/api', protect, router);

app.get("/users", async (req, res) => {
	const allUsers = await prisma.user.findMany();
	res.json(allUsers);
});

app.post("/user", async (req, res) => {
	const newUser = await prisma.user.create({
		data: {
			username: req.body.username,
			password: req.body.password,
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
			name: req.body.name,
			define: req.body.define,
			language: req.body.language,
			authorId: Number(req.body.authorId)
		}
	});
	res.json(result);
});

app.on('error', (error) => {
    if (error.code === 'EADDRINUSE') {
        console.error('Error: Address already in use:', error.message);
        // Implement your recovery strategy here (e.g., retry with a different port)
    } else {
        console.error(error); // Handle other errors
    }
});

var myErrorHandler = function(err, req, res, next){
    // note, using the typical middleware pattern, we'd call next() here, but 
    // since this handler is a "provider", i.e. it terminates the request, we 
    // do not.
  console.error(`Error ${error}`)
};

app.use(myErrorHandler);

const http = require('http');

const server = http.createServer(app);
const serverFunc = () => {
	server.listen(port, () => {
		console.log(`Server listening on port ${port}`);
	});
};

server.on('error', (error) => {
	if (error.code === 'EADDRINUSE') {
		console.error(`Port ${port} is already in use. Trying the next port number.`);
		port += 1;
		setTimeout(serverFunc, 1000);
	} else {
		console.error('Error starting server:', error);
	}
});

serverFunc();
