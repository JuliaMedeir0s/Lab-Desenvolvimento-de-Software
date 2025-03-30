const router = require('express').Router();
const empregoController = require('../controllers/empregoController');

router.route('/:id')
    .delete(empregoController.delete)
    .put(empregoController.update);

module.exports = router;