const express = require("express");
const app = express();
const cors = require("cors");
const path = require("path");

const port = process.env.PORT || 5000;
const buildPath = path.join(__dirname, "../client/build");

app.use(cors());

// Serve static files from the 'build' directory
app.use(express.static(buildPath));

// Handle React routing, return all requests to React app
app.get("*", (req, res) => {
  res.sendFile(path.join(buildPath, "index.html"));
});

// Start the server
app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
