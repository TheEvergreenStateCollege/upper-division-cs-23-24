const dotenv = require('dotenv');
dotenv.config();

const app = require('./server');


app.listen(3001, () => {
    console.log('hi from localhost3001')
});