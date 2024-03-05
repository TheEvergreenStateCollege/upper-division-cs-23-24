const dotenv = require('dotenv');
require('dotenv').config();

const app = require('./server');

app.listen(5000, () => {
    console.log('Server is running on http://localhost:5000');
});