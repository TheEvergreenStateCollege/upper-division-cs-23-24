import express from 'express'
import router from './router'
import morgan from 'morgan'

const app = express()

app.use(morgan('dev'))
app.use(express.json())
app.use(express.urlencoded({extended: true}))

/*custom middleware*/
app.use((req, res, next) => {
    /*req.sh_secret = 'cat'
    next()*/
    res.json({message: 'message'})
})

/*end request, do not go to further route
app.use((req, res, next) => {
    res.status(401)
    res.send('Nope')
})*/

app.get('/', (req, res) => {
    console.log('express says hello')
    res.status(200)
    res.json({message: 'hello'})
})

app.use('/api', router)

export default app