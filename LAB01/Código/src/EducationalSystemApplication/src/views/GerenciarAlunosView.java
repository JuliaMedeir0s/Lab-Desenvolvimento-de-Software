package views;

import java.util.Scanner;
import controller.SecretariaController;

public class GerenciarAlunosView {
    private static final SecretariaController secretariaController = new SecretariaController();
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrarMenu() {
        int opcao;
        do {
            limparConsole();
            System.out.println("\n===== GERENCIAR ALUNOS =====");
            System.out.println("1. Adicionar Aluno");
            System.out.println("2. Editar Aluno");
            System.out.println("3. Desativar/Ativar Aluno");
            System.out.println("4. Listar Alunos");
            System.out.println("0. Voltar ao Menu Anterior");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 1:
                    adicionarAluno();
                    break;
                case 2:
                    editarAluno();
                    break;
                case 3:
                    alterarStatusAluno();
                    break;
                case 4:
                    listarAlunos();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.");
                    pausarTela();
            }
        } while (opcao != 0);
    }

    private static void adicionarAluno() {
        System.out.print("Nome do Aluno: ");
        String nome = sc.nextLine();
        System.out.print("E-mail do Aluno: ");
        String email = sc.nextLine();
        System.out.print("Senha do Aluno: ");
        String senha = sc.nextLine();
        secretariaController.adicionarAluno(nome, email, senha);
        System.out.println("✅ Aluno adicionado com sucesso!");
        pausarTela();
    }

    private static void editarAluno() {
        System.out.print("Matrícula do Aluno: ");
        String matricula = sc.nextLine();
        System.out.print("Novo Nome: ");
        String novoNome = sc.nextLine();
        secretariaController.editarAluno(matricula, novoNome);
        System.out.println("✅ Aluno editado com sucesso!");
        pausarTela();
    }

    private static void alterarStatusAluno() {
        System.out.print("Matrícula do Aluno: ");
        String matricula = sc.nextLine();
        secretariaController.alterarStatusAluno(matricula);
        System.out.println("✅ Status do aluno alterado com sucesso!");
        pausarTela();
    }

    private static void listarAlunos() {
        secretariaController.listarAlunos();
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