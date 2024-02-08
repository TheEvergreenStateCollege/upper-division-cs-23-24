const express = require("express");
const path = require("path");
const PrismaClient = require("@prisma/client");
const parsed = require("dotenv");

const app = express();
const port = 5000;

console.log(parsed["DATABASE_URL"]);
console.log(process.env["DATABASE_URL"]);
const prisma = new PrismaClient();

app.use(express.json());
app.use(express.static("static"));

/**
 * app.[method]([route], [route handler])
 */
app.get("/", (_req, res) => {
    // Send index.html when browser requests /
    res.sendFile(path.resolve("www/index.html"));
});
app.get("/index.js", (_req, res) => {
    // Send index.html when browser requests /
    res.sendFile(path.resolve("www/index.js"));
});

app.get("/search-hit/:hit", (req, res) => {
    res.sendFile(path.resolve(`www/search-${req.params.hit}.html`));
});

app.post("/login", (req, res) => {
    console.log(`${JSON.stringify(req.body)}`);
    res.json(req.body);
})

// Create and start a server for our API on a defined port
app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`);
});

// PostgreSQL
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
    })

    console.log("Created a user!")
});

