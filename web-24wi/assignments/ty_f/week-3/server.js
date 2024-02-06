
const path = require("path");
const { PrismaClient } = require('@prisma/client/edge');
dG


app.use(express.static("static"));

/**
 * app.[method]([route], [route handler])
 */
app.get("/", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve("pages/index.html"));
});

app.get("/users", async () => {
    const allUsers = await prisma.user.findMany();
    res.json(allUsers);
});

// Return search hit given :hit  URL route parameters
app.get("/search-hit/:hit", (req, res) => {
