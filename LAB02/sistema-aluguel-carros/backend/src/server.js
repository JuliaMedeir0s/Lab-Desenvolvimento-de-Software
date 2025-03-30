
require('dotenv').config();
const express = require('express');
const cors = require('cors');
const app = express();

const PORT = 3000;

const usuarioRoute = require('./routes/usuarios');
const enderecoRouter = require('./routes/enderecos');
const empregoRouter = require('./routes/empregos');
const veiculoRouter = require('./routes/veiculos');
const pedidosRouter = require('./routes/pedidos');
const authRouter = require('./routes/auth');
const authMiddleware = require('./middlewares/auth'); 

app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use('/auth', authRouter);

app.use('/usuario', authMiddleware, usuarioRoute);
app.use('/endereco', enderecoRouter);
app.use('/emprego', empregoRouter);
app.use('/veiculo', authMiddleware, veiculoRouter);
app.use('/pedido', authMiddleware, pedidosRouter);
app.use('/auth', authRouter);


const syncDataBase = async () => {
    const database = require('./database/db')

    try{
        await database.sync();
        console.log('Conexão com o banco de dados estabelecida.');
    } catch(err){
        console.error('Erro ao conectar com o banco de dados:', err);
    }
}

function onStat(){
    syncDataBase();
    console.log(`Servidor rodando na porta ${PORT}`);
}

app.listen(PORT, onStat);

module.exports = app;
