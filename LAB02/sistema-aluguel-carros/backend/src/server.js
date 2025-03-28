const express = require('express');
const cors = require('cors');
const app = express();

const PORT = 3000;

const clienteRouter = require('./routes/clientes');
//const enderecoRouter = require('./routes/endereco');
//const empregoRouter = require('./routes/emprego');

app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use('/clientes', clienteRouter);
//app.use('/endereco', enderecoRouter);
//app.use('/emprego', empregoRouter);

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
