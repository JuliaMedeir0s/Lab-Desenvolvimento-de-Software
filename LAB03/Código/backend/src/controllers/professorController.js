const professor = require('../models/professor');

//Criar professr
exports.create = (req, res, next) => {
    const newProfessor = req.body;
    professor.create(newProfessor).then(professor => {
        res.status(201).send(professor);
    }).catch(err => {
        next(err);
    });
}

//Listar professor
exports.list = (req, res, next) => {
    professor.findAll().then(professores => {
        res.status(200).send(professores);
    }).catch(err => {
        next(err);
    });
}

//Listar professor por id
exports.listById = (req, res, next) => {
    const id = req.params.id;
    professor.findByPk(id).then(professor => {
        if (professor) {
            res.status(200).send(professor);
        } else {
            res.status(404).send(`Professor com id ${id} não encontrado.`);
        }
    }).catch(err => {
        next(err);
    });
}

//Atualizar professor
exports.update = (req, res, next) => {
    const id = req.params.id;
    const newProfessor = req.body;
    professor.findByPk(id).then(professor => {
        if (!professor) {
            res.status(404).send(`Professor com id ${id} não encontrado.`);
        } else {
            professor.update(newProfessor).then(() => {
                res.status(200).send(professor);
            }).catch(err => {
                next(err);
            });
        }
    }).catch(err => {
        next(err);
    });
}

//Deletar professor
exports.delete = (req, res, next) => {
    const id = req.params.id;
    professor.findByPk(id).then(professor => {
        if (professor) {
            professor.destroy().then(() => {
                res.status(204).send();
            }).catch(err => {
                next(err);
            });
        } else {
            res.status(404).send(`Professor com id ${id} não encontrado.`);
        }
    }).catch(err => {
        next(err);
    });
}

