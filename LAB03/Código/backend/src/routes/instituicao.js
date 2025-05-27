const express = require('express');
const { getAllInstituicoes } = require('../controllers/instituicaoController');

const router = express.Router();

router.get('/', getAllInstituicoes);

module.exports = router;
