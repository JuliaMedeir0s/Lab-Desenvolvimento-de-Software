package views;
import utils.Utils;

import java.util.Scanner;
import controller.SecretariaController;
import controller.SessaoController;

public class SecretariaView {
    public static void mostrarMenu() {
        while (true) {
            Utils.limparTela();
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

            int opcao = Utils.lerInteiro();
            switch (opcao) {
                case 1 -> GerenciarDisciplinasView.mostrarMenu();
                case 2 -> GerenciarAlunosView.mostrarMenu();
                case 3 -> GerenciarProfessoresView.mostrarMenu();
                case 4 -> GerenciarSemestreView.mostrarMenu();
                case 5 -> GerenciarCursosView.mostrarMenu();
                case 6 -> GerenciarUsuariosView.mostrarMenu();
                case 7 -> deslogar(); 
                case 0 -> {
                    return;
                }
                default -> System.out.println("❌ Opção inválida! Tente novamente.");
            }
        }
    }

    private static void deslogar() {
        System.out.println("✅ Você foi deslogado.");
        SessaoController.encerrarSessao();
        LoginView.mostrarLogin(); 
    }
}