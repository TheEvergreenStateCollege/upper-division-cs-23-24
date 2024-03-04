import express from 'express';
import router from './router';
import morgan from 'morgan';
import path from "path";
import cors from "cors";
import { protect } from './modules/auth';
import { createNewUser, signin } from "./handlers/user";

const app = express();
const { parsed } = require("dotenv").config();

// Serve static files (including stylesheets).
app.use("/styles", express.static(path.resolve(__dirname, 'static/styles')));
app.use(cors());
app.use(express.static("pages"));
app.use(morgan('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.get("/", async (req, res, next) => {
  res.sendFile(path.resolve("pages/index.html"));
});

app.use(async (err, req, res, next) => {
  console.log(err);
  res.json({message: `had an error: ${err.message}`});
});

console.log(parsed['DATABASE_URL']);
console.log(process.env['DATABASE_URL']);

app.use("/api", protect, router);

app.post("/user", createNewUser);
app.post("/signin", signin);

export default app;
