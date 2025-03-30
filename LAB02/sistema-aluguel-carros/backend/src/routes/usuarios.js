const router = require('express').Router();
const clienteController = require('../controllers/usuarioController');

router.route('/')
    .post(clienteController.create)
    .get(clienteController.findAll);

router.route('/:id')
    .get(clienteController.findOne)
    .put(clienteController.update)
    .delete(clienteController.delete);

router.route('/funcionario')
    .get(clienteController.findAllFuncionarios);

router.route('/cliente')
    .get(clienteController.findAllClientes);

module.exports = router;