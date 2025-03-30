const { DataTypes } = require('sequelize');
const database = require('../database/db');

const Emprego = database.define('Emprego', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true,
        allowNull: false
    },
    empresa: {
        type: DataTypes.STRING,
        allowNull: false
    },
    renda: {
        type: DataTypes.FLOAT,
        allowNull: false
    }
});

module.exports = Emprego;
