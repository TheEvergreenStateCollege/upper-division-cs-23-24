const express = require("express");
const app = express();
const port = 5000;
const path = require("path");


app.use(express.static("static"));

app.get("/", (req, res) => {
    res.sendFile(path.resolve("pages/index.html"));
});

// not implemenented
app.get("/user/:id", (req, res) => {
    console.log(req.body);
});

// not implemenented
app.get("/users", (req, res) => {
    console.log(req.body);
});

// not implemenented
app.post("/user", (req, res) => {
    console.log(req.body);
});

// not implemented
app.put("/user/:id", (req, res) => {
    console.log(req.body);
});

app.listen(port, () => {
    console.log(`Example app listening at localhost:${port}`);
});
