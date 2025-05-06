const express = require('express');
const router = express.Router();
const transacaoController = require('../controllers/transacoesController'); 

router.route('/')
    .get(transacaoController.list) // Listar todas as transações
    .post(transacaoController.create); // Criar uma nova transação

router.route('/:id')
    .get(transacaoController.listById) // Listar uma transação por ID
    .put(transacaoController.update) // Atualizar uma transação
    .delete(transacaoController.delete); // Deletar uma transação

module.exports = router;