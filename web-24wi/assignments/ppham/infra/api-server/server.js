const express = require("express");
const app = express();
const port = 5000;
const path = require("path");
const { PrismaClient } = require('@prisma/client');
const { parsed } = require('dotenv').config();

console.log(parsed['DATABASE_URL']);
console.log(process.env['DATABASE_URL']);
const prisma = new PrismaClient();

app.use(express.json());
app.use(express.static("static"));

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
});

// Return search hit given :hit  URL route parameters
app.get("/search-hit/:hit", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve(`pages/search-hit-${req.params.hit}.html`));
});

// http://sub.arcology.builders:5000 

// creates and starts a server for our API on a defined port
app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});

app.post("/login", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  console.log(JSON.stringify(req.body));
  res.json(req.body);
});


// Create a bare HTTP server so we can register custom error handlers
// for things like duplicate listening ports
const http = require('http');
const server = http.createServer(app); // Replace with your Express app creation
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
  // Optional: Perform additional actions like exiting the application
});
// Kick off the chain of server listen retries with the original port
serverFunc();
