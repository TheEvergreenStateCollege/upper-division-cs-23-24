import express from 'express'
import router from './router'
import morgan from 'morgan'
import cors from 'cors'
import { protect } from '../src/modules/auth'
import { createNewUser, signin } from '../src/handlers/user'

const app = express();

app.use(cors())
app.use(morgan('dev'))
app.use(express.json())
app.use(express.urlencoded({extended: true}))

app.use((req, res, next) => {
    req.shhhh_secret = 'doggy'
    next()
})

app.get('/', (req, res, next) => {
    res.json({ message: 'hello'})

})

app.use('/api', protect, router)

app.post('/user', createNewUser)
app.post('/signin', signin)


//TODO : Invalid Errors need to be worked on
app.use((err, req, res, next) => {
    if(err.type === 'auth') {
        res.status(401).json({ message: 'unauthorized'})
    } else if(err.type === 'input'){
        res.status(400).json({message:'invalid input'})
    } else{
        res.status(500).json({message: 'My bad, Im trying'})
    }
})


export default app