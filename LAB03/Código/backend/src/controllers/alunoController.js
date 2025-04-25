const aluno = require('../models/aluno');

//criar aluno
exports.create = (req, res, next) => {
    const newAluno = req.body;
    aluno.create(newAluno).then(aluno => {
        res.status(201).send(aluno);
    }).catch(err => {
        next(err);
    });
}

//listar aluno
exports.list = (req, res, next) => {
    aluno.findAll().then(alunos => {
        res.status(200).send(alunos);
    }).catch(err => {
        next(err);
    });
}

//listar aluno por id
exports.listById = (req, res, next) => {
    const id = req.params.id;
    aluno.findByPk(id).then(aluno => {
        if (aluno) {
            res.status(200).send(aluno);
        } else {
            res.status(404).send(`Aluno com id ${id} não encontrado.`);
        }
    }).catch(err => {
        next(err);
    });
}

//atualizar aluno
exports.update = (req, res, next) => {
    const id = req.params.id;
    const newAluno = req.body;
    aluno.findByPk(id).then(aluno => {
        if (!aluno) {
            res.status(404).send(`Aluno com id ${id} não encontrado.`);
        } else {
            aluno.update(newAluno).then(() => {
                res.status(200).send(aluno);
            }).catch(err => {
                next(err);
            });
        }
    }).catch(err => {
        next(err);
    });
}

//deletar aluno
exports.delete = (req, res, next) => {
    const id = req.params.id;
    aluno.findByPk(id).then(aluno => {
        if (aluno) {
            aluno.destroy();
            res.status(200).send(aluno);
        } else {
            res.status(404).send(`Aluno com id ${id} não encontrado.`);
        }
    }).catch(err => {
        next(err);
    });
}
