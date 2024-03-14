import express from 'express';
const app = express();
const port = 5000;
import path from 'path';

app.use(express.static("static"));

/**
 * app.[method]([route], [route handler])
 */
app.get("/", (req, res) => {
    res.sendFile(path.resolve("src/pages/index.html"));
});

// Handler function to serve HTML files
function serveHTMLFile(req, res, fileName) {
    res.sendFile(path.resolve(`src/pages/${fileName}.html`));
}

// creates and starts a server for our API on a defined port
app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`);
});