import express from 'express'
import router from './router'
import morgan from 'morgan'
import path from 'path'
import { protect } from './modules/auth'
import { createNewUser, signin } from './handlers/user'
import cors from 'cors'
import bycrypt from 'bcrypt'
// import fetch from 'node-fetch'

const app = express()
// const path = require("path")
app.use(cors())
app.use(morgan('dev'))
app.use(express.json())
app.use(express.urlencoded({extended: true}))
app.use(express.static('../client/dist'))

app.get('/', (req,res) => {
    res.status(200)
    // fetch('http://localhost:5173')
    res.sendFile(path.resolve("../client/dist/index.html"))
})

app.get('/entry', (req,res) => {
    res.status(200)
    // fetch('http://localhost:5173')
    res.sendFile(path.resolve("../client/dist/nested/index.html"))
})

app.post('auth/register', (req,res) => {
    var salt = bycrypt.genSaltSync(10);
    var hash = bycrypt.hashSync(req.body.password, salt);

    const user = {
        username: req.body.username,
        password: hash
    };
    
})

// app.get('/', (req,res) => {
//     res.status(200)
//     res.send('Hello from our server!')
// })

// app.get('/', (req, res) => {
//     res.sendFile(path.join(__dirname, 'privacy.html'))
// })


app.get('/bluescreen', (req,res) => {
    res.status(200)
    res.sendFile(path.resolve("../client/blueScreen.html"))
})

app.use('/api', protect, router)

app.post('/user', createNewUser)
app.post('/signin', signin)

export default app
