const router = require('express').Router();
const clienteController = require('../controllers/usuarioController');
const authMiddleware = require('../middlewares/auth');

router.route('/')
    .post(clienteController.create)
    .get(authMiddleware, clienteController.findAll);

router.route('/:id')
    .get(authMiddleware, clienteController.findOne)
    .put(authMiddleware, clienteController.update)
    .delete(authMiddleware, clienteController.delete);

router.route('/funcionario')
    .get(authMiddleware, clienteController.findAllFuncionarios);

router.route('/cliente')
    .get(authMiddleware, clienteController.findAllClientes);

module.exports = router;