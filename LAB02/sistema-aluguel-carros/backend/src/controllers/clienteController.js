const clientes = require('../models/cliente');
const enderecos = require('../models/endereco');
const emprego = require('../models/emprego');

//criar um cliente
exports.create = (req, res, next) => {
    clientes.create(req.body, {
        include: [enderecos, emprego],
    }).then(cliente => {
        res.status(200).send(cliente);
    }).catch(err => {
        if (err.name === 'SequelizeUniqueConstraintError') {
            res.status(400).send(`Cliente com informações já cadastradas.`);
        } else {
            next(err);
        }
    });
}


//buscar todos os clientes
exports.findAll = (req, res, next) => {
    clientes.findAll({
        include: [enderecos, emprego]
    }).then(cliente => {
        res.status(200).send(cliente);
    }).catch(err => {
        next(err);
    });
};


//buscar um cliente pelo id
exports.findOne = async (req, res, next) => {
    const id = req.params.id;
    const cliente = await clientes.findByPk(id, {
        include: [enderecos, emprego]
    }).catch(next);
    if (!cliente) {
        res.status(404).send(`Cliente com id ${id} não encontrado.`);
    } else {
        res.status(200).send(cliente);
    }
};


//atualizar um cliente pelo id
exports.update =  (req, res, next) => {
    const newCliente = req.body;
    const id = req.params.id;

    clientes.findByPk(id, { include: [enderecos, emprego] }).then(cliente => {
        if (cliente) {
            cliente.update(newCliente);
            res.status(200).send(cliente);
        }else {
            res.status(404).send(`Cliente com id ${id} não encontrado.`);
        }
    }).catch(next);
}

//deletar um cliente pelo id
exports.delete = async (req, res, next) => {
    const id = req.params.id;
    clientes.findByPk(id, { include: [enderecos, emprego] }).then(cliente => {
        if (!cliente) {
            res.status(404).send(`Cliente com id ${id} não encontrado.`);
        } else {
            cliente.destroy().then(() => {
                res.status(200).send(cliente);
            }).catch(err => {
                next(err);
            });
        }
    }).catch(err => {
        next(err);
    });
}