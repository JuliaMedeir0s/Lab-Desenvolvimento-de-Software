package views;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import controller.secretariaController;
import models.Secretaria;
import models.Aluno;
import models.Professor;

public class secretariaView {

    public static secretariaController administrativeStaffController = new secretariaController();

    public static void menu(Secretaria secretaria) {
        System.out.println("\n=== Menu da Secretaria ===");
        System.out.println("1. Listar disciplinas");
        System.out.println("2. Adicionar disciplina");
        System.out.println("3. Listar alunos");
        System.out.println("4. Adicionar aluno");
        System.out.println("5. Listar professores");
        System.out.println("6. Adicionar professor");
        System.out.println("7. Atualizar data de inscrição");
        System.out.println("8. Criar Semestre");
        System.out.println("9. Sair");
        System.out.print("Escolha uma opção: ");
        try (Scanner sc = new Scanner(System.in)) {
            int op = sc.nextInt();

            if (op == 1) {
                listarDisciplinas();
            } else if (op == 2) {
                adicionarDisciplina();
            } else if (op == 3) {
                listarAlunos();
            } else if (op == 4) {
                adicionarAluno();
            } else if (op == 5) {
                listarProfessores();
            } else if (op == 6) {
                adicionarProfessor();
            } else if (op == 7) {
                atualizarDataInscricao();
            } else if (op == 8) {
                criarSemestre();
            } else if (op == 9) {
                System.out.println("Saindo...");
            } else {
                System.out.println("Opção inválida!");

            }
        }
    }

    public static void listarDisciplinas() {
        System.out.println("\n=== Disciplinas Cadastradas ===");
        administrativeStaffController.listarDisciplinas(null); //passar arugmento
    }

    public static void listarAlunos() {
        System.out.println("\n=== Alunos Cadastrados ===");
        List<Aluno> alunos = administrativeStaffController.getAlunos(null); //passar arugmento
        for (Aluno aluno : alunos) {
            int i = 1;
            System.out.println(i + aluno.toString());
            i++;
        }
    }

    public static void listarProfessores() {
        System.out.println("\n=== Professores Cadastrados ===");
        List<Professor> professores = administrativeStaffController.getProfessores(null);//passar arugmento
        for (Professor professor : professores) {
            int i = 1;
            System.out.println(i + professor.toString());
            i++;
        }
    }

    public static void atualizarDataInscricao(){

    }

    public static void criarSemestre(){

    }

    public static void criarSemestre(int ano, int periodo, LocalDate inicio, LocalDate fim) {
        // implementar metodo        
    }

    public static void adicionarDisciplina() {
        // implementar metodo
    }

    public static void adicionarAluno() {
        // implementar metodo
    }

    public static void adicionarProfessor() {
        // implementar metodo
    }

}
