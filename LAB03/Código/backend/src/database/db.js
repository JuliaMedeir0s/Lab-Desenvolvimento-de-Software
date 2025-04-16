const sequelize = require('sequelize');
require('dotenv').config();


const database = new sequelize(
    'moeda_estudantil',
    'postgres',
    'gustana2810',
    {
        host: 'localhost',
        port: 5432,
        dialect: 'postgres',
        logging: false
    }
)

module.exports = database;