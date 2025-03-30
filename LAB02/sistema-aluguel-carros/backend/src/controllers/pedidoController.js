const pedidos = require('../models/pedido');
const veiculos = require('../models/veiculo');
const usuarios = require('../models/usuario');
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

// Criar um pedido
exports.create = [verificarToken, async (req, res, next) => {
    try {
        const { veiculoId, clienteId, ...pedidoData } = req.body;

        if (req.userId !== clienteId) {
            return res.status(403).send({ message: 'Você não tem permissão para criar este pedido.' });
        }

        const pedido = await pedidos.create(
            { veiculoId, clienteId, ...pedidoData },
            { include: [veiculos, usuarios] }
        );

        res.status(201).send({ message: 'Pedido criado com sucesso!', pedido });
    } catch (err) {
        if (err.name === 'SequelizeUniqueConstraintError') {
            res.status(400).send({ message: 'Pedido com informações já cadastradas.' });
        } else {
            next(err);
        }
    }
}];


exports.update = [verificarToken, (req, res, next) => {
    const id = req.params.id;
    const newPedido = req.body;

    pedidos.findByPk(id).then(pedido => {
        if (!pedido) {
            return res.status(404).send('Pedido não encontrado.');
        }

        // Verifica se o usuário autenticado é o cliente associado ao pedido
        if (req.userId !== pedido.clienteId) {
            return res.status(403).send({ message: 'Você não tem permissão para atualizar este pedido.' });
        }

        pedido.update(newPedido).then(() => {
            res.status(200).send(pedido);
        }).catch(err => {
            next(err);
        });
    }).catch(err => {
        next(err);
    });
}];

// Deletar um pedido com JWT
exports.delete = [verificarToken, async (req, res, next) => {
    try {
        const id = req.params.id;

        const pedido = await pedidos.findByPk(id);
        if (!pedido) {
            return res.status(404).send('Pedido não encontrado.');
        }

        // Verifica se o usuário autenticado é o cliente associado ao pedido
        if (req.userId !== pedido.clienteId) {
            return res.status(403).send({ message: 'Você não tem permissão para deletar este pedido.' });
        }

        await pedido.destroy();
        res.status(200).send(pedido);
    } catch (err) {
        next(err);
    }
}];


// Buscar todos os pedidos
exports.findAll = [verificarToken, async (req, res, next) => {
    try {
        const pedidosList = await pedidos.findAll({
            include: [veiculos, usuarios]
        });

        if (!pedidosList || pedidosList.length === 0) {
            return res.status(404).send('Pedidos não encontrados.');
        }

        // Filtrar pedidos para retornar apenas os associados ao usuário autenticado, se for CLIENTE
        const usuario = await usuarios.findByPk(req.userId);
        if (usuario.tipo === 'CLIENTE') {
            const pedidosCliente = pedidosList.filter(pedido => pedido.clienteId === req.userId);
            return res.status(200).send(pedidosCliente);
        }

        // Se for FUNCIONARIO, retorna todos os pedidos
        res.status(200).send(pedidosList);
    } catch (err) {
        next(err);
    }
}];

// Buscar um pedido pelo id
exports.findOne = [verificarToken, async (req, res, next) => {
    try {
        const id = req.params.id;
        const pedido = await pedidos.findByPk(id, {
            include: [veiculos, usuarios]
        });

        if (!pedido) {
            return res.status(404).send(`Pedido com id ${id} não encontrado.`);
        }

        // Verifica se o usuário autenticado é o cliente associado ao pedido
        const usuario = await usuarios.findByPk(req.userId);
        if (usuario.tipo === 'CLIENTE' && req.userId !== pedido.clienteId) {
            return res.status(403).send({ message: 'Você não tem permissão para acessar este pedido.' });
        }

        res.status(200).send(pedido);
    } catch (err) {
        next(err);
    }
}];

// Buscar todos os pedidos de um cliente
exports.findAllByCliente = [verificarToken, async (req, res, next) => {
    try {
        const clienteId = req.params.clienteId;

        // Verifica se o usuário autenticado é o cliente associado ao pedido
        if (req.userId !== parseInt(clienteId)) {
            return res.status(403).send({ message: 'Você não tem permissão para acessar os pedidos deste cliente.' });
        }

        const pedidosCliente = await pedidos.findAll({
            where: { clienteId: clienteId },
            include: [veiculos, usuarios]
        });

        if (!pedidosCliente || pedidosCliente.length === 0) {
            return res.status(404).send(`Pedidos do cliente com id ${clienteId} não encontrados.`);
        }

        res.status(200).send(pedidosCliente);
    } catch (err) {
        next(err);
    }
}];

// Buscar todos os pedidos de um veiculo
exports.findAllByVeiculo = [verificarToken, async (req, res, next) => {
    try {
        const veiculoId = req.params.veiculoId;

        const usuario = await usuarios.findByPk(req.userId);
        if (!usuario) {
            return res.status(404).send({ message: 'Usuário não encontrado.' });
        }

        // Se o usuário for CLIENTE, não permitir acesso a pedidos de veículos
        if (usuario.tipo === 'CLIENTE') {
            return res.status(403).send({ message: 'Você não tem permissão para acessar os pedidos deste veículo.' });
        }

        const pedidosVeiculo = await pedidos.findAll({
            where: { veiculoId: veiculoId },
            include: [veiculos, usuarios]
        });

        if (!pedidosVeiculo || pedidosVeiculo.length === 0) {
            return res.status(404).send(`Pedidos do veículo com id ${veiculoId} não encontrados.`);
        }

        res.status(200).send(pedidosVeiculo);
    } catch (err) {
        next(err);
    }
}];



