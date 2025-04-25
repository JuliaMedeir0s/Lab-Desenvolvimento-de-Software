const vantagem = require('../models/vantagem.js');

// Função para criar uma nova vantagem
exports.createVantagem =  (req, res, next) => {
    const newVantagem = req.body;
    vantagem.create(newVantagem).then(vantagem => {
        res.status(201).send(vantagem);
    }).catch(err => {
        next(err);
    });
}

// Função para listar todas as vantagens
exports.listVantagens = (req, res, next) => {
    vantagem.findAll().then(vantagens => {
        res.status(200).send(vantagens);
    }).catch(err => {
        next(err);
    });
}

// Função para listar uma vantagem por ID
exports.listVantagemById = (req, res, next) => {
    const id = req.params.id;
    vantagem.findByPk(id).then(vantagem => {
        if (vantagem) {
            res.status(200).send(vantagem);
        } else {
            res.status(404).send(`Vantagem com id ${id} não encontrada.`);
        }
    }).catch(err => {
        next(err);
    });
}

// Função para atualizar uma vantagem
exports.updateVantagem = (req, res, next) => {
    const id = req.params.id;
    const newVantagem = req.body;
    vantagem.findByPk(id).then(vantagem => {
        if (!vantagem) {
            res.status(404).send(`Vantagem com id ${id} não encontrada.`);
        } else {
            vantagem.update(newVantagem).then(() => {
                res.status(200).send(vantagem);
            }).catch(err => {
                next(err);
            });
        }
    }).catch(err => {
        next(err);
    });
}
