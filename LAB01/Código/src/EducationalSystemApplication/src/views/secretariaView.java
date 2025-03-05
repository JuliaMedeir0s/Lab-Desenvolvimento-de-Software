package views;

import java.util.Scanner;
import controller.SecretariaController;

public class SecretariaView {
    private static final SecretariaController secretariaController = new SecretariaController();
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrarMenu() {
        int opcao;
        do {
            limparConsole();
            System.out.println("\n===== MENU DA SECRETARIA =====");
            System.out.println("1. Gerenciar Disciplinas");
            System.out.println("2. Gerenciar Alunos");
            System.out.println("3. Gerenciar Professores");
            System.out.println("4. Gerenciar Semestre");
            System.out.println("5. Gerenciar Cursos");
            System.out.println("6. Gerenciar Usuários da Secretaria");
            System.out.println("7. Deslogar");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1:
                    GerenciarDisciplinasView.mostrar();
                    break;
                case 2:
                    GerenciarAlunosView.mostrar();
                    break;
                case 3:
                    GerenciarProfessoresView.mostrar();
                    break;
                case 4:
                    GerenciarSemestreView.mostrar();
                    break;
                case 5:
                    GerenciarCursosView.mostrar();
                    break;
                case 6:
                    GerenciarUsuariosView.mostrar();
                    break;
                case 7:
                    System.out.println("✅ Você foi deslogado.");
                    return;
                case 0:
                    System.out.println("Retornando ao menu principal...");
                    return;
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.");
                    pausarTela();
            }
        } while (opcao != 7 && opcao != 0);
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