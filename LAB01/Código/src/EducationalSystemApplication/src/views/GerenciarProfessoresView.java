package views;

import java.util.Scanner;
import controller.SecretariaController;

public class GerenciarProfessoresView {
    private static final SecretariaController secretariaController = new SecretariaController();
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrar() {
        int opcao;
        do {
            limparConsole();
            System.out.println("\n===== GERENCIAR PROFESSORES =====");
            System.out.println("1. Adicionar Professor");
            System.out.println("2. Editar Professor");
            System.out.println("3. Desativar/Ativar Professor");
            System.out.println("4. Listar Professores");
            System.out.println("0. Voltar ao Menu Anterior");
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
                    listarProfessores();
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
        secretariaController.adicionarProfessor(nome, email, senha);
        System.out.println("✅ Professor adicionado com sucesso!");
        pausarTela();
    }

    private static void editarProfessor() {
        System.out.print("ID do Professor: ");
        String id = sc.nextLine();
        System.out.print("Novo Nome: ");
        String novoNome = sc.nextLine();
        secretariaController.editarProfessor(id, novoNome);
        System.out.println("✅ Professor editado com sucesso!");
        pausarTela();
    }

    private static void alterarStatusProfessor() {
        System.out.print("ID do Professor: ");
        String id = sc.nextLine();
        secretariaController.alterarStatusProfessor(id);
        System.out.println("✅ Status do professor alterado com sucesso!");
        pausarTela();
    }

    private static void listarProfessores() {
        secretariaController.listarProfessores();
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