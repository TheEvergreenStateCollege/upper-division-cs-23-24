const express = require("express");
const path = require("path");
const { PrismaClient } = require("@prisma/client");
const { parsed } = require("dotenv");

const prisma = new PrismaClient();

const app = express();
const port = 5000;

app.use(express.json());
app.use(express.static("static"));

// Create and start a server for our API on a defined port
app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`);
});

// ROUTES

//console.log(parsed["DATABASE_URL"]);
//console.log(process.env["DATABASE_URL"]);

/**
 * app.[method]([route], [route handler])
 */

// Main page
app.get("/", (_req, res) => {
    // Send index.html when browser requests /
    res.sendFile(path.resolve("public/index.html"));
});
app.get("/index.js", (_req, res) => {
    // Send index.html when browser requests /
    res.sendFile(path.resolve("public/index.js"));
});

app.get("/map", (_req, res) => {
    res.sendFile(path.resolve("public/map.html"));
});
app.get("/map.js", (_req, res) => {
    res.sendFile(path.resolve("public/map.js"));
});

// Search hits
app.get("/search-hit/:hit", (req, res) => {
    res.sendFile(path.resolve(`public/search-${req.params.hit}.html`));
});

// API

app.get("/api/users", async (_req, res) => {
    const allUsers = await prisma.user.findMany();
    res.json(allUsers);
});

app.post("/api/user", async (req, _res) => {
    try {
        const newUser = await prisma.user.create({
            data: {
                username: req.body.username,
                password: req.body.password,
            },
        })

        console.log("Created a user!")
    } catch (error) {
        console.log(error);
    }
});

