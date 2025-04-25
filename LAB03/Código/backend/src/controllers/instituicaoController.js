const instituicao = require('../models/instituicao');

//Criar instituicao
exports.create = (req, res, next) => {
    const newInstituicao = req.body;
    instituicao.create(newInstituicao).then(instituicao => {
        res.status(201).send(instituicao);
    }).catch(err => {
        next(err);
    });
}

//Listar instituicao
exports.list = (req, res, next) => {
    instituicao.findAll().then(instituicoes => {
        res.status(200).send(instituicoes);
    }).catch(err => {
        next(err);
    });
}

//Listar instituicao por id
exports.listById = (req, res, next) => {
    const id = req.params.id;
    instituicao.findByPk(id).then(instituicao => {
        if (instituicao) {
            res.status(200).send(instituicao);
        } else {
            res.status(404).send(`Instituição com id ${id} não encontrada.`);
        }
    }).catch(err => {
        next(err);
    });
}

//Atualizar instituicao
exports.update = (req, res, next) => {
    const id = req.params.id;
    const newInstituicao = req.body;
    instituicao.findByPk(id).then(instituicao => {
        if (!instituicao) {
            res.status(404).send(`Instituição com id ${id} não encontrada.`);
        } else {
            instituicao.update(newInstituicao).then(() => {
                res.status(200).send(instituicao);
            }).catch(err => {
                next(err);
            });
        }
    }).catch(err => {
        next(err);
    });
}

//Deletar instituicao
exports.delete = (req, res, next) => {
    const id = req.params.id;
    instituicao.findByPk(id).then(instituicao => {
        if (instituicao) {
            instituicao.destroy().then(() => {
                res.status(200).send(`Instituição com id ${id} deletada.`);
            }).catch(err => {
                next(err);
            });
        } else {
            res.status(404).send(`Instituição com id ${id} não encontrada.`);
        }
    }).catch(err => {
        next(err);
    });
}

