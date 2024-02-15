import express from "express";
import path from "path";
import router from "./router.js";

const app = express();
const port = 5000;
app.use(express.json());
app.use("/api", router);
app.use(express.static(path.join(process.cwd())));

app.get("/", (req, res) => {
    res.sendFile(path.resolve("loginPage.html"));
});

app.get("/home", (req, res) => {
    res.sendFile(path.resolve("homePage.html"));
});

app.get("/game", (req, res) => {
    res.sendFile(path.resolve("gamePage.html"));
});



app.listen(port, () => {
    console.log(`Example app listening at localhost:${port}`);
    console.log(process.cwd());
});
