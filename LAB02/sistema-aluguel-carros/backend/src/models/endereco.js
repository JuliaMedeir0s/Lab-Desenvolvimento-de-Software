const sequelize = require('sequelize');
const database = require('../database/db');

const Endereco = database.define('endereco', {
    id: {
        type: sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    rua: {
        type: sequelize.STRING,
        allowNull: false
    },
    numero: {
        type: sequelize.INTEGER,
        allowNull: false
    },
    bairro: {
        type: sequelize.STRING,
        allowNull: false
    },
    cidade: {
        type: sequelize.STRING,
        allowNull: false
    },
    estado: {
        type: sequelize.STRING,
        allowNull: false
    },
    cep: {
        type: sequelize.STRING,
        allowNull: false
    }
});

module.exports = Endereco;