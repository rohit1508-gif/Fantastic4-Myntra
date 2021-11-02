require('dotenv').config();
const express = require('express');
const http = require('http');
const pool = require('./config/database');


const app = express();
//const routes = require('./router');

const PORT = 3300;
const server = http.createServer(app);

app.get('/', (req, res) =>{
    res.send('Hello World!!');
})

app.get('/quiz', (req, res) => {
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

app.get('/leaderboard', (req, res) => {
     
    pool.query("SELECT * FROM users", function (err, result, fields) {
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

app.get('/quiz/game', (req, res) => {

    let quiz = [];

    pool.query("SELECT * FROM Questions", function (err, result, fields) {
        if (err) throw err;
        // console.log(result);
        while(quiz.length<5){
            var r = Math.floor(Math.random() * result.length);
            if(quiz.indexOf(r) === -1) quiz.push(result[r])
        }
        console.log("quiz is here", quiz);
        res.send(quiz);
      })
    
})
app.use(express.json());
//app.use('/api', routes); 

server.listen(PORT, () => {
    console.log('Server up and running on ', PORT);

});