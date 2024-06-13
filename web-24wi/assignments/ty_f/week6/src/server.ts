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
export const createNewUser = async (req, res, next) => {
  console.log(req.body.username);
  try {
    //const salt = bcrypt.genSaltsync(10);
    //const hashes = bcrypt.hashsync(req.password, salt);
    let userExist = await findUser(req.body.username)
    if (userExist != undefined) {
      //the user already exist
      res.json({ok: false, message: "user already exist"});
    }

  const hash = await hashPassword(req.body.password);

    const user = await prisma.user.create({
      data: {
        username: req.body.username,
        password: hash,
      },
    });

    console.log("User created:", user);
    const token = createJWT(user);
    res.status(201).json({ token });
  }

  catch (e) {
    e.type = 'input';
    console.error("Error creating user:", e);
    next(e);
  }
};

export const signin = async (req, res) => {
  //console.log(JSON.stringify(req.headers));
  console.log(JSON.stringify(req.body));

  const userfound = findUser(req.body.username)

  if (userfound) {
    //console.log(JSON.stringify(req.headers));
    console.log(JSON.stringify(req.body));

    const user = await prisma.user.findUnique({
      where: { username: req.body.username },
    });
  
    const isValid = await comparePasswords(req.body.password, user?.password);
  
    if (!isValid) {
      res.status(401);
      res.json({ message :"Invalid username or password"} );
      return;
    }

    // if (userfound) {
    //   //found user, check password
    //   if (bcrypt.comparesync(req.body.password, userfound.password)) {
    //     res.json({ok: true, name: userfound.name, email: userfound.email})
    //   }
    // }
    // else {
    //   //user not found
    //   res.send({ok: false, message: "wrong credential"})
    // }
  
    const token = createJWT(user);
    res.json({ token });
  }
  else {
    res.status(401);
    res.json({ message :"Invalid username or password"} );
    return;
  }
};