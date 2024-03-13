import express from "express";
import path from "node:path";

const app = express();
app.use(express.static("static"));

/**
 * app.[method]([route], [route handler])
 */
app.get("/", (req, res) => {
    // Update the path to the correct location of your HTML file
    res.sendFile(path.resolve(__dirname, 'pages', 'index.html'));
});

const PORT = 5000;


// creates and starts a server for our API on a defined port
app.listen(PORT, () => {
    console.log(`App listening at ${PORT}`);
});
