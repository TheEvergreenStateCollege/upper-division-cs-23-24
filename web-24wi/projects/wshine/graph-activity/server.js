import express from "express";
import path from "path";
const app = express();
const port = 5000;

app.use(express.json());
app.use(express.static(path.join(process.cwd())));

app.get("/", (req, res) => {
    res.sendFile(path.resolve("graph.html"));
})
app.get("/randomGraph", async (req, res) => {
    let results = [];
    for (let i = 0; i < 10; i += 1) {
        results.push({ "day": i, "stepCount": Math.round(Math.random() * 1000) });
    }

    res.json({ results });
})

app.listen(port, () => {
    console.log(`listening on ${port}`);
})
