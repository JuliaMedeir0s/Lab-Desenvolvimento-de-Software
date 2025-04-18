
require('dotenv').config();
const express = require('express');
const cors = require('cors');
const app = express();

const PORT = 3000;

// Rotas
const usuarioRoute = require('./routes/usuarios');
const enderecoRouter = require('./routes/enderecos');
const empregoRouter = require('./routes/empregos');
const veiculoRouter = require('./routes/veiculos');
const pedidosRouter = require('./routes/pedidos');
const authRouter = require('./routes/auth');
const authMiddleware = require('./middlewares/auth');


// Seeds
const popularVeiculos = require('./seeds/seedVeiculos');
const popularCliente = require('./seeds/seedCliente');

app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use('/auth', authRouter);

app.use('/usuario', usuarioRoute);
app.use('/endereco', enderecoRouter);
app.use('/emprego', empregoRouter);
app.use('/veiculo', authMiddleware, veiculoRouter);
app.use('/pedido', authMiddleware, pedidosRouter);

// Inicialização do banco e do servidor
const startApp = async () => {
    const database = require('./database/db');

    try {
        await database.sync({ alter: true });
        console.log('Banco sincronizado com sucesso.');

        app.listen(PORT, () => {
            console.log(`Servidor rodando em http://localhost:${PORT}`);
        });

        await popularVeiculos(); // seed veiculos
        await popularCliente();  // seed cliente

    } catch (err) {
        console.error('Erro ao iniciar servidor:', err);
    }
};

startApp();

module.exports = app;
