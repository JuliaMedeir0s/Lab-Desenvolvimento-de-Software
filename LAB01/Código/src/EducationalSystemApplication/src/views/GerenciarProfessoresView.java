package views;

import controller.ProfessorController;
import models.Professor;
import utils.Utils;

import java.util.Scanner;

public class GerenciarProfessoresView {
    private static final ProfessorController professorController = new ProfessorController();
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrarMenu() {
        int opcao;
        do {
            Utils.limparTela();
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
                    Utils.pausarTela();
                    break;
                case 5:
                    visualizarProfessor();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.");
                    Utils.pausarTela();
            }
        } while (opcao != 0);
    }

    private static void adicionarProfessor() {
        Utils.limparTela();
        System.out.println("===== ADICIONAR PROFESSOR =====");
    
        System.out.print("Nome do Professor (0 para cancelar): ");
        String nome = sc.nextLine();
        if (nome.equals("0")) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        System.out.print("E-mail do Professor (0 para cancelar): ");
        String email = sc.nextLine();
        if (email.equals("0")) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        System.out.print("Senha do Professor (0 para cancelar): ");
        String senha = sc.nextLine();
        if (senha.equals("0")) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        boolean sucesso = professorController.adicionarProfessor(nome, email, senha);
        System.out.println(
                sucesso ? "✅ Professor adicionado com sucesso!" : "❌ Erro: Já existe um professor com esse e-mail.");
        Utils.pausarTela();
    }    

    private static void editarProfessor() {
        professorController.listarProfessores();
        System.out.print("\nDigite o número do professor que deseja editar (0 para cancelar): ");
        int index = Utils.lerInteiro();
        if (index == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        Professor professorSelecionado = professorController.selecionarProfessor(index);
        if (professorSelecionado == null) {
            System.out.println("❌ Erro: Professor não encontrado.");
            Utils.pausarTela();
            return;
        }
    
        System.out.println("\n📌 Professor Selecionado:");
        System.out.println("Nome: " + professorSelecionado.getNome());
        System.out.println("E-mail: " + professorSelecionado.getEmail());
    
        System.out.print("\nNovo Nome (ENTER para manter, 0 para cancelar): ");
        String novoNome = sc.nextLine();
        if (novoNome.equals("0")) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        System.out.print("Novo E-mail (ENTER para manter, 0 para cancelar): ");
        String novoEmail = sc.nextLine();
        if (novoEmail.equals("0")) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        System.out.print("Nova Senha (ENTER para manter, 0 para cancelar): ");
        String novaSenha = sc.nextLine();
        if (novaSenha.equals("0")) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        System.out.print("\nConfirmar edição? (S/N): ");
        String confirmacao = sc.nextLine().trim().toUpperCase();
        if (!confirmacao.equals("S")) {
            System.out.println("❌ Edição cancelada.");
            Utils.pausarTela();
            return;
        }
    
        boolean sucesso = professorController.editarProfessor(professorSelecionado.getId(), novoNome, novoEmail,
                novaSenha);
        System.out.println(sucesso ? "✅ Edição realizada com sucesso!" : "❌ Erro ao editar professor.");
        Utils.pausarTela();
    }    

    private static void alterarStatusProfessor() {
        professorController.listarProfessores();
        System.out.print("\nDigite o número do professor que deseja ativar/desativar: ");
        int index = sc.nextInt();
        sc.nextLine();

        boolean sucesso = professorController.alterarStatusProfessor(index);
        System.out
                .println(sucesso ? "✅ Status do professor alterado com sucesso!" : "❌ Erro: Professor não encontrado.");
        Utils.pausarTela();
    }

    private static void visualizarProfessor() {
        professorController.listarProfessores();
        System.out.print("\nDigite o número do professor que deseja visualizar: ");
        int index = sc.nextInt();
        sc.nextLine();
    
        Professor professorSelecionado = professorController.selecionarProfessor(index);
        if (professorSelecionado == null) {
            System.out.println("❌ Erro: Professor não encontrado.");
            Utils.pausarTela();
            return;
        }
    
        professorController.visualizarProfessor(professorSelecionado.getId());
        Utils.pausarTela();
    }
}