const sequelize = require('sequelize');
const database = require('../database/db');

const Transacao = database.define('transacoes', {
    id:{
        type: sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    tipo:{
        type: sequelize.ENUM('compra', 'resgate'),
        allowNull: false
    },
    valor:{
        type: sequelize.FLOAT,
        allowNull: false
    },
    mensagem:{
        type: sequelize.STRING(100),
        allowNull: true
    },
    codigo:{
        type: sequelize.STRING(100),
        allowNull: false,
        unique: true
    },
    data:{
        type: sequelize.DATE,
        allowNull: false,
        defaultValue: sequelize.NOW
    },
})

module.exports = Transacao;