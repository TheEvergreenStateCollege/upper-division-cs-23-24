const express = require("express");
const app = express();
const port = 5000;
const path = require("path");

app.use(express.static("static"));
app.use(express.json());


app.get("/", function(req, res) {
        res.sendFile("/home/ubuntu/src/upper-division-cs/web-24wi/assignments/Gavin-Bowers/backend/api-server/index.html");
});

app.listen(port, () => {
        console.log("example app listening at port ?");
});
