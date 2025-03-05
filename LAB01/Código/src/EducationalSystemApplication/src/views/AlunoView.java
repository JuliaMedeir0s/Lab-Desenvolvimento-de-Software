package views;

import java.util.Scanner;

import controller.SessaoController;

public class AlunoView {
    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU ALUNO =====");
            System.out.println("1 - Visualizar Disciplinas");
            System.out.println("2 - Realizar Matrícula");
            System.out.println("3 - Cancelar Matrícula");
            System.out.println("0 - Logout");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Listando disciplinas...");
                    break;
                case 2:
                    System.out.println("Realizando matrícula...");
                    break;
                case 3:
                    System.out.println("Cancelando matrícula...");
                    break;
                case 0:
                    SessaoController.logout();
                    System.out.println("Sessão encerrada.");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }
}
