require('dotenv').config();
const express = require('express');
const cors = require('cors');
const app = express();

const PORT = 3000;

const alunoRoute = require('./routes/alunos');
const instituicaoRoute = require('./routes/instuicoes');
const professorRoute = require('./routes/professores');
const authRouter = require('./routes/auth');
const authMiddleware = require('./middlewares/auth');
const transacaoRoute = require('./routes/transacoes');
const vantagemRoute = require('./routes/vantagens');

app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use('/auth', authRouter);
app.use('/aluno', alunoRoute);
app.use('/instituicao', instituicaoRoute);
app.use('/professor', professorRoute);
app.use('/transacao', authMiddleware, transacaoRoute);
app.use('/vantagem', authMiddleware, vantagemRoute);

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