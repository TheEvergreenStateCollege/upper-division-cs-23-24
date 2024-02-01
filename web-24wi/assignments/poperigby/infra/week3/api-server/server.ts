const express = require("express");
const path = require("path");

const app = express();
const port = 5000;

app.use(express.static("static"));

/**
 * app.[method]([route], [route handler])
 */
app.get("/", (_req, res) => {
    // Send index.html when browser requests /
    res.sendFile(path.resolve("pages/index.html"));
});

app.get("/search-hit/:hit", (req, res) => {
    res.sendFile(path.resolve(`pages/search-${req.params.hit}.html`));
});

// Create and start a server for our API on a defined port
app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`);
});


