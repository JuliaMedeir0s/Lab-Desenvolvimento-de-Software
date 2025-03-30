const pedidos = require('../models/pedido');
const veiculos = require('../models/veiculo');
const usuarios = require('../models/usuario');


// Criar um pedido
exports.create = (req, res, next) => {
    pedidos.create(req.body, {
        include: [veiculos, usuarios],
    }).then(pedido => {
        res.status(200).send(pedido);
    }).catch(err => {
        if (err.name === 'SequelizeUniqueConstraintError') {
            res.status(400).send(`Pedido com informações já cadastradas.`);
        } else {
            next(err);
        }
    });
}

exports.update = (req, res, next) => {
    const id = req.params.id;
    const newPedido = req.body;

    pedidos.findByPk(id).then(pedido => {
        if (!pedido) {
            return res.status(404).send('Pedido não encontrado.');
        }
        pedido.update(newPedido).then(() => {
            res.status(200).send(pedido);
        }).catch(err => {
            next(err);
        });
    }).catch(err => {
        next(err);
    });
}

// Buscar todos os pedidos
exports.findAll = (req, res, next) => {
    pedidos.findAll({
        include: [veiculos, usuarios]
    }).then(pedido => {
        if (!pedido) {
            return res.status(404).send('Pedidos não encontrados.');
        }
        res.status(200).send(pedido);
    }).catch(err => {
        next(err);
    });
};

// Buscar um pedido pelo id
exports.findOne = async (req, res, next) => {
    const id = req.params.id;
    const pedido = await pedidos.findByPk(id, {
        include: [veiculos, usuarios]
    }).catch(next);
    if (!pedido) {
        res.status(404).send(`Pedido com id ${id} não encontrado.`);
    } else {
        res.status(200).send(pedido);
    }
}

// Buscar todos os pedidos de um cliente
exports.findAllByCliente = async (req, res, next) => {
    const clienteId = req.params.clienteId;
    const pedidosCliente = await pedidos.findAll({
        where: { clienteId: clienteId },
        include: [veiculos, usuarios]
    }).catch(next);
    if (!pedidosCliente) {
        res.status(404).send(`Pedidos do cliente com id ${clienteId} não encontrados.`);
    } else {
        res.status(200).send(pedidosCliente);
    }
}

// Buscar todos os pedidos de um veiculo
exports.findAllByVeiculo = async (req, res, next) => {
    const veiculoId = req.params.veiculoId;
    const pedidosVeiculo = await pedidos.findAll({
        where: { veiculoId: veiculoId },
        include: [veiculos, usuarios]
    }).catch(next);
    if (!pedidosVeiculo) {
        res.status(404).send(`Pedidos do veiculo com id ${veiculoId} não encontrados.`);
    } else {
        res.status(200).send(pedidosVeiculo);
    }
}

// Deletar um pedido pelo id
exports.delete = (req, res, next) => {
    const id = req.params.id;
    pedidos.findByPk(id).then(pedido => {
        if (!pedido) {
            return res.status(404).send('Pedido não encontrado.');
        }
        pedido.destroy().then(() => {
            res.status(200).send(pedido);
        }).catch(err => {
            next(err);
        });
    }
    ).catch(err => {
        next(err);
    });
}



