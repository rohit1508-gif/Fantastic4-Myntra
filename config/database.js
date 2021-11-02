const { createPool } = require('mysql');

const pool = createPool({
    port: '3306',
    host: 'beqdfetas6as1orjunp2-mysql.services.clever-cloud.com',
    user: 'uktonoxzm21uepge',
    password: 'K2rkHRTBkmWiAh72Kkiv',
    database: 'beqdfetas6as1orjunp2',
    connectionLimit: '10',
});

pool.getConnection((err, connection) => {
    if (err) {
        throw err;
    
    }
    else {
        console.log('MySQL connected...');
    }
    connection.release();
    return;
});

module.exports = pool;