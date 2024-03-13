const express = require('express');
const cors = require('cors');
const app = express();

app.use(cors());

app.get('/', (req, res) => {
    res.send('Hello from our server!')
});


app.listen(8080, () => {
      console.log('server listening on port 8080')
});