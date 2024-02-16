const express = require("express");
const app = express();
const port = 5000;
const path = require("path");

app.use(express.static("public"));

/**
 * app.[method]([route], [route handler])
 */
app.get("/", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve("public/index.html"));
});

/*
// Return search hit given :hit  URL route parameters
app.get("/search-hit/:hit", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve(`pages/search-hit-${req.params.hit}.html`));
});
*/

// http://tor.arcology.builders:5000

// creates and starts a server for our API on a defined port
app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});

//Sevres a loist of data to a static page in final project directory

app.get("/randomGraph", async (req, res) => {
  let results = [];
  for (let i= 0; i < 10; i++) {
    results.push({"day": i, "stepcount": Math.round(Math.random() * 100) });
  }
  res.json({ results });

});
