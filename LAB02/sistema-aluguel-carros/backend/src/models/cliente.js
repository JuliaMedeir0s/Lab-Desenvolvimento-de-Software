const { DataTypes } = require('sequelize');
const database = require('../database/db');
const endereco = require('./endereco');
const emprego = require('./emprego');

const cliente = database.define('Cliente', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    nome: {
        type: DataTypes.STRING,
        allowNull: false
    },
    cpf: {
        type: DataTypes.STRING,
        allowNull: false,
        unique: true
    },
    rg: {
        type: DataTypes.STRING,
        allowNull: false
    }
});

cliente.hasMany(emprego,{
    onDelete: 'CASCADE',
    onUpdate: 'CASCADE',
});

cliente.hasOne(endereco, {
    onDelete: 'CASCADE',
    onUpdate: 'CASCADE',
});

module.exports = cliente;
