var express = require('express');
var router = express.Router();

router.get('/', function(req, res, next){
        res.render('./home/welcome', { title: 'Express' });
});

router.get('/home', function(req, res, next) {
        res.render('./home/welcome', {title: 'Express'});
});

router.get('/about', function(req, res) {
        res.render('./home/about', {title: 'Express'});
});

module.exports = router;
