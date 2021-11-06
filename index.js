require('dotenv').config();
const express = require('express');
const http = require('http');
const pool = require('./database');
var session = require('express-session');
var bodyParser = require('body-parser');
var path = require('path');
// var BetterMemoryStore = require(__dirname + '/memory');


var app = express();


// app.use(express.json());
// app.use(express.urlencoded());

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.use(session({
    secret: 'secret',
    resave: true,
    saveUninitialized: true
}));
// app.use(require('connect').bodyParser());



//const routes = require('./router');

const PORT = process.env.PORT || 3300;
const server = http.createServer(app);


app.get('/',  (req, res) =>{
    res.send('Hello World!!');
})


app.get('/quiz', (req, res) => {
    pool.query("SELECT * FROM Quiz", function (err, result, fields) {
        if (err) throw err;
        res.send(result);
      })
<<<<<<< HEAD
    // res.redirect('/quiz/game');
=======
   
>>>>>>> dd0cdd315de49ac0519c8377b5501986f7f9b3ea
})

app.put('/quiz/game/:id/return', (req, res) => {
    
    
        let score = req.body.score;
        console.log(score);
        var id = req.params.id;
        pool.getConnection(function(err) {
            if (err) throw err;
            var sql = "UPDATE users SET score = ? WHERE user_id = ?";
            pool.query(sql, [score, id] ,function (err, result) {
              if (err) throw err;
              console.log(result.affectedRows + " record(s) updated");
            });
          });
    })

app.get('/leaderboard', (req, res) => {
     
    pool.query("SELECT * FROM users", function (err, result, fields) {
        if (err) throw err;
        res.send(result);
      })
})

// app.get('/quiz/game/return', (req, res) => {
//     pool.query("SELECT score FROM users", function (err, result, fields) {
//         if (err) throw err;
//         res.send(score);
//       })
// })

app.get('/quiz/:id/game', (req, res) => {

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

app.get('/login', function(request, response) {
    if (request.session.loggedin) {
        response.send('Welcome back, ' + request.session.username + '!');
    } else {
        response.send('Please login to view this page!');
    }
    response.end();
});

app.post('/login',  (request, response) =>{
    console.log(request.body);
    let username = request.body.username;
    let password = request.body.password;
    console.log(username);
    console.log(password);
        if (username && password) {
        pool.query('SELECT * FROM users WHERE username = ? AND password = ?', [username, password], function(error, results, fields) {
            console.log(username);
            if (results.length > 0) {
                request.session.loggedin = true;
                request.session.username = username;
                console.log(username);
                response.redirect('/');
            } else {
                response.send('Incorrect Username and/or Password!');
            }           
            response.end();
        });
    } else {
        response.send('Please enter Username and Password!');
        response.end();
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

});