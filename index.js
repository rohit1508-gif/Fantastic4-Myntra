require('dotenv').config();
const express = require('express');
const http = require('http');
const pool = require('./config/database');
var flash             = require('connect-flash');
var crypto            = require('crypto');
var passport          = require('passport');
var LocalStrategy     = require('passport-local').Strategy;
var sess              = require('express-session');
var Store             = require('express-session').Store;
var BetterMemoryStore = require(__dirname + '/memory');

const app = express();
//const routes = require('./router');

const PORT = process.env.PORT || 3300;
const server = http.createServer(app);


var store = new BetterMemoryStore({ expires: 60 * 60 * 1000, debug: true });

app.use(sess({

   name: 'JSESSION',

   secret: 'MYSECRETISVERYSECRET',

   store:  store,

   resave: true,

   saveUninitialized: true

}));

app.use(flash());

app.use(passport.initialize());

app.use(passport.session());

passport.use('local', new LocalStrategy({

    usernameField: 'username',
  
    passwordField: 'password',
  
    passReqToCallback: true //passback entire req to call back
  } , function (req, username, password, done){
  
  
        if(!username || !password ) { return done(null, false, req.flash('message','All fields are required.')); }
  
        var salt = '7fa73b47df808d36c5fe328546ddef8b9011b2c6';
        pool.connect(function(err) {
            if (err) throw err;
        pool.query("select * from users where username = ?", [username], function(err, rows){
  
            console.log(err); console.log(rows);
  
          if (err) return done(req.flash('message',err));
  
          if(!rows.length){ return done(null, false, req.flash('message','Invalid username or password.')); }
  
          salt = salt+''+password;
  
          var encPassword = crypto.createHash('sha1').update(salt).digest('hex');
  
  
          var dbPassword  = rows[0].password;
  
          if(!(dbPassword == encPassword)){
  
              return done(null, false, req.flash('message','Invalid username or password.'));
  
           }
  
          return done(null, rows[0]);
  
        });
    })
      }
  
  ));

  passport.serializeUser(function(user, done){

    done(null, user.id);

});

passport.deserializeUser(function(id, done){
    pool.connect(function(err) {
        if (err) throw err;
    pool.query("select * from tbl_users where id = "+ id, function (err, rows){

        done(err, rows[0]);

    });
  })
});

app.get('/', isAuthenticated, (req, res) =>{
    res.send('Hello World!!');
})

app.get('/signin', function(req, res){
    res.render('logged in');
  });

app.post("/signin", passport.authenticate('local', {

    successRedirect: '/',

    failureRedirect: '/signin',

    failureFlash: true

}), function(req, res, info){

    res.render('login');

});

app.get('/quiz',isAuthenticated, (req, res) => {
    pool.query("SELECT * FROM Quiz", function (err, result, fields) {
        if (err) throw err;
        res.send(result);
      })
    res.redirect('/quiz/game');
})

app.post('/quiz/game/:id/return', (req, res) => {
    
    pool.connect(function(err) {
        let score = req.body.score;
        if (err) throw err;
        var id = req.params.id;
        pool.connect(function(err) {
            if (err) throw err;
            var sql = "UPDATE users SET score = ? WHERE user_id = ?"+ (score, id);
            pool.query(sql, function (err, result) {
              if (err) throw err;
              console.log(result.affectedRows + " record(s) updated");
            });
          });
    })
})

app.get('/leaderboard',isAuthenticated, (req, res) => {
     
    pool.query("SELECT * FROM users order by score desc", function (err, result, fields) {
        if (err) throw err;
        res.send(result);
      })
})

app.get('/quiz/game/return', (req, res) => {
    pool.query("SELECT score FROM users", function (err, result, fields) {
        if (err) throw err;
        res.send(score);
      })
})

app.get('/quiz/:id/game',isAuthenticated, (req, res) => {

    let quiz = [];
    let id = req.params.id;
    if(id==1){
    pool.query("SELECT * FROM Questions", function (err, result, fields) {
        if (err) throw err;
        // console.log(result);
        while(quiz.length<5){
            var r = Math.floor(Math.random() * result.length);
            if(quiz.indexOf(r) === -1) quiz.push(result[r])
            console.log(r);
        }
        console.log("quiz is here", quiz);
        res.send(quiz);
      })
    }
    else if(id==2){
        pool.query("SELECT * FROM Questions_2", function (err, result, fields) {
            if (err) throw err;
            // console.log(result);
            while(quiz.length<5){
                var r = Math.floor(Math.random() * result.length);
                if(quiz.indexOf(r) === -1) quiz.push(result[r])
                console.log(r);
            }
            console.log("quiz is here", quiz);
            res.send(quiz);
          })
    }
    
})

function isAuthenticated(req, res, next) {

    if (req.isAuthenticated())
  
      return next();
  
    res.redirect('/signin');
  
  }



app.use(express.json());
//app.use('/api', routes); 

server.listen(PORT, () => {
    console.log('Server up and running on ', PORT);

<<<<<<< HEAD
});
=======
server.listen(PORT,  function() {
    console.log("here is the port");
    console.log("portttttttt"+ PORT);
    console.log("Listening on Port 3300");
});
>>>>>>> 6ece9b79521183e11b383e766f6e0a2ea7175f7d
