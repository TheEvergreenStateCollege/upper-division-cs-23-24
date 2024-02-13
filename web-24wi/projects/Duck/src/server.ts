import express from 'express'
import router from './router';
import morgan from 'morgan'
import path from "path";
import cors from "cors"

const app = express();

const customLogger = (message) => (req, res, next) => {
  console.log('Hello from ${message}')
  next()
}

app.use(cors());
app.use(express.static("static"));

app.use(morgan('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(customLogger('user_computer'));

// Blocking, stopping app.use example middleware
// app.use((req, res, next) => {
//   res.status(401)
//   res.json({message: 'Unauthorized'})
// //   next()
// })

/**
 * app.[method]([route], [route handler])
 */
app.get("/", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve("pages/index.html"));
});

app.use('/api', router);


export default app
