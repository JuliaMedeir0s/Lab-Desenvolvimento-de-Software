const express = require('express');
const router = express.Router();
const parceiroController = require('../controllers/parceiroController');   
const { route } = require('./alunos');

router.route('/')
    .get(parceiroController.listParceiros) // Listar todos os parceiros
    .post(parceiroController.createParceiro); // Criar um novo parceiro

router.route('/:id')
    .get(parceiroController.listParceiroById) // Listar um parceiro por ID
    .put(parceiroController.updateParceiro) // Atualizar um parceiro
    .delete(parceiroController.deleteParceiro); // Deletar um parceiro

module.exports = router;
