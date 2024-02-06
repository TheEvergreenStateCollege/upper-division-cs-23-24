const express = require("express");
const url = require('url');
const { bcrypt } = require('bcryptjs');
const jwtJsDecode = require('jwt-js-decode');
const { base64url } = require('base64url');

const app = express();
const port = 5000;
const path = require("path");

const { PrismaClient } = require('@prisma/client');

const prisma = new PrismaClient();

app.use(express.static("public"));
app.use(express.json());
app.use(express.urlencoded({
  extended: true
}))

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

// Return search hit given :hit  URL route parameters
app.get("/search-hit/:hit", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve(`pages/search-hit-${req.params.hit}.html`));
});

// http://sub.arcology.builders:5000 

const rpID = "localhost";
const protocol = "http";
const expectedOrigin = `${protocol}://${rpID}:${port}`;

app.use(express.static('public'));
app.use(express.json());
// const __dirname = url.fileURLToPath(new URL('.', import.meta.url));

app.listen(port, () => {
  console.log(`App listening on port ${port}`);
});
