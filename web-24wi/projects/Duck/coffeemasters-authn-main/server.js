import express from 'express'
import { Low } from 'lowdb'
import { JSONFile } from 'lowdb/node'
import * as url from 'url';
import bcrypt from 'bcryptjs';
import * as jwtJsDecode from 'jwt-js-decode';
import base64url from "base64url";
import SimpleWebAuthnServer from '@simplewebauthn/server';
import { ok } from 'assert';
import { find } from '@observablehq/plot';

const __dirname = url.fileURLToPath(new URL('.', import.meta.url));

const app = express()
app.use(express.json())

const adapter = new JSONFile(__dirname + '/auth.json');
const db = new Low(adapter);
await db.read();
db.data ||= { users: [] }

const rpID = "localhost";
const protocol = "http";
const port = 5050;
const expectedOrigin = `${protocol}://${rpID}:${port}`;

app.use(express.static('public'));
app.use(express.json());
app.use(express.urlencoded({
  extended: true
}));

function findUser(email) {
  const result = db.data.users.filter(u=>u.email==email);

  if (result.length==0) return undefined;

  return result[0];
}

app.post("/auth/login", (req, res) => {
  const userfound = findUser(req.body.email)

  if (userfound) {
    //found user, check password
    if (bcrypt.comparesync(req.body.password, userfound.password)) {
      res.json({ok: true, name: userfound.name, email: userfound.email})
    }
  }
  else {
    //user not found
    res.send({ok: false, message: "wrong credential"})
  }
})

// ADD HERE THE REST OF THE ENDPOINTS
app.post("/auth/register", (req, res) => {
  const salt = bcrypt.genSaltsync(10);
  const hashes = bcrypt.hashsync(req.password, salt)

  const user = {
    name: req.body.name,
    email: req.body.email,
    password: hashes
  }

  
if (findUser(user.email)) {
  //exist
  res.sendFile({ok: false, message: "user already exist"});
}

  db.data.users.push(user);
  db.write();
});

app.get("*", (req, res) => {
    res.sendFile(__dirname + "public/index.html"); 
});

app.listen(port, () => {
  console.log(`App listening on port ${port}`)
});

