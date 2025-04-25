const parceiro = require('../models/parceiro');

//Criar parceiro
exports.create = (req, res, next) => {
    const newParceiro = req.body;
    parceiro.create(newParceiro).then(parceiro => {
        res.status(201).send(parceiro);
    }).catch(err => {
        next(err);
    });
}

//Listar parceiro
exports.list = (req, res, next) => {
    parceiro.findAll().then(parceiros => {
        res.status(200).send(parceiros);
    }).catch(err => {
        next(err);
    });
}

//Listar parceiro por id    
exports.listById = (req, res, next) => {
    const id = req.params.id;
    parceiro.findByPk(id).then(parceiro => {
        if (parceiro) {
            res.status(200).send(parceiro);
        } else {
            res.status(404).send(`Parceiro com id ${id} não encontrado.`);
        }
    }).catch(err => {
        next(err);
    });
}

//Atualizar parceiro
exports.update = (req, res, next) => {
    const id = req.params.id;
    const newParceiro = req.body;
    parceiro.findByPk(id).then(parceiro => {
        if (!parceiro) {
            res.status(404).send(`Parceiro com id ${id} não encontrado.`);
        } else {
            parceiro.update(newParceiro).then(() => {
                res.status(200).send(parceiro);
            }).catch(err => {
                next(err);
            });
        }
    }).catch(err => {
        next(err);
    });
}

//Deletar parceiro
exports.delete = (req, res, next) => {
    const id = req.params.id;
    parceiro.findByPk(id).then(parceiro => {
        if (parceiro) {
            parceiro.destroy().then(() => {
                res.status(200).send(`Parceiro com id ${id} deletado.`);
            }).catch(err => {
                next(err);
            });
        } else {
            res.status(404).send(`Parceiro com id ${id} não encontrado.`);
        }
    }).catch(err => {
        next(err);
    });
}

