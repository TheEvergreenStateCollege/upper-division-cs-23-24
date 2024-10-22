const express = require("express");
const app = express();
const DEFAULT_PORT = process.env.API_PORT || 5000;

// Start with default port, some servers may already be listening on port 5000
// On Mac OS on Sonoma and afterwards, Mac Control Center listens on this
let port = DEFAULT_PORT;
const path = require("path");
const { parsed } = require("dotenv").config();
const { PrismaClient } = require('@prisma/client');

const prisma = new PrismaClient();
app.use(express.static("pages"));
app.use(express.json());

/**
 * app.[method]([route], [route handler])
 */
app.get("/", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve("pages/index.html"));
});

console.log(Object.keys(prisma));

app.get("/users", async (req, res) => {
    const allUsers = await prisma.user.findMany();
    res.json(allUsers);
});

app.post("/user", async (req, res) => {
    const result = await prisma.user.create({
      data: {
        username: req.body.username,
        password: req.body.password,
      }
    });
    res.json(result);
});

app.get("/cities", async (req, res) => {
    const allCities = await prisma.uSCity.findMany();
    res.json(allCities);
});

app.get("/city/:name", async (req, res) => {
    const match = await prisma.uSCity.findFirst({
      name: req.body.name,
    });
    res.json(match);
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

// Return search hit given :hit  URL route parameters
app.get("/search-hit/:hit", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve(`pages/search-hit-${req.params.hit}.html`));
});

// http://sub.arcology.builders:5000 
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
/*
var server = http.createServer((req, res) => {
  res.writeHead(200, { 'Content-Type': 'text/plain' });
  res.end('okay');
});
*/
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
