const usuarios = require('../models/usuario');
const enderecos = require('../models/endereco');
const emprego = require('../models/emprego');
const bcrypt = require('bcryptjs'); // Para criptografar senhas
const jwt = require('jsonwebtoken');
const dotenv = require('dotenv');

dotenv.config();
const SECRET_KEY = process.env.JWT_SECRET;

const verificarToken = (req, res, next) => {
    const token = req.headers.authorization?.split(' ')[1]; 
    if (!token) {
        return res.status(401).send({ message: 'Token não fornecido.' });
    }

    jwt.verify(token, SECRET_KEY, (err, decoded) => {
        if (err) {
            return res.status(401).send({ message: 'Token inválido.' });
        }
        req.userId = decoded.id;
        next();
    });
};

exports.create = async (req, res, next) => {
    try {
        const { senha, ...userData } = req.body;

        const senhaHash = await bcrypt.hash(senha, 10);

        const usuario = await usuarios.create(
            { ...userData, senha: senhaHash }, 
            { include: [enderecos, emprego] }
        );

        res.status(201).send({ message: 'Usuário criado com sucesso!', usuario });
    } catch (err) {
        if (err.name === 'SequelizeUniqueConstraintError') {
            res.status(400).send({ message: 'Usuário com informações já cadastradas.' });
        } else {
            next(err);
        }
    }
};

exports.findAll = [verificarToken, async (req, res, next) => {
    try {
        const usuario = await usuarios.findAll({ include: [enderecos, emprego] });
        if (!usuario.length) {
            return res.status(404).send({ message: 'Nenhum usuário encontrado.' });
        }
        res.status(200).send(usuario);
    } catch (err) {
        next(err);
    }
}];

exports.findAllClientes = [verificarToken, async (req, res, next) => {
    try {
        const clientes = await usuarios.findAll({ where: { tipo: 'cliente' }, include: [enderecos, emprego] });
        res.status(200).send(clientes);
    } catch (err) {
        next(err);
    }
}];

exports.findAllFuncionarios = [verificarToken, async (req, res, next) => {
    try {
        const funcionarios = await usuarios.findAll({ where: { tipo: 'funcionario' }, include: [enderecos, emprego] });
        res.status(200).send(funcionarios);
    } catch (err) {
        next(err);
    }
}];

exports.findOne = [verificarToken, async (req, res, next) => {
    try {
        const id = req.params.id;

        if (req.userId !== id) {
            return res.status(403).send({ message: 'Acesso negado.' });
        }

        const usuario = await usuarios.findByPk(id, { include: [enderecos, emprego] });

        if (!usuario) {
            return res.status(404).send({ message: `Usuário com id ${id} não encontrado.` });
        }

        res.status(200).send(usuario);
    } catch (err) {
        next(err);
    }
}];

exports.update = [verificarToken, async (req, res, next) => {
    try {
        const id = req.params.id;

        if (req.userId !== id) {
            return res.status(403).send({ message: 'Você não tem permissão para alterar este usuário.' });
        }

        const usuario = await usuarios.findByPk(id, { include: [enderecos, emprego] });

        if (!usuario) {
            return res.status(404).send({ message: `Usuário com id ${id} não encontrado.` });
        }

        if (req.body.senha) {
            req.body.senha = await bcrypt.hash(req.body.senha, 10);
        }

        await usuario.update(req.body);
        res.status(200).send({ message: 'Usuário atualizado com sucesso!', usuario });

    } catch (err) {
        next(err);
    }
}];

exports.delete = [verificarToken, async (req, res, next) => {
    try {
        const id = req.params.id;

        if (req.userId !== id) {
            return res.status(403).send({ message: 'Você não tem permissão para excluir este usuário.' });
        }

        const usuario = await usuarios.findByPk(id, { include: [enderecos, emprego] });

        if (!usuario) {
            return res.status(404).send({ message: `Usuário com id ${id} não encontrado.` });
        }

        await usuario.destroy();
        res.status(200).send({ message: 'Usuário excluído com sucesso!' });

    } catch (err) {
        next(err);
    }
}];
