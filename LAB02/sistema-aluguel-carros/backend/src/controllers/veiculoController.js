const veiculo = require('../models/veiculo');

//criar um veiculo
exports.create = (req, res, next) => {
    veiculo.create(req.body).then(veiculo => {
        res.status(200).send(veiculo);
    }).catch(err => {
        if (err.name === 'SequelizeUniqueConstraintError') {
            res.status(400).send(`Veículo com informações já cadastradas.`);
        } else {
            next(err);
        }
    });
}

//buscar todos os veiculos
exports.findAll = (req, res, next) => {
    veiculo.findAll().then(veiculo => {
        if (!veiculo) {
            return res.status(404).send('Veículos não encontrados.');
        }
        res.status(200).send(veiculo);
    }).catch(err => {
        next(err);
    });
};

//buscar um veiculo pelo id
exports.findOne = async (req, res, next) => {
    const id = req.params.id;
    const veicu = await veiculo.findByPk(id).catch(next);
    if (veicu) {
        res.status(200).send(veicu);

    } else {
        res.status(404).send(`Veículo com id ${id} não encontrado.`);
    }
};

//atualizar um veiculo pelo id
exports.update =  (req, res, next) => {
    const newVeiculo = req.body;
    const id = req.params.id;

    veiculo.findByPk(id).then(veiculo => {
        if (veiculo) {
            veiculo.update(newVeiculo);
            res.status(200).send(veiculo);
        }else {
            res.status(404).send(`Veículo com id ${id} não encontrado.`);
        }
    }).catch(next);
}


//deletar um veiculo pelo id
exports.delete = (req, res, next) => {
    const id = req.params.id;
    veiculo.findByPk(id).then(veiculo => {
        if (veiculo) {
            veiculo.destroy();
            res.status(200).send(veiculo);
        } else {
            res.status(404).send(`Veículo com id ${id} não encontrado.`);
        }
    }).catch(next);
}

