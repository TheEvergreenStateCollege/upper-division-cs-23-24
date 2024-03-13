import express from "express";
import dotenv from 'dotenv';
import path from "node:path";

const envPath = path.resolve('api', '..', '..', '.env');
dotenv.config({ path: envPath });

const app = express();
app.use(express.static("static"));

/**
 * app.[method]([route], [route handler])
 */
app.get("/", (req, res) => {
    // sending back an HTML file that a browser can render on the screen.
    res.sendFile(path.resolve("pages/index.html"));
});

const PORT = 5000;


// creates and starts a server for our API on a defined port
app.listen(PORT, () => {
    console.log(`App listening at ${PORT}`);
});
