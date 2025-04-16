const sequelize = require('sequelize');
const database = require('../database/db');

const Vantagem = database.define('vantagens', {
    id: {
        type: sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    nome:{
        type: sequelize.STRING(100),
        allowNull: false,
        unique: true
    },
    descricao: {
        type: sequelize.STRING(100),
        allowNull: false
    },
    valor: {
        type: sequelize.FLOAT,
        allowNull: false
    },
    imagem:{
        type: sequelize.STRING(100),
        allowNull: true
    }
})

module.exports = Vantagem