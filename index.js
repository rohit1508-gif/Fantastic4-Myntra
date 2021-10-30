require('dotenv').config();
const express = require('express');
const http = require('http');

const app = express();
//const routes = require('./router');

const PORT = 3300;
const server = http.createServer(app);



app.use(express.json());
//app.use('/api', routes);

server.listen(PORT, () => {
    console.log('Server up and running on ', PORT);

});