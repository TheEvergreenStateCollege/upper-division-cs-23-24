const express = require('express');
const router = require('./router');
const morgan = require('morgan');


const app = express();

//now use
app.use(morgan('dev'));
app.use(express.json());
app.use(express.urlencoded({extended: true}))

//get
app.get('/', (req, res) => {
    console.log('hello from express')
    res.status(200)
    res.json({message: 'hello'})
});

app.use ('/api', router);


module.exports = app;