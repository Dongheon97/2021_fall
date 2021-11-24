var express = require('express');
var app = express();
var passport = require('passport');
var session = require('express-session');
var http = require('http');
var https = require('https');
var fs = require('fs');

var options = {
	key: fs.readFileSync('./key.pem'),
	cert: fs.readFileSync('./cert.pem')
};

app.set('view engine', 'ejs');
app.set('views', __dirname + '/views');
app.use(express.static(__dirname+'/public'));

app.use(session({
	secret:'MySecret', 
	resave: false, 
	saveUninitialized:true
}));

// Passport setting
app.use(passport.initialize());
app.use(passport.session());

// Routes
app.use('/', require('./routes/main'));
app.use('/auth', require('./routes/auth'));

/*
// HTTPS Port Setting
https.createServer(options, (req, res) => {
		console.log('Server On! https://localhost:443');
		//res.end('secure');
}).listen(443);
*/
//http.createServer(app).listen(3000);

https.createServer(options, app).listen(443);

/*
// Port setting
var port = 3000;
app.listen(port, () => {
		console.log('server on! http://localhost:'+port);
});
*/
