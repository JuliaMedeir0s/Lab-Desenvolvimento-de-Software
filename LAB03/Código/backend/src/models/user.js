const sequelize = require('sequelize');
const database = require('../database/db');

const User = database.define('users', {
    id: {
        type: sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    name: {
        type: sequelize.STRING(50),
        allowNull: false
    },
    email: {
        type: sequelize.STRING(100),
        allowNull: false,
        unique: true
    },
    password: {
        type: sequelize.STRING(100),
        allowNull: false
    },
    tipoUsuario: {
        type: sequelize.ENUM('aluno', 'professor', 'parceiro'),
    }
});
module.exports = User;