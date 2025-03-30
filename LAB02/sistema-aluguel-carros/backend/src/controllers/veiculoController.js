const veiculo = require('../models/veiculo');
const jwt = require('jsonwebtoken');
const usuario = require('../models/usuario');
const SECRET_KEY = 'your_secret_key'; // Replace with a secure key

// Middleware to verify JWT token
exports.verifyToken = (req, res, next) => {
    const token = req.headers['authorization'];
    if (!token) {
        return res.status(403).send('Token não fornecido.');
    }
    jwt.verify(token, SECRET_KEY, (err, decoded) => {
        if (err) {
            return res.status(401).send('Token inválido.');
        }
        req.userId = decoded.id;
        next();
    });
};

//criar um veiculo
exports.create = (req, res, next) => {
    const token = req.headers['authorization'];
    if (!token) {
        return res.status(403).send('Token não fornecido.');
    }
    jwt.verify(token, SECRET_KEY, async (err, decoded) => {
        if (err) {
            return res.status(401).send('Token inválido.');
        }
        try {
            const user = await usuario.findByPk(decoded.id);
            if (!user || user.tipo !== 'FUNCIONARIO') {
                return res.status(403).send('Acesso negado.');
            }
            const veiculoCriado = await veiculo.create(req.body);
            res.status(200).send(veiculoCriado);
        } catch (error) {
            if (error.name === 'SequelizeUniqueConstraintError') {
                res.status(400).send('Veículo com informações já cadastradas.');
            } else {
                next(error);
            }
        }
    });
};

//buscar todos os veiculos
exports.findAll = (req, res, next) => {
    veiculo.findAll().then(veiculos => {
        if (!veiculos) {
            return res.status(404).send('Veículos não encontrados.');
        }
        res.status(200).send(veiculos);
    }).catch(next);
};

//buscar um veiculo pelo id
exports.findOne = (req, res, next) => {
    const id = req.params.id;
    veiculo.findByPk(id).then(veiculo => {
        if (veiculo) {
            res.status(200).send(veiculo);
        } else {
            res.status(404).send(`Veículo com id ${id} não encontrado.`);
        }
    }).catch(next);
};

//atualizar um veiculo pelo id
exports.update = (req, res, next) => {
    const token = req.headers['authorization'];
    if (!token) {
        return res.status(403).send('Token não fornecido.');
    }
    jwt.verify(token, SECRET_KEY, async (err, decoded) => {
        if (err) {
            return res.status(401).send('Token inválido.');
        }
        try {
            const user = await usuario.findByPk(decoded.id);
            if (!user || user.tipo !== 'FUNCIONARIO') {
                return res.status(403).send('Acesso negado.');
            }
            const id = req.params.id;
            const veiculoEncontrado = await veiculo.findByPk(id);
            if (veiculoEncontrado) {
                await veiculoEncontrado.update(req.body);
                res.status(200).send(veiculoEncontrado);
            } else {
                res.status(404).send(`Veículo com id ${id} não encontrado.`);
            }
        } catch (error) {
            next(error);
        }
    });
};

//deletar um veiculo pelo id
exports.delete = (req, res, next) => {
    const token = req.headers['authorization'];
    if (!token) {
        return res.status(403).send('Token não fornecido.');
    }
    jwt.verify(token, SECRET_KEY, async (err, decoded) => {
        if (err) {
            return res.status(401).send('Token inválido.');
        }
        try {
            const user = await usuario.findByPk(decoded.id);
            if (!user || user.tipo !== 'FUNCIONARIO') {
                return res.status(403).send('Acesso negado.');
            }
            const id = req.params.id;
            const veiculoEncontrado = await veiculo.findByPk(id);
            if (veiculoEncontrado) {
                await veiculoEncontrado.destroy();
                res.status(200).send(veiculoEncontrado);
            } else {
                res.status(404).send(`Veículo com id ${id} não encontrado.`);
            }
        } catch (error) {
            next(error);
        }
    });
};
