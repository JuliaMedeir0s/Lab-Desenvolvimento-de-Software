const express = require('express');
const router = express.Router();
const vantagemController = require('../controllers/vantagemController'); // Importa o controlador de vantagens

router.route('/')
    .get(vantagemController.listVantagens) // Listar todas as vantagens
    .post(vantagemController.createVantagem); // Criar uma nova vantagem

router.route('/:id')
    .get(vantagemController.listVantagemById) // Listar uma vantagem por ID
    .put(vantagemController.updateVantagem) // Atualizar uma vantagem
    .delete(vantagemController.delete); // Deletar uma vantagem

module.exports = router; // Exporta o roteador para uso em outros arquivos