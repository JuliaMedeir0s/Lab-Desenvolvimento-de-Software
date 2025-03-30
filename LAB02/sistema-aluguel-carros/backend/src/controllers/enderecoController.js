const enderecos = require('../models/endereco');

exports.delete = (req, res, next) => {
    const id = req.params.id;
    enderecos.findByPk(id).then(enderecos => {
        if (enderecos) {
            enderecos.destroy();
            res.status(200).send(enderecos);
        } else {
            res.status(404).send(`Endereco com id ${id} não encontrado.`);
        }
    }).catch(err => {
        next(err);
    });
}

exports.update = (req, res, next) => {
    const id = req.params.id;
    const newEndereco = req.body;
    enderecos.findByPk(id).then(endereco => {
        if (!endereco) {
            res.status(404).send(`Endereco com id ${id} não encontrado.`);
        } else {
            endereco.update(newEndereco).then(() => {
                res.status(200).send(endereco);
            }).catch(err => {
                next(err);
            });
        }
    }).catch(err => {
        next(err);
    });
}

