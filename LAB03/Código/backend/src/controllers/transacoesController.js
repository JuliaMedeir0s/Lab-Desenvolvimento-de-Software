const transacao = require('../models/transacoes');

//criar transacao
exports.create = (req, res, next) => {
    const newTransacao = req.body;
    transacao.create(newTransacao).then(transacao => {
        res.status(201).send(transacao);
    }
    ).catch(err => {
        next(err);
    });
}

// update transacao
exports.update = (req, res, next) => {
    const id = req.params.id;
    const newTransacao = req.body;
    transacao.findByPk(id).then(transacao => {
        if (!transacao) {
            res.status(404).send(`Transação com id ${id} não encontrada.`);
        } else {
            transacao.update(newTransacao).then(() => {
                res.status(200).send(transacao);
            }).catch(err => {
                next(err);
            });
        }
    }).catch(err => {
        next(err);
    });
}

//buscar transacao por id
exports.listById = (req, res, next) => {
    const id = req.params.id;
    transacao.findByPk(id).then(transacao => {
        if (transacao) {
            res.status(200).send(transacao);
        }
        else {
            res.status(404).send(`Transação com id ${id} não encontrada.`);
        }
    }
    ).catch(err => {
        next(err);
    });
}

//delete transacao
exports.delete = (req, res, next) => {
    const id = req.params.id;
    transacao.findByPk(id).then(transacao => {
        if (transacao) {
            transacao.destroy().then(() => {
                res.status(200).send(`Transação com id ${id} deletada.`);
            }).catch(err => {
                next(err);
            });
        } else {
            res.status(404).send(`Transação com id ${id} não encontrada.`);
        }
    }
    ).catch(err => {
        next(err);
    });
}

