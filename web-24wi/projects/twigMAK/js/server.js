const express = require("express");
const app = express();
const DEFAULT_PORT = process.env.API_PORT || 5000;

// Start with default port, some servers may already be listening on port 5000
// On Mac OS on Sonoma and afterwards, Mac Control Center listens on this
let port = DEFAULT_PORT;
const path = require("path");
const { PrismaClient } = require('@prisma/client/edge');

const prisma = new PrismaClient();
app.use(express.static("pages"));

/**
 * app.[method]([route], [route handler])
 */
app.get("/", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve("pages/index.html"));
});

app.get("/users", async () => {
    const allUsers = await prisma.user.findMany();
    res.json(allUsers);
});

//--------------------------graph-----------------------------------
//UPDATE FOR MY WEBSITE
app.get("/randomGraph", async (req, res) => {
  let results = [];
  for (let i = 0; i < 10; i += 1){
    results.push({"time": i, "amount":Math.round(Math.random() * 1000)});
  }
  res.json({ results });
});
//-------------------------------------------------------------

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

//------------------------Registration---------------------------------
import bcrypt from 'bcryptjs';

function findUser(email) {
  const results = db.data.users.filter(u=>u.email==email);
  if (results.length==0) return undefined;
  return results[0];
}

app.post("/auth/register", (req, res) => {
  var salt = bcrypt.genSaltSync(10);
  var hash = bcrypt.hashSync(req.body.password, salt);

  const user = {
      name: req.body.name,
      email: req.body.email,
      password: hash
  };
  const userFound = findUser(req.body.email);

  if (userFound) {
      // User already registered
      res.send({ok: false, message: 'User already exists'});
  } else {
      // New User
      db.data.users.push(user);
      db.write();
      res.send({ok: true});
  }
});

//------------------------login---------------------------------
app.post("/auth/login", (req, res) => {
  const user = findUser(req.body.email);
  if (user) {
      // user found, check password
      if (bcrypt.compareSync(req.body.password, user.password)) {
          res.send({ok: true, email: user.email, name: user.name});
      } else {
          res.send({ok: false, message: 'Data is invalid'});            
      }
  } else {
      // User not found
      res.send({ok: false, message: 'Data is invalid'});
  }
});