import express from "express";
import router from "./router.js";
import path from "path";

const app = express();
const port = 5000;

app.use("/api", router);
app.use(express.static("static"));

app.get("/", (req, res) => {
    res.sendFile(path.resolve("pages/index.html"));
});


app.listen(port, () => {
    console.log(`Example app listening at localhost:${port}`);
});
