const router = require('express').Router();
const clienteController = require('../controllers/clienteController');

router.route('/')
    .post(clienteController.create)

module.exports = router;