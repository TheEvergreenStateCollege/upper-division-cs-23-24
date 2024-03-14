import express from 'express';
import cors from "cors";
import morgan from 'morgan';
const path = require("path");
import { protect } from './modules/auth';
import router from './router';
import { createNewUser, signin } from "./handlers/user";

const app = express();
const port = 5000;
const { parsed } = require('dotenv').config();

app.use(express.static("pages"));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(cors());
app.use(morgan('dev'));

/* app.[method]([route], [route handler]) */
app.get("/", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve("pages/index.html"));
});

console.log(parsed['DATABASE_URL']);
console.log(process.env['DATABASE_URL']);

app.post("/login", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve("pages/logIn.html"));
});

app.post("/cart", (req, res) => {
	// sending back an HTML file that a browser can render on the screen.
	res.sendFile(path.resolve("pages/cart.html"));
  });

app.use('/api', protect, router);

app.post("/user", createNewUser);
app.post("/signin", signin);

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