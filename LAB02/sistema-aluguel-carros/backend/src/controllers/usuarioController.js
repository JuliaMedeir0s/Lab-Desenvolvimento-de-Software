const usuarios = require('../models/usuario');
const enderecos = require('../models/endereco');
const emprego = require('../models/emprego');

//criar um usuario
exports.create = (req, res, next) => {
    usuarios.create(req.body, {
        include: [enderecos, emprego],
    }).then(usuario => {
        res.status(200).send(usuario);
    }).catch(err => {
        if (err.name === 'SequelizeUniqueConstraintError') {
            res.status(400).send(`usuario com informações já cadastradas.`);
        } else {
            next(err);
        }
    });
}


//buscar todos os usuarios
exports.findAll = (req, res, next) => {
    usuarios.findAll({
        include: [enderecos, emprego]
    }).then(usuario => {
        res.status(200).send(usuario);
    }).catch(err => {
        next(err);
    });
};

//buscar todos os usuarios com o tipo cliente
exports.findAllClientes = (req, res, next) => {
    usuarios.findAll({
        where: { tipo: 'cliente' },
        include: [enderecos, emprego]
    }).then(usuario => {
        res.status(200).send(usuario);
    }).catch(err => {
        next(err);
    });
};

//buscar todos os usuarios com o tipo funcionario
exports.findAllFuncionarios = (req, res, next) => {
    usuarios.findAll({
        where: { tipo: 'funcionario' },
        include: [enderecos, emprego]
    }).then(usuario => {
        res.status(200).send(usuario);
    }).catch(err => {
        next(err);
    });
};



//buscar um usuario pelo id
exports.findOne = async (req, res, next) => {
    const id = req.params.id;
    const usuario = await usuarios.findByPk(id, {
        include: [enderecos, emprego]
    }).catch(next);
    if (!usuario) {
        res.status(404).send(`usuario com id ${id} não encontrado.`);
    } else {
        res.status(200).send(usuario);
    }
};


//atualizar um usuario pelo id
exports.update =  (req, res, next) => {
    const newusuario = req.body;
    const id = req.params.id;

    usuarios.findByPk(id, { include: [enderecos, emprego] }).then(usuario => {
        if (usuario) {
            usuario.update(newusuario);
            res.status(200).send(usuario);
        }else {
            res.status(404).send(`usuario com id ${id} não encontrado.`);
        }
    }).catch(next);
}

//deletar um usuario pelo id
exports.delete = async (req, res, next) => {
    const id = req.params.id;
    usuarios.findByPk(id, { include: [enderecos, emprego] }).then(usuario => {
        if (!usuario) {
            res.status(404).send(`usuario com id ${id} não encontrado.`);
        } else {
            usuario.destroy().then(() => {
                res.status(200).send(usuario);
            }).catch(err => {
                next(err);
            });
        }
    }).catch(err => {
        next(err);
    });
}