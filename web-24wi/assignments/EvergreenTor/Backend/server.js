const express = require("express");
const { register } = require("module");
const app = express();
const port = 5000;
const path = require("path");

app.use(express.static("public"));

/**
 * app.[method]([route], [route handler])
 */
app.get("/", (req, res) => {
    // sending back an HTML file that a browser can render on the screen.
    res.sendFile(path.resolve("public/index.html"));
  });

app.get("/login", (req, res) => {
    // sending back an HTML file that a browser can render on the screen.
    res.sendFile(path.resolve("public/index.html"));
  });


app.post("/login", (req, res) => {
    // sending back an HTML file that a browser can render on the screen.
// TO DO: fix req handle, send useful response
     res.send
  });

/*
// Return search hit given :hit  URL route parameters
app.get("/search-hit/:hit", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve(`pages/search-hit-${req.params.hit}.html`));
});
*/

// http://tor.arcology.builders:5000

// creates and starts a server for our API on a defined port
app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});

const { PrismaClient } = require('@prisma/client');
const{ parsed } = require('dotenv').config();

console.log(parsed['DATABASE_URL']);
console.log(process.env['DATABASE_URL']);
const prisma = new PrismaClient();

app.get("/user", async (req, res ) => {
  const allUsers = await prisma.user.findmany();
  res.json(allUsers);
});

app.post("/user", async (req, res) => {
  const newUser = await prisma.user.create({
    data: {
      userame: req.body.username,
      password: '',
    },
  });
  console.log("created");
});