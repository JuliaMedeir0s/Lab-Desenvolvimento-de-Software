const { DataTypes } = require('sequelize');
const database = require('../database/db');
const endereco = require('./endereco');
const emprego = require('./emprego');
const bcrypt = require('bcryptjs');

const usuario = database.define('Usuario', {
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
    }, 
    email: {
        type: DataTypes.STRING,
        allowNull: false,
        unique: true
    }, 
    senha: {
        type: DataTypes.STRING,
        allowNull: false
    },
    tipo: {
        type: DataTypes.ENUM('CLIENTE', 'FUNCIONARIO'),
        allowNull: false
    },
});

usuario.hasMany(emprego, {
    onDelete: 'CASCADE',
    onUpdate: 'CASCADE',
    allowNull: true,
});

usuario.hasOne(endereco, {
    onDelete: 'CASCADE',
    onUpdate: 'CASCADE',
    allowNull: true
});

usuario.beforeCreate(async (usuario) => {
    usuario.senha = await bcrypt.hash(usuario.senha, 10);
});

usuario.beforeUpdate(async (usuario) => {
    if (usuario.changed('senha')) {
        usuario.senha = await bcrypt.hash(usuario.senha, 10);
    }
});

module.exports = usuario;
