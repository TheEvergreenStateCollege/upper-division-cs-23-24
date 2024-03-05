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
  res.sendFile(path.resolve("pages/map.html"));
});

app.get("/us.json", (req, res) => {
	res.sendFile(path.resolve("./us.json"));
});

app.get("/uscities.json", (req, res) => {
  res.sendFile(path.resolve("./uscities.json"))
});

// creates and starts a server for our API on a defined port
app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});
