var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  var someHTML = "<a href=\"foo\">bar</a>"
  res.send(someHTML);
});


module.exports = router;
