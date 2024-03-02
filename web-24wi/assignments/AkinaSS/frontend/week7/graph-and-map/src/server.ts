import express from 'express';
import router from './router';
import morgan from 'morgan';
import path from "path";
import cors from "cors";
import * as myModule from './modules/auth';
import { createNewUser, signin } from "./handlers/user";
import { body, validationResult } from "express-validator";

const app = express();
import { PrismaClient } from "@prisma/client";
const { parsed } = require("dotenv").config();

const customLogger = (message) => (req, res, next) => {
  console.log(`Hello from ${message}`);
  next();
}

// Middleware to set MIME type for CSS files
app.use("/styles", (req, res, next) => {
  res.header('Content-Type', 'text/css');
  next();
});

// Serve static files (including stylesheets).
app.use("/styles", express.static(path.resolve(__dirname, 'static/styles')));
app.use(cors());
app.use(express.static("static"));
app.use(morgan('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(customLogger('user_computer'));

app.get("/", (req, res) => {
  res.sendFile(path.resolve("pages/index.html"));
});

app.post("/login", (req, res) => {
  console.log(req.body);
  // res.json(req.body);
});

console.log(parsed['DATABASE_URL']);
console.log(process.env['DATABASE_URL']);
const prisma = new PrismaClient();

app.get("/users", async (req, res) => {
const allUsers = await prisma.user.findMany();
res.json(allUsers);
});

app.post("/user", async (req, res) => {
  try {
    const newUser = await prisma.user.create({
      data: {
        username: req.body.username,
        password: '',
      },
    });
    console.log("User created:", newUser);
    res.status(201).json({ message: "User created successfully", user: newUser });
  } catch (error) {
    console.error("Error creating user:", error);
    res.status(500).json({ message: "Failed to create user" });
  }
});

app.use('/api', router);

export default app;

app.use("/api", myModule.protect, router);

app.post("/user", createNewUser);
app.post("/signin", signin);

app.post("/product", body("name").isString(), (req, res) => {
  const errors = validationResult(req);

  if (!errors.isEmpty()) {
    res.status(400);
    res.json({ errors: errors.array() });
  }
});

//Error handler function
/*app.get("/", () => {
  throw new Error("oops");
});

app.use((err, req, res, next) => {
  // handle error
});

const handler = async (req, res, next) = {
  // ....
  try {
    const user = await prisma.user.create({})
  } catch(e) {
    next(e)
  }
}

app.use((err, req, res, next) => {
  if (err.type === "auth") {
    res.status(401);
    res.json({ message: "nope" });
  }
});

catch (e) {
  e.type = 'input'
  next(e)
}

app.use((err, req, res, next) => {
  if (err.type === 'input') {
    res.status(400)
    return res.send('invalid input')
  }
})*/