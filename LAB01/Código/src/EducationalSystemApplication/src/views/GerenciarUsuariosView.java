package views;

import java.util.Scanner;
import controller.SecretariaController;

public class GerenciarUsuariosView {
    private static final SecretariaController secretariaController = new SecretariaController();
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrar() {
        int opcao;
        do {
            limparConsole();
            System.out.println("\n===== GERENCIAR USUÁRIOS DA SECRETARIA =====");
            System.out.println("1. Adicionar Usuário");
            System.out.println("2. Editar Usuário");
            System.out.println("3. Desativar/Ativar Usuário");
            System.out.println("4. Listar Usuários");
            System.out.println("0. Voltar ao Menu Anterior");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 1:
                    adicionarUsuario();
                    break;
                case 2:
                    editarUsuario();
                    break;
                case 3:
                    alterarStatusUsuario();
                    break;
                case 4:
                    listarUsuarios();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.");
                    pausarTela();
            }
        } while (opcao != 0);
    }

    private static void adicionarUsuario() {
        System.out.print("Nome do Usuário: ");
        String nome = sc.nextLine();
        System.out.print("E-mail do Usuário: ");
        String email = sc.nextLine();
        System.out.print("Senha do Usuário: ");
        String senha = sc.nextLine();
        secretariaController.adicionarUsuario(nome, email, senha);
        System.out.println("✅ Usuário adicionado com sucesso!");
        pausarTela();
    }

    private static void editarUsuario() {
        System.out.print("ID do Usuário: ");
        String id = sc.nextLine();
        System.out.print("Novo Nome: ");
        String novoNome = sc.nextLine();
        secretariaController.editarUsuario(id, novoNome);
        System.out.println("✅ Usuário editado com sucesso!");
        pausarTela();
    }

    private static void alterarStatusUsuario() {
        System.out.print("ID do Usuário: ");
        String id = sc.nextLine();
        secretariaController.alterarStatusUsuario(id);
        System.out.println("✅ Status do usuário alterado com sucesso!");
        pausarTela();
    }

    private static void listarUsuarios() {
        secretariaController.listarUsuarios();
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
