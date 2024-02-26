import express from 'express'
import router from './router'
import morgan from 'morgan'
import path from 'path'
import { protect } from './modules/auth'
import { createNewUser, signin } from './handlers/user'

import { PrismaClient } from '@prisma/client';

const prisma = new PrismaClient();
const app = express()
// const path = require("path")

app.use(morgan('dev'))
app.use(express.json())
app.use(express.urlencoded({extended: true}))

app.get('/', (req,res) => {
    res.status(200)
    res.sendFile(path.resolve("pages/index.html"))
})

app.use('/api', protect, router)

app.post('/user', createNewUser)
app.post('/signin', signin)

app.get("/users", async (req, res) => {
        const allUsers = await prisma.user.findMany();
        res.json(allUsers);
});

export default app
