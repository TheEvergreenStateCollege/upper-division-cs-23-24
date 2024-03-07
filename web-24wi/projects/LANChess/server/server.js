import express from "express";
import path from "path";
import router from "./router.js";
import * as dotenv from 'dotenv';
import cors from "cors";
import morgan from "morgan";
import { protect } from "./modules/auth.js";
import { createNewUser, signin } from "./handlers/user.js";
import { WebSocketServer } from "ws";

dotenv.config();
const app = express();
const port = 5000;

// http server
app.use(express.json());
app.use(cors());
app.use(morgan("dev"));
app.use(express.static(path.join(process.cwd())));

app.get("/", (req, res) => {
    res.sendFile(path.resolve("userRegisterAndLoginPage.html"));
});

app.get("/home", (req, res) => {
    res.sendFile(path.resolve("homePage.html"));
});

app.get("/game", (req, res) => {
    res.sendFile(path.resolve("onlineGamePage.html"));
});
app.get("/example", (req, res) => {
    res.sendFile(path.resolve("example.html"));
});
// proctected api, user has to already be authenticated
app.use("/api", protect, router);

// api for user to authenticate
app.post("/login", signin);
app.post("/register", createNewUser);

const server = app.listen(port, () => {
    console.log(`Example app listening at localhost:${port}`);
    console.log(process.cwd());
});

// chat server
const wss = new WebSocketServer({ server: server });

// String -> [ WebSockets ]
const ongoingGames = new Map();

function handleMessage(data) {
    if (data.jwt) {
       console.log(data.jwt); 
    } else if (!data.gameid) {
        console.log("WEBSOCKET error: missing gameid in message");
    } else if (!data.message) {
        console.log("WEBSOCKET error: missing message");
    }
}
wss.on("connection", ws => {
    ws.on("error", console.error);
    ws.on("message", data => {
        console.log("received %s", JSON.parse(data));
        handleMessage(data);
        ws.send(data.toString());
    });
});

