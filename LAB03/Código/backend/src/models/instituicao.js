const sequelize = require('sequelize')
const database = require('../database/db')

const Instituicao = database.define('instituicoes', {
    id:{
        type: sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    nome:{
        type: sequelize.STRING(100),
        allowNull: false,
        unique: true
    },
})

module.exports = Instituicao