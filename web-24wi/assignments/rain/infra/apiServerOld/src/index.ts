import * as dotenv from 'dotenv'
dotenv.config()

import app from './server'

app.list(3001, () => {
	console.log('hello on http://localhost:3001')
})
