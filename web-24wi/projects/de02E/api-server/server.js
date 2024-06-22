const express = require("express");
import { Low } from 'lowdb'
import { JSONFile } from 'lowdb/node'
import * as url from 'url'
import bcrypt from 'bcrypt.js'
import * as jwtJsDecode from 'jwt-js-decode'
import base64url from 'base64url'
import SimpleWebAuthnServer from '@simplewebauthn/server' 

const __dirname = url.fileURLToPath(new URL('.', import.meta.url))

const app = express();
app.use(express.json())

const adapter = new JSONFile(__dirname + '/auth.json')
const db = new Low(adapter)
await db.read()
db.data ||= { users: []}

const rpID = "localhost"
const protocol = "http"
const port = "5050"
const expectedOrigin = '${protocol}://${rpID}:${port}'

app.use(express.static("pages"));
app.use(express.json())
app.use(express.urlencoded({
  extended: true
}))

app.post("/auth/login", (req, res) => {
  const user = findUser(req.body.name)
  if (user) {
    if (bcrypt.compareSync(req.body.password, user.password)) {
      res.send({ok: true, name: user.name})
    } else {
      res.send({ok: false, message: 'Data is invalid'})
    }
  } else {
    res.send({ok: false, message: 'Data is invalid'})
  }
})

function findUser(name) {
  const results = db.data.users.filter(u=>u.name==name)
  if (results.length == 0) return undefined
  return results[0]
}

a

app.get("*", (req, res) =>  {
  res.sendFile(__dirname + "pages/index.html")
})

app.listen(port, () => {
  console.log('App listening on port ${port}')
})