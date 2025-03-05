package controller;

import java.util.List;
import java.util.Optional;

import DAO.DisciplinaDAO;
import models.Disciplina;
import models.enums.Status;

public class SecretariaDisciplinaController {
    private final DisciplinaDAO disciplinaDAO = DisciplinaDAO.getInstance();

    public void listarDisciplinas() {
        List<Disciplina> disciplinas = disciplinaDAO.listarDisciplinas();
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
        } else {
            System.out.println("\n=== Disciplinas Cadastradas ===");
            disciplinas.forEach(System.out::println);
        }
    }

    public void criarDisciplina(String codigo, String nome, int cargaHoraria, double valor) {
        Optional<Disciplina> disciplinaExistente = disciplinaDAO.buscarPorCodigo(codigo);
        if (disciplinaExistente.isPresent()) {
            System.out.println("Erro: Já existe uma disciplina com este código.");
            return;
        }

        Disciplina novaDisciplina = new Disciplina(codigo, nome, cargaHoraria, null, valor);
        disciplinaDAO.adicionarDisciplina(novaDisciplina);
        System.out.println("✅ Disciplina criada com sucesso!");
    }

    public void editarDisciplina(String codigo, String novoNome, int novaCargaHoraria, double novoValor) {
        Optional<Disciplina> disciplinaOpt = disciplinaDAO.buscarPorCodigo(codigo);

        if (disciplinaOpt.isEmpty()) {
            System.out.println("Erro: Disciplina não encontrada.");
            return;
        }

        Disciplina disciplina = disciplinaOpt.get();
        if (disciplina.getStatus() == Status.INATIVO) {
            System.out.println("Erro: Não é possível editar uma disciplina desativada.");
            return;
        }

        disciplinaDAO.editarDisciplina(codigo, novoNome, novaCargaHoraria, novoValor);
        System.out.println("Disciplina " + codigo + " atualizada com sucesso!");
    }

    public void desativarDisciplina(String codigo) {
        Optional<Disciplina> disciplinaOpt = disciplinaDAO.buscarPorCodigo(codigo);

        if (disciplinaOpt.isEmpty()) {
            System.out.println("Erro: Disciplina não encontrada.");
            return;
        }

        Disciplina disciplina = disciplinaOpt.get();
        if (disciplina.getStatus() == Status.INATIVO) {
            System.out.println("Erro: A disciplina já está desativada.");
            return;
        }

        disciplinaDAO.alterarStatusDisciplina(codigo, Status.INATIVO);
        System.out.println("Disciplina " + codigo + " foi desativada.");
    }

    public void ativarDisciplina(String codigo) {
        Optional<Disciplina> disciplinaOpt = disciplinaDAO.buscarPorCodigo(codigo);

        if (disciplinaOpt.isEmpty()) {
            System.out.println("Erro: Disciplina não encontrada.");
            return;
        }

        Disciplina disciplina = disciplinaOpt.get();
        if (disciplina.getStatus() == Status.ATIVO) {
            System.out.println("Erro: A disciplina já está ativa.");
            return;
        }

        disciplinaDAO.alterarStatusDisciplina(codigo, Status.ATIVO);
        System.out.println("Disciplina " + codigo + " foi ativada.");
    }
}

