const express = require('express');
const router = express.Router();
const instituicaoController = require('../controllers/instituicaoController');

router.route('/')
    .get(instituicaoController.list) // Listar todas as instituições
    .post(instituicaoController.create); // Criar uma nova instituição

router.route('/:id')
    .get(instituicaoController.listById) // Listar uma instituição por ID
    .put(instituicaoController.update) // Atualizar uma instituição
    .delete(instituicaoController.delete); // Deletar uma instituição

module.exports = router;