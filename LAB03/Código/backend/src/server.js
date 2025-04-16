require('dotenv').config();
const express = require('express');
const cors = require('cors');
const app = express();

const PORT = 3000;

app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

const startApp = async () => {
    const database = require('./database/db');

    try {
        await database.sync({ alter: true });
        console.log('Banco sincronizado com sucesso.');

        app.listen(PORT, () => {
            console.log(`Servidor rodando em http://localhost:${PORT}`);
        });

    } catch (err) {
        console.error('Erro ao iniciar servidor:', err);
    }
};

startApp();

module.exports = app;