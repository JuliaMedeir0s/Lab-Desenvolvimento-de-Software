const express = require('express');
const router = express.Router();
const professorController = require('../controllers/professorController');
const { route } = require('./alunos');

router.route('/')
    .get(professorController.list) // Listar todos os professores
    .post(professorController.create); // Criar um novo professor

router.route('/:id')
    .get(professorController.listById) // Listar um professor por ID
    .put(professorController.update) // Atualizar um professor
    .delete(professorController.delete); // Deletar um professor

module.exports = router;