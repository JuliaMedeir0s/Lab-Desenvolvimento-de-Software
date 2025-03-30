const sequelize = require('sequelize');
const database = require('../database/db');

const veiculo = database.define('veiculo', {
    id: {
        type: sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    modelo: {
        type: sequelize.STRING,
        allowNull: false,
    },
    marca: {
        type: sequelize.STRING,
        allowNull: false,
    },
    ano: {
        type: sequelize.INTEGER,
        allowNull: false,
    },
    cor: {
        type: sequelize.STRING,
        allowNull: false,
    },
    placa: {
        type: sequelize.STRING,
        allowNull: false,
        unique: true,
    },
    valor: {
        type: sequelize.FLOAT,
        allowNull: false,
    },
});

module.exports = veiculo;