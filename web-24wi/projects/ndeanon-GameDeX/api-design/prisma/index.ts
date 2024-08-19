import * as dotenv from 'dotenv'
dotenv.config()
import config from '../src/config'
import app from './server'

// TODO: Work on the STAGE=PRODUCTION command, will not work on different ports. 
app.listen(config.port, () => {
    console.log(`hello on http://localhost:${config.port}`)
})