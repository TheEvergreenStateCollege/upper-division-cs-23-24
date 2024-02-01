const express = require("express");
const app = express();
const port = 5000;
const path = require("path");

app.use(express.static("static"));

/**
 * app.[method]([route], [route handler])
 */
app.get("/", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve("pages/index.html"));
});

app.get("/search-hit/:hit", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve(`pages/search-hit-${req.params.hit}.html`));
});


// creates and starts a server for our API on a defined port
app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});

app.post("/login", (req, res) => {
	console.log(` ${req.body}`);
	const bodyJSON = JSON.parse(req.body);
	res.json(bodyJSON);
});

// codepen code
const button = document.getElementById("submit");
const username = document.getElementById("input-username");
const password = document.getElementById("input-password");

button.addEventListener("click", async (event) => {
	event.preventDefault();
	const response = await fetch("http://rilesbe.arcology.builders:5000", {
		"method": "post",
		"body": { username, password }
	});
	console.log("I've been clicked!");
});
