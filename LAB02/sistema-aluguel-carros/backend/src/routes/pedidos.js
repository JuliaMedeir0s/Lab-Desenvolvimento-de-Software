const router = require('express').Router();
const pedidoController = require('../controllers/pedidoController');

router.route('/')
    .post(pedidoController.create)
    .get(pedidoController.findAll);

router.route('/:id')
    .get(pedidoController.findOne)
    .put(pedidoController.update)
    .delete(pedidoController.delete);

router.route('/cliente/:id')
    .get(pedidoController.findAllByCliente);

router.route('/veiculo/:id')
    .get(pedidoController.findAllByVeiculo);

module.exports = router;