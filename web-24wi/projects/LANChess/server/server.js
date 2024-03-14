import express from "express";
import { URL } from "url";
import path from "path";
import router from "./router.js";
import * as dotenv from 'dotenv';
import cors from "cors";
import morgan from "morgan";
import config from "../config/index.js";
import { protect } from "./modules/auth.js";
import { createNewUser, signin } from "./handlers/user.js";
import { WebSocketServer } from "ws";
import { url } from "inspector";
import { createMove } from "./handlers/move.js";

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

const server = app.listen(config.port, () => {
    console.log(`Example app listening at localhost:${config.port}`);
    console.log(`http://localhost:${config.port}`);
    console.log(process.cwd());
    console.log(process.env.DATABASE_URL);
});


// chat server
const wss = new WebSocketServer({ server: server });
// game_id -> Set<string>
const games = new Map();
// user_id -> WebSocket
const clients = new Map();

wss.on("connection", (ws, req) => {
    const params = new URLSearchParams(req.url.split("?")[1]);
    const userid = params.get("userid");
    const gameid = params.get("gameid");
    console.log("client has connected");
    console.log(params);

    clients.set(userid, ws);

    if (games.has(params.get("gameid"))) {
        games.get(gameid).add(userid);
        console.log("game existed");
    } else {
        games.set(gameid, new Set([userid]));
    }

    console.log(clients.keys());
    console.log(games);

    ws.on("error", console.error);

    // delete users socket
    // if number of users in game room is 0 remove game as well
    ws.on("close", () => {
        clients.delete(userid);
        const game = games.get(gameid);
        game.delete(userid);

        if (game.size === 0) {
            games.delete(gameid);
        }
    });
    ws.on("message", data => {
        const message = JSON.parse(data).FEN_string;
        console.log(message);
        const players = games.get(gameid)

        players.forEach(player_id => {
            if (!(player_id === userid)) {
                const client = clients.get(player_id);
                client.send(JSON.stringify({ FEN_string: message }));
            }
        });
        //createMove({
        //    FEN_string: message,
        //    userid: userid,
        //    gameid: gameid,
        //})
    });
});

export default app;
