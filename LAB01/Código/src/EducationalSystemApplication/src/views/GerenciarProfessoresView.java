package views;

import controller.ProfessorController;
import models.Professor;

import java.util.Scanner;

public class GerenciarProfessoresView {
    private static final ProfessorController professorController = new ProfessorController();
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrar() {
        int opcao;
        do {
            limparConsole();
            System.out.println("\n===== GERENCIAR PROFESSORES =====");
            System.out.println("1. Adicionar Professor");
            System.out.println("2. Editar Professor");
            System.out.println("3. Ativar/Desativar Professor");
            System.out.println("4. Listar Professores");
            System.out.println("5. Visualizar Detalhes de um Professor");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    adicionarProfessor();
                    break;
                case 2:
                    editarProfessor();
                    break;
                case 3:
                    alterarStatusProfessor();
                    break;
                case 4:
                    professorController.listarProfessores();
                    pausarTela();
                    break;
                case 5:
                    visualizarProfessor();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.");
                    pausarTela();
            }
        } while (opcao != 0);
    }

    private static void adicionarProfessor() {
        System.out.print("Nome do Professor: ");
        String nome = sc.nextLine();
        System.out.print("E-mail do Professor: ");
        String email = sc.nextLine();
        System.out.print("Senha do Professor: ");
        String senha = sc.nextLine();

        boolean sucesso = professorController.adicionarProfessor(nome, email, senha);
        System.out.println(
                sucesso ? "✅ Professor adicionado com sucesso!" : "❌ Erro: Já existe um professor com esse e-mail.");
        pausarTela();
    }

    private static void editarProfessor() {
        professorController.listarProfessores();
        System.out.print("\nDigite o número do professor que deseja editar: ");
        int index = sc.nextInt();
        sc.nextLine();

        Professor professorSelecionado = professorController.selecionarProfessor(index);
        if (professorSelecionado == null) {
            System.out.println("❌ Erro: Professor não encontrado.");
            pausarTela();
            return;
        }

        System.out.println("\n📌 Professor Selecionado:");
        System.out.println("Nome: " + professorSelecionado.getNome());
        System.out.println("E-mail: " + professorSelecionado.getEmail());

        System.out.print("\nNovo Nome (ENTER para manter): ");
        String novoNome = sc.nextLine();
        System.out.print("Novo E-mail (ENTER para manter): ");
        String novoEmail = sc.nextLine();
        System.out.print("Nova Senha (ENTER para manter): ");
        String novaSenha = sc.nextLine();

        System.out.print("\nConfirmar edição? (S/N): ");
        String confirmacao = sc.nextLine().trim().toUpperCase();
        if (!confirmacao.equals("S")) {
            System.out.println("❌ Edição cancelada.");
            pausarTela();
            return;
        }

        boolean sucesso = professorController.editarProfessor(professorSelecionado.getId(), novoNome, novoEmail,
                novaSenha);
        System.out.println(sucesso ? "✅ Edição realizada com sucesso!" : "❌ Erro ao editar professor.");
        pausarTela();
    }

    private static void alterarStatusProfessor() {
        professorController.listarProfessores();
        System.out.print("\nDigite o número do professor que deseja ativar/desativar: ");
        int index = sc.nextInt();
        sc.nextLine();

        boolean sucesso = professorController.alterarStatusProfessor(index);
        System.out
                .println(sucesso ? "✅ Status do professor alterado com sucesso!" : "❌ Erro: Professor não encontrado.");
        pausarTela();
    }

    private static void visualizarProfessor() {
        professorController.listarProfessores();
        System.out.print("\nDigite o número do professor que deseja visualizar: ");
        int index = sc.nextInt();
        sc.nextLine();
    
        Professor professorSelecionado = professorController.selecionarProfessor(index);
        if (professorSelecionado == null) {
            System.out.println("❌ Erro: Professor não encontrado.");
            pausarTela();
            return;
        }
    
        professorController.visualizarProfessor(professorSelecionado.getId());
        pausarTela();
    }

    private static void limparConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pausarTela() {
        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
    }
}