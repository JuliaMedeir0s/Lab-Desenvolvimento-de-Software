const sequelize = require('sequelize');
const database = require('../database/db');

const Emprego = database.define('emprego', {
    id: {
        type: sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
        allowNull: false
    },
    empresa: {
        type: sequelize.STRING,

    },
    renda: {
        type: sequelize.FLOAT,
    },
});

module.exports = Emprego;
