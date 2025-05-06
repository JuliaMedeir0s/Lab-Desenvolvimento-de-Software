const express = require('express');
const router = express.Router();
const alunoController = require('../controllers/alunoController');

router.route('/')
    .get(alunoController.list) // Listar todos os alunos
    .post(alunoController.create); // Criar um novo aluno

router.route('/:id')
    .get(alunoController.listById) // Listar um aluno por ID
    .put(alunoController.update) // Atualizar um aluno
    .delete(alunoController.delete); // Deletar um aluno

module.exports = router;
