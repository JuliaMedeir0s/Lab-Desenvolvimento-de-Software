const router = require('express').Router();
const veiculoController = require('../controllers/veiculoController');

router.route('/')
    .post(veiculoController.create)
    .get(veiculoController.findAll);

router.route('/:id')
    .get(veiculoController.findOne)
    .put(veiculoController.update)
    .delete(veiculoController.delete);

module.exports = router;