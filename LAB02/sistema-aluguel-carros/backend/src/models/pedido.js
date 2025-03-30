const sequelize = require('sequelize');
const database = require('../database/db');
const veiculo = require('./veiculo');
const usuario = require('./usuario');

const pedido = database.define('Pedido', {
    id: {
        type: sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    data_inicio: {
        type: sequelize.DATE,
        allowNull: false
    },
    data_fim: {
        type: sequelize.DATE,
        allowNull: false
    },
    observacao: {
        type: sequelize.STRING,
        allowNull: true
    }
});

pedido.belongsTo(veiculo, {
    onDelete: 'CASCADE',
    onUpdate: 'CASCADE'
});

pedido.belongsTo(usuario, {
    onDelete: 'CASCADE',
    onUpdate: 'CASCADE'
});

module.exports = pedido;