const sequelize = require('sequelize');
const database = require('../database/db');
const Endereco = require('./endereco');
const Emprego = require('./emprego');

const Cliente = database.define('cliente', {
    id: {
        type: sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    nome: {
        type: sequelize.STRING,
        allowNull: false
    },
    cpf: {
        type: sequelize.STRING,
        allowNull: false
    },
    rg:{
        type: sequelize.STRING,
        allowNull: false
    },
    empregos:{
        type: sequelize.STRING,
        allowNull: false
    },
});

Cliente.hasOne(Endereco,{
    onDelete: 'CASCADE',
    onUpdate: 'CASCADE',
    allowNull: false
});

Cliente.hasMany(Emprego, { as: 'historicoEmpregos', foreignKey: 'clienteId' });

module.exports = Cliente;