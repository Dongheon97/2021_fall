var express = require('express');
var router = express.Router();

router.get('/', (req, res) => {
  res.render('hue_controller');
});

var Hue = require('philips-hue');
var hue = new Hue();

hue.bridge = "192.168.1.18";
hue.username = "x6AoKBiqgtpEtbPPt6o5xxpOQQwvINLs2dx0Oqnj";

router.post('/huecontrol', (req, res) => {
  try{
    if(req.body.power == "On"){
      console.log("On")
      hue.light(req.body.hueid).on().then(setState(
        {bri: parseInt(req.body.brightness), 
         sat: parseInt(req.body.saturation), 
         hue: parseInt(req.body.hue)})).catch(console.error);
    }
    if(req.body.power == "Off"){
      console.log("Off");
      hue.light(req.body.hueid).off();
    }
  } catch (error){
    console.log(error);
  }
  res.render('hue_controller'); 
});

module.exports = router;
