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
    console.log("http://localhost:5000");
    console.log(process.cwd());
});


// chat server
const wss = new WebSocketServer({ server: server });

// game_id -> [ user_id ]
const games = new Map();
// user_id -> WebSockets
const clients = new Map();
wss.on("connection", (ws, req) => {
    console.log("client has connected");
    console.log(req.url);
    ws.on("error", console.error);
    ws.on("close", data => {
        console.log(JSON.stringify(ws.user_id));
        for (let [key, value] of clients) {
            if (value === ws) {
                clients.delete(key);
            }
        }
    });
    ws.on("message", data => {
        //update clients map
        const message = JSON.parse(data);
        ws.send(JSON.stringify(message));
        console.log(`${message.user_id} ${data}`);
        if (!clients.has(message.user_id)) {
            clients.set(message.user_id, ws);
        }

        //update games map
        if (!games.has(message.game_id)) {
            games.set(message.game_id, new Set([message.user_id]));
        } else {
            games.get(message.game_id).add(message.user_id);
        }

        const players = games.get(message.game_id)
        players.forEach(player_id => {
            if (!(player_id === message.user_id)) {
                const client = clients.get(player_id);
                client.send(message.fen_string);
            }
        });
    });
});
