import express from "express";
import path from "path";
import router from "./router.js";
import * as dotenv from 'dotenv';
import cors from "cors";
import morgan from "morgan";
import { protect } from "./modules/auth.js";
import { createNewUser, signin } from "./handlers/user.js";

dotenv.config();
const app = express();
const port = 5000;
app.use(express.json());
app.use(cors());
app.use(morgan("dev"));
app.use(express.urlencoded({extended: true}));
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

// proctected api, user has to already be authenticated
app.use("/api", protect, router);

// api for user to authenticate
app.post("/login", signin);
app.post("/register", createNewUser);

app.listen(port, () => {
    console.log(`Example app listening at localhost:${port}`);
    console.log(process.cwd());
});
