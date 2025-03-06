package views;

import java.util.Scanner;

import controller.SessaoController;
import models.Professor;
import controller.ProfessorController;


public class ProfessorView {

    private static final ProfessorController professorController = new ProfessorController();
    private static final Scanner sc = new Scanner(System.in);
    private static Professor p = (Professor) SessaoController.getUsuarioLogado();


    public static void mostrarMenu() {
        int opcao;

        do {
            limparConsole();
            System.out.println("\n===== MENU PROFESSOR =====");
            System.out.println("1 - Listar Disciplinas");
            System.out.println("0 - Deslogar");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 1:
                    listarDisciplinas();
                    break;
                case 0:
                    SessaoController.encerrarSessao();
                    System.out.println("✅ Você foi deslogado.");
                    return;
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.");
                    pausarTela();
            }

        } while (opcao != 0);
    }

    private static void listarDisciplinas() {
        System.out.println("Disciplinas lecionadas por " + p.getNome() + ":");
        professorController.listarDisciplinas(p);

        System.out.println("\n1 - Listar alunos de uma disciplina");
        System.out.println("0 - Voltar");   
        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                listarAlunos();
                break;
            case 0:
                return;
            default:
                System.out.println("❌ Opção inválida! Tente novamente.");

        pausarTela();
        }
    }

    private static void listarAlunos() {
        System.out.print("Digite o código da disciplina: ");
        String codigo = sc.nextLine();

        System.out.println("Alunos matriculados na disciplina:");
        professorController.listarAlunos(codigo);

        System.out.println("\n0 - Voltar");
        System.out.print("Escolha uma opção: ");

        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 0:
                limparConsole();
                mostrarMenu();
                break;
            default:
                System.out.println("❌ Opção inválida! Tente novamente.");
        }


        pausarTela();
    }
    
    private static void limparConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("❌ Não foi possível limpar o console.");
        }
    }

    private static void pausarTela() {
        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
    }
}
