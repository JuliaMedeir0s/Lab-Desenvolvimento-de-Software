const router = require('express').Router();
const clienteController = require('../controllers/clienteController');

router.route('/')
    .post(clienteController.create)
    .get(clienteController.findAll);

router.route('/:id')
    .get(clienteController.findOne)
    .put(clienteController.update)
    .delete(clienteController.delete);

module.exports = router;