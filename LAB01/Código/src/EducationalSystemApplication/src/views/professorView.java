package views;

import controller.ProfessorController;
import controller.SessaoController;
import models.Aluno;
import models.Disciplina;
import models.Matricula;
import models.Professor;
import utils.Utils;

import java.util.List;
import java.util.Scanner;

import DAO.ProfessorDAO;

public class ProfessorView {
    private static final ProfessorController professorController = new ProfessorController();
    
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrarMenu() {
        while (true) {
            Utils.limparTela();
            System.out.println("===== MENU PROFESSOR =====");
            System.out.println("1. Listar Disciplinas");
            System.out.println("2. Listar Alunos de uma Disciplina");
            System.out.println("3. Logout");
            System.out.println("0. Encerrar sistema");
            System.out.print("Escolha uma opção: ");

            int opcao = Utils.lerInteiro();
            switch (opcao) {
                case 1 -> listarDisciplinas();
                case 2 -> listarAlunos();
                case 3 -> deslogar();
                case 0 -> {
                    return;
                }
                default -> System.out.println("❌ Opção inválida.");
            }
        }
    }

    private static void deslogar() {
        Utils.limparTela();
        System.out.println("✅ Você foi deslogado.");
        SessaoController.encerrarSessao();
        LoginView.mostrarLogin(); 
    }

    private static void listarDisciplinas() {
        Utils.limparTela();
        List<Disciplina> disciplinas = professorController.listarDisciplinasLecionadas((Professor) SessaoController.getUsuarioLogado());
        if (disciplinas.isEmpty()) {
            System.out.println((Professor) SessaoController.getUsuarioLogado());
            System.out.println("❌ Você não está lecionando nenhuma disciplina.");
        } else {
            System.out.println("\n=== Disciplinas Lecionadas ===");
            System.out.println(" Nº | Código   | Nome");
            System.out.println("------------------------------------");
            for (int i = 0; i < disciplinas.size(); i++) {
                System.out.printf(" %2d | %-8s | %s\n", i + 1, disciplinas.get(i).getCodigo(), disciplinas.get(i).getNome());
            }
        }
        Utils.pausarTela();
    }

    private static void listarAlunos() {
        listarDisciplinas();
        System.out.print("\nDigite o número da disciplina para visualizar os alunos (0 para cancelar): ");
        int index = Utils.lerInteiro();
        if (index == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }

        List<Disciplina> disciplinas = professorController.listarDisciplinasLecionadas((Professor) SessaoController.getUsuarioLogado());
        if (index < 1 || index > disciplinas.size()) {
            System.out.println("❌ Opção inválida.");
            Utils.pausarTela();
            return;
        }

        Disciplina disciplinaSelecionada = disciplinas.get(index - 1);
        exibirAlunosDaDisciplina(disciplinaSelecionada);
    }

    private static void exibirAlunosDaDisciplina(Disciplina disciplina) {
        List<Matricula> matriculas = disciplina.getMatriculas();

        System.out.println("\n📌 Alunos Matriculados em: " + disciplina.getNome());
        if (matriculas.isEmpty()) {
            System.out.println("❌ Nenhum aluno matriculado nesta disciplina.");
        } else {
            System.out.println("\n Nº | Nome                 | Curso");
            System.out.println("------------------------------------------");
            for (int i = 0; i < matriculas.size(); i++) {
                Aluno aluno = matriculas.get(i).getAluno();
                System.out.printf(" %2d | %-20s | %s\n", i + 1, aluno.getNome(), aluno.getCurso());
            }
        }
        Utils.pausarTela();
    }
}