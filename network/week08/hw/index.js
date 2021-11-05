var express = require('express');
var bodyParser = require('body-parser');
var methodOverride = require('method-override');
var app = express();
var js = require('fs');
const port = 3000

const homeRouter = require('./routes/home');

app.set('view engine', 'ejs');
app.set('views', __dirname + '/views');
app.use(express.static(__dirname+'/public'));

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:true}));
app.use(methodOverride('__method'));

app.use('/', homeRouter);

app.listen(port, () => {
        console.log('Example app listening at http://localhost:'+port);
});

