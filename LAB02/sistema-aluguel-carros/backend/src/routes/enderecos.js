const router = require('express').Router();
const enderecoController = require('../controllers/enderecoController');

router.route('/:id')
    .delete(enderecoController.delete)
    .put(enderecoController.update);

module.exports = router;