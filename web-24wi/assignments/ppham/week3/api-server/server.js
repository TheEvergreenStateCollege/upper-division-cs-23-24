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

// Return search hit given :hit  URL route parameters
app.get("/search-hit/:hit", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve(`pages/search-hit-${req.params.hit}.html`));
});

app.post("/adopt-pet/:name", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  console.log(`Adopted pet ${req.params.name}`);
  res.json({"adopted": true, "name": req.params.name});
});


// http://sub.arcology.builders:5000 

// creates and starts a server for our API on a defined port
app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});
