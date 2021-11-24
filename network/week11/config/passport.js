var passport = require('passport');
var GoogleStrategy = require('passport-google-oauth2').Strategy;

passport.serializeUser(function(user, done) {
			done(null, user.id);
});
passport.deserializeUser(function(user, done) {
			done(null, user);
});

passport.use(new GoogleStrategy(
	{	
		clientID : '605741326236-4hgumkj4f49cvli878e1qnqe2bcj8dtd.apps.googleusercontent.com',
		clientSecret : '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',
		callbackURL : '/auth/google/callback',
		passReqToCallback : true
	}, function(request, accessToken, refreshToken, profile, done){
		console.log('profile: ', profile);
		var user = profile;
		
		done(null, user);
	}
));
	
module.exports = passport;
