require('dotenv').config();
const express = require('express');
const http = require('http');

const app = express();
//const routes = require('./router');

const PORT = 3300;
const server = http.createServer(app);

app.get('/', (req, res) => {
    res.send('Hello World!');
  });
