import express from 'express'
import router from './router'
import morgan from 'morgan'
import path from 'path'
import { protect } from './modules/auth'
import { createNewUser, signin } from './handlers/user'
import { body, validationResult } from 'express-validator'


const app = express()
// const path = require("path")


app.use(morgan('dev'))
app.use(express.json())
app.use(express.urlencoded({extended: true}))

app.get('/', (req,res, next) => {
    res.status(200)
    res.sendFile(path.resolve("pages/index.html"))
    setTimeout(() => {
        next(new Error('uhoh'))
    },1)
});

app.use('/api', protect, router)

app.post('/user', createNewUser)
app.post('/signin', signin)

app.use((err, req, res, next) => {
    console.log(err)
    res.json({message: `oops an error occured: ${err.message}` })
})

export default app