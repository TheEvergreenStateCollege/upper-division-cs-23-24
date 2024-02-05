//This is for reference only and is not used after changeing to typescript
// Can be run to test a basic server.js 
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

// creates and starts a server for our API on a defined port
app.listen(port, () => {
  console.log(`Express api listening at http://pswish-corp.arcology.builders:${port}`);
});

// export default app
