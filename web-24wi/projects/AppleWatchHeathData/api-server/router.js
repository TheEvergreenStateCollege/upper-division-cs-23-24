const express = require('express');
const router = express.Router();

/*
Daily-Watch-Data
*/
router.get('/daily-watch-data', (req, res) => {
     res.json({message: 'hello'});
});

router.get('/daily-watch-data/id', (req, res) => {
    // Implementation for GET /daily-watch-data/id
});

router.put('/daily-watch-data/id', (req, res) => {
    // Implementation for PUT /daily-watch-data/id
});

router.post('/daily-watch-data', (req, res) => {
    // Implementation for POST /daily-watch-data
});

router.delete('/daily-watch-data/id', (req, res) => {
    // Implementation for DELETE /daily-watch-data/id
});



module.exports = router;
