const express = require("express");
const app = express();
const DEFAULT_PORT = process.env.API_PORT || 5000;
const path = require("path");


let port = DEFAULT_PORT;
const path = require("path");
const { parsed } = require("dotenv").config();
const { PrismaClient } = require('@prisma/client');

const prisma = new PrismaClient();
app.use(express.static("static"));

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

// creates and starts a server for our API on a defined port
app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});
