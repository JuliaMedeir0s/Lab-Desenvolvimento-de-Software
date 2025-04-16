const sequelize = require('sequelize')
const database = require('../database/db')

const Aluno = database.define('alunos', {
    id:{
        type: sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    rg:{
        type: sequelize.STRING(20),
        allowNull: false,
        unique: true
    },
    cpf:{
        type: sequelize.STRING(20),
        allowNull: false,
        unique: true
    },
    endereco:{
        type: sequelize.STRING(100),
        allowNull: false
    },
    saldo:{
        type: sequelize.FLOAT,
        allowNull: false,
        defaultValue: 0.0
    },
})

module.exports = Aluno