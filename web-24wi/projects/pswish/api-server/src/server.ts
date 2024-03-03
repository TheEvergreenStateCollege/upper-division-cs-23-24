import express from "express";
import router from "./router";
import morgan from "morgan";
import path from "path";
import cors from "cors";

const app = express();
import { PrismaClient } from "@prisma/client";
import { protect } from "./modules/auth";
import { createNewUser, signin } from "./handlers/user";
const { parsed } = require("dotenv").config();

const customLogger = (message) => (req, res, next) => {
  console.log(`Hello from ${message}`);
  next();
};

// Middleware to set MIME type for CSS files
app.use("/styles", (req, res, next) => {
  res.header("Content-Type", "text/css");
  next();
});

// Serve static files (including stylesheets).
app.use("/styles", express.static(path.resolve(__dirname, "static/styles")));
app.use(cors());
app.use(express.static("static"));
app.use(morgan("dev"));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(customLogger("user_computer"));
app.use(express.static("public"));

app.get("/", (req, res) => {
  res.sendFile(path.resolve("pages/index.html"));
});

console.log(parsed["DATABASE_URL"]);
console.log(process.env["DATABASE_URL"]);

app.use("/api", protect, router);

app.post("/user", createNewUser);
app.post("/signin", signin);

app.get("/maps", (req, res) => {
  res.sendFile(path.resolve("pages/maps.html"));
});

// custom error handling
app.use((err, req, res, next) => {
  if (err.type === 'auth') {
    res.status(401).json({message:'Authentication failed'})
  } else if (err.type === 'input')  {
    res.status(400).json({message: 'Invalid input'})
  } else {
    res.status(500).json({message: 'Internal server error, check with system administrator for stack trace'})
      }})

export default app;
