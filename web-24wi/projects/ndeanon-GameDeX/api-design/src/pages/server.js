import express from 'express'
import { Low } from 'lowdb'
import { JSONFile } from 'lowdb/node'
import * as url from 'url';
import bcrypt from 'bcryptjs';
import * as jwtJsDecode from 'jwt-js-decode';
import base64url from "base64url";
import SimpleWebAuthnServer from '@simplewebauthn/server';
import path from 'path';

const __dirname = url.fileURLToPath(new URL('.', import.meta.url));

const app = express()
app.use(express.json())

const adapter = new JSONFile(__dirname + '/auth.json');
const db = new Low(adapter);
await db.read();
db.data ||= { users: [] }

const rpID = "localhost";
const protocol = "http";
const port = 3000;
const expectedOrigin = `${protocol}://${rpID}:${port}`;

app.use(express.static('public'));
app.use(express.json());
app.use(express.urlencoded({
  extended: true
}));

function findUser(email){
    const results = db.data.users.filter(user =>user.email === email);
    if(results.length === 0)
        return undefined;
    return results[0];
}

// ADD HERE THE REST OF THE ENDPOINTS
app.post("/auth/login", (req,res) => {
    console.log("Login request received:", req.body);
    const userFound = findUser(req.body.email);
    if(userFound) {
        //UserFound, Check Password
    if(bcrypt.compareSync(req.body.password, userFound.password)){
        res.status(200).json({ok: true, name: userFound.name,
            email:userFound.email});
    } else {
        //User found but wrong password
        res.send({ok: false, message: "Invalid username or password. Please try again."})
    }   
    }else{
        //User not found
        res.send({ok:false, message: "Invalid username or password. Please try again."})
    }

});


app.post ("/auth/register", async (req,res) => {
    console.log("Register request received:", req.body);
    const salt = bcrypt.genSaltSync(10);
    const hashedPass = bcrypt.hashSync(req.body.password, salt);

    //TODO: Data Validation
    const user = {
        name: req.body.name,
        email: req.body.email,
        password: hashedPass
    }

    const userFound = findUser(user.email);
    if(userFound) {
        //User already found
        res.send({ok: false, message: "User already exists!"});
    } else{
        //User is new!
        db.data.users.push(user);
        await db.write();
        res.send({ok: true});

    }

    db.data.users.push(user);
    db.write();
    });


app.get("*", (req, res) => {
    res.sendFile(path.join(__dirname + "public" , "index.html")); 
});

app.listen(port, () => {
  console.log(`App listening on port ${port}`)
});

