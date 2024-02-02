import express from 'express';
import router from './router';
import morgan from 'morgan';
import path from "path";
import cors from "cors";

const app = express();

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

app.use('/api', router);

export default app;
