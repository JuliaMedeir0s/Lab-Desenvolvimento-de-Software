package views;

import java.util.Scanner;
import controller.SecretariaController;
import utils.Utils;

public class GerenciarUsuariosView {
    private static final SecretariaController secretariaController = new SecretariaController();
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrarMenu() {
        int opcao;
        do {
            Utils.limparTela();
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
                    Utils.pausarTela();
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
        Utils.pausarTela();
    }

    private static void editarUsuario() {
        System.out.print("ID do Usuário: ");
        String id = sc.nextLine();
    
        System.out.print("Novo Nome (ENTER para manter): ");
        String novoNome = sc.nextLine();
    
        System.out.print("Novo E-mail (ENTER para manter): ");
        String novoEmail = sc.nextLine();
    
        System.out.print("Nova Senha (ENTER para manter): ");
        String novaSenha = sc.nextLine();
    
        boolean sucesso = secretariaController.editarUsuario(id, novoNome, novoEmail, novaSenha);
        System.out.println(sucesso ? "✅ Usuário editado com sucesso!" : "❌ Erro ao editar usuário.");
        Utils.pausarTela();
    }
    

    private static void alterarStatusUsuario() {
        System.out.print("ID do Usuário: ");
        String id = sc.nextLine();
    
        System.out.print("Tem certeza que deseja alterar o status deste usuário? (S/N): ");
        String confirmacao = sc.nextLine().trim().toUpperCase();
        if (!confirmacao.equals("S")) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        boolean sucesso = secretariaController.alterarStatusUsuario(id);
        System.out.println(sucesso ? "✅ Status do usuário alterado com sucesso!" : "❌ Erro ao alterar status.");
        Utils.pausarTela();
    }

    private static void listarUsuarios() {
        secretariaController.listarUsuarios();
        Utils.pausarTela();
    }
}
