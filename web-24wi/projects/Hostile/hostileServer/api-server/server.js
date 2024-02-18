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

app.get("/randomGraph", async (req, res) => {
  let results = [];
  for (let i = 0; i < 10; i += 1) {
    results.push({ "day": i, "stepCount": Math.round(Math.random() * 1000) });
  }
  res.json({ results });
});

app.get("/graph.html", (req, res) => {
  res.sendFile(path.resolve("pages/graph.html"))
})

// creates and starts a server for the API on a defined port
app.listen(port, () => {
  console.log(`Listening at http://localhost:${port}`);
});
