package views;

import java.util.Scanner;
import controller.SecretariaController;

public class GerenciarCursosView {
    private static final SecretariaController secretariaController = new SecretariaController();
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrarMenu() {
        int opcao;
        do {
            limparConsole();
            System.out.println("\n===== GERENCIAR CURSOS =====");
            System.out.println("1. Adicionar Curso");
            System.out.println("2. Editar Curso");
            System.out.println("3. Excluir Curso");
            System.out.println("4. Listar Cursos");
            System.out.println("0. Voltar ao Menu Anterior");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 1:
                    adicionarCurso();
                    break;
                case 2:
                    editarCurso();
                    break;
                case 3:
                    excluirCurso();
                    break;
                case 4:
                    listarCursos();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.");
                    pausarTela();
            }
        } while (opcao != 0);
    }

    private static void adicionarCurso() {
        System.out.print("Nome do Curso: ");
        String nome = sc.nextLine();
        System.out.print("Código do Curso: ");
        String codigo = sc.nextLine();
        secretariaController.adicionarCurso(nome, codigo);
        System.out.println("✅ Curso adicionado com sucesso!");
        pausarTela();
    }

    private static void editarCurso() {
        System.out.print("Código do Curso: ");
        String codigo = sc.nextLine();
        System.out.print("Novo Nome do Curso: ");
        String novoNome = sc.nextLine();
        secretariaController.editarCurso(codigo, novoNome);
        System.out.println("✅ Curso editado com sucesso!");
        pausarTela();
    }

    private static void excluirCurso() {
        System.out.print("Código do Curso: ");
        String codigo = sc.nextLine();
        secretariaController.excluirCurso(codigo);
        System.out.println("✅ Curso excluído com sucesso!");
        pausarTela();
    }

    private static void listarCursos() {
        secretariaController.listarCursos();
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
