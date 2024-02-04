
const express = require("express");
const app = express();
const port = 5000;
const path = require("path");

app.use(express.static("public"));

app.get("/", (req, res) => {
	res.sendFile(path.resolve("../../frontend/week4/wordle/index.html"));
});

app.listen(port, () => {
	  console.log(`Example app listening at http://localhost:${port}`);
});
