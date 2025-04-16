const sequelize = require('sequelize')
const database = require('../database/db')

const professor = database.define('professores', {
    id: {
        type: sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    cpf: {
        type: sequelize.STRING(20),
        allowNull: false,
        unique: true
    },
    departamento: {
        type: sequelize.STRING(100),
        allowNull: false
    },
    saldo: {
        type: sequelize.FLOAT,
        allowNull: false,
        defaultValue: 0.0
    }
})

module.exports = professor;