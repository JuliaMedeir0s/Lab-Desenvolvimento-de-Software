const sequelize = require('sequelize')
const database = require('../database/db')

const Parceiro = database.define('parceiros', {
    id:{
        type: sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    cnpj:{
        type: sequelize.STRING(20),
        allowNull: false,
        unique: true
    }
})

module.exports = Parceiro;