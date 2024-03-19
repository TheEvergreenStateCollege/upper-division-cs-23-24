import express from 'express';
import router from './router';
import morgan from 'morgan';
import path from "path";
import cors from "cors";
import { protect } from './modules/auth';
import { createNewUser, signin } from "./handlers/user";

const app = express();
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
app.use(express.static("pages"));
app.use(morgan('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(customLogger('user_computer'));

app.get("/", (req, res) => {
  res.sendFile(path.resolve("pages/index.html"));
  // setTimeout(() => {   Remember to use next in the params if uncommented
  //   next(new Error("oops"));
  // }, 1);
});

console.log(parsed['DATABASE_URL']);
console.log(process.env['DATABASE_URL']);

app.use('/api', protect, router);

app.post("/user", createNewUser);
app.post("/signin", signin);

// app.use((err, req, res, next) => {
//   console.log(err);
//   res.json({message: `had an error: ${err.message}`});
// });

app.use((err, req, res, next) => {
  if (err.type === 'auth') {
    res.status(401).json({message: 'unauthorized'});
  }
  else if (err.type === 'input') {
    res.status(400).json({message: 'invalid input'});
  }
  else {
    res.status(500).json({message: 'my bad'});
  }
});

export default app;