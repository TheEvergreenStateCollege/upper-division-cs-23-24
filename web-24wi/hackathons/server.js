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
app.use(express.static("static"));
app.use(express.json());

/**
 * app.[method]([route], [route handler])
 */
app.get("/", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve("pages/index.html"));
});

app.get("/servers", async (req, res) => {
    const allServers = await prisma.server.findMany();
    res.json(allServers);
});

app.post("/server", async (req, res) => {
  const result = await prisma.server.create({
    data: {
      discordID: req.body.discordID,
      name: req.body.name,
    }
  });
  res.json(req.body);
})
//
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
