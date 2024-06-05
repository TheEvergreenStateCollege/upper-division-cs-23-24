import express from 'express'
import router from './router'
import morgan from 'morgan'
import cors from 'cors'
import { protect } from './modules/auth'
import { createNewUser, signin } from './handlers/user'

const app = express()

app.use(cors())
app.use(morgan('dev'))
app.use(express.json())
app.use(express.urlencoded({extended: true}))

/*custom middleware*
app.use((req, res, next) => {
    res.json({message: 'message'})
}) */

app.get('/', (req, res) => { //server up and running
    console.log('express says hello')
    res.status(200)
    res.json({message: 'hello'})
})

app.use('/api', protect, router)

app.post('/user', createNewUser)
app.post('/signin', signin)

//error handler
app.use((err, req, res, next) => {
    //console.log(err)
    //res.json({message: 'oops-daise there was an error'})
    if (err.type == 'auth') {
        res.status(401).json({message: 'unauthorized'})
    } else if (err.type == 'input'){
        res.status(400).json({message: 'invalid input'})
    } else {
        res.status(500).json({message: 'oops, something went wrong on are side'})
    }
})

export default app