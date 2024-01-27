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

// return search hit given :hit URL route paramets
apt.get("/search-hit/:hit", (req, res) => {
	// sending back an HTML files that a browser can redner on the screen
	res.sendFile(path.resolve('pages/serch-hit-${req.params.hit}.html'));
});


// creates and starts a server for our API on a defined port
app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});

