const express = require("express");
const app = express();
const port = 5000;
const path = require("path");


app.use(express.static("static"));

app.get("/", (req, res) => {
    res.sendFile(path.resolve("pages/index.html"));
});

app.post("/login", (req, res) => {
    console.log(req);
});

app.listen(port, () => {
    console.log(`Example app listening at localhost:${port}`);
});
