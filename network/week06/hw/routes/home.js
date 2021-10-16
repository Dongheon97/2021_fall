var express = require('express');
var router = express.Router();

module.exports = function(app, js){

    app.get('/', function(req, res){
            res.render('./home/welcome');
    });

    app.get('/home', function(req, res){
            res.render('./home/welcome');
    });

    app.get('/about', function(req, res){
            res.render('./home/about');
    });
}
