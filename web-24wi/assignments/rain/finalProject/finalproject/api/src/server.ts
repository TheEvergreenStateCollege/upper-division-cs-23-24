import express from 'express'
import router from './router'
import morgan from 'morgan'
import path from 'path'
import { protect } from './modules/auth'
import { createNewUser, signin, deletUser, updateUser, updatePass } from './handlers/user'
import cors from 'cors'
import bycrypt from 'bcrypt'
import  cookieParser from 'cookie-parser'

// import fetch from 'node-fetch'

const app = express()
// const path = require("path")
app.use(cors())
app.use(morgan('dev'))
app.use(express.json())
app.use(express.urlencoded({extended: true}))
app.use(express.static('../client/dist'))
app.use (cookieParser())

app.get('/', (req,res) => {
    // console.log(req.cookies['user'])
    // console.log("test")
    // if(req.cookies['user']){
    //     console.log("already signed in")
    //     res.redirect("/api/profile")
    // }
    // else{
        res.sendFile(path.resolve("../client/dist/index.html"))
    
    // fetch('http://localhost:5173')
    
})

app.get('/entry', (req,res) => {

        res.sendFile(path.resolve("../client/dist/nested/index.html"))
    
    // fetch('http://localhost:5173')
    
})

app.get('/landing', (req,res) => {

        res.sendFile(path.resolve("../client/dist/landing/index.html"))
   
    // fetch('http://localhost:5173')
   
})

app.get('/post', (req,res) => {
    res.sendFile(path.resolve("../client/dist/post/index.html"))
    res.status(200)
    // fetch('http://localhost:5173')
   
})


app.get('/bluescreen', (req,res) => {
    res.status(200)
    res.sendFile(path.resolve("../client/blueScreen.html"))
})

app.get('/login', (req,res) => {

        res.sendFile(path.resolve("../client/dist/login/index.html"))
    
    
})



app.use('/api', protect, router)

app.post('/user', createNewUser) 
app.post('/signin', signin)




export default app
