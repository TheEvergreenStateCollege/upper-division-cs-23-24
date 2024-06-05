import * as dotenv from 'dotenv'
dotenv.config()

import app from './server'

const port = 3002;

app.listen(port, () => {
    console.log('hello on http://localhost:'+port)
})