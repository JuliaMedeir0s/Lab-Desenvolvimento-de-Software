package views;

import java.util.Scanner;
import controller.SecretariaController;

public class GerenciarDisciplinasView {
    private static final SecretariaController secretariaController = new SecretariaController();
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrar() {
        int opcao;
        do {
            limparConsole();
            System.out.println("\n===== GERENCIAR DISCIPLINAS =====");
            System.out.println("1. Adicionar Disciplina");
            System.out.println("2. Editar Disciplina");
            System.out.println("3. Desativar/Ativar Disciplina");
            System.out.println("4. Listar Disciplinas");
            System.out.println("0. Voltar ao Menu Anterior");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 1:
                    adicionarDisciplina();
                    break;
                case 2:
                    editarDisciplina();
                    break;
                case 3:
                    alterarStatusDisciplina();
                    break;
                case 4:
                    listarDisciplinas();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.");
                    pausarTela();
            }
        } while (opcao != 0);
    }

    private static void adicionarDisciplina() {
        System.out.print("Nome da Disciplina: ");
        String nome = sc.nextLine();
        System.out.print("Código da Disciplina: ");
        String codigo = sc.nextLine();
        secretariaController.adicionarDisciplina(nome, codigo);
        System.out.println("✅ Disciplina adicionada com sucesso!");
        pausarTela();
    }

    private static void editarDisciplina() {
        System.out.print("Código da Disciplina: ");
        String codigo = sc.nextLine();
        System.out.print("Novo Nome: ");
        String novoNome = sc.nextLine();
        secretariaController.editarDisciplina(codigo, novoNome);
        System.out.println("✅ Disciplina editada com sucesso!");
        pausarTela();
    }

    private static void alterarStatusDisciplina() {
        System.out.print("Código da Disciplina: ");
        String codigo = sc.nextLine();
        secretariaController.alterarStatusDisciplina(codigo);
        System.out.println("✅ Status da disciplina alterado com sucesso!");
        pausarTela();
    }

    private static void listarDisciplinas() {
        secretariaController.listarDisciplinas();
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