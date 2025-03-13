package views;

import java.util.Scanner;
import utils.Utils;

import controller.SessaoController;
import models.abstracts.Usuario;

public class LoginView {
    public static void mostrarLogin() {
        try (Scanner scanner = new Scanner(System.in)) { //ele não tava fechando certo, por isso o try-with-resources
            if (SessaoController.isLogado()) {
                Usuario usuarioLogado = SessaoController.getUsuarioLogado();
                System.out.println("✅ Usuário " + usuarioLogado.getNome() + " já está logado.");
                abrirView(usuarioLogado);
                return;
            }

            while (!SessaoController.isLogado()) {
                System.out.print("ID: ");
                String id = scanner.nextLine();
                System.out.print("Senha: ");
                String senha = scanner.nextLine();

                Usuario usuario = SessaoController.iniciarSessao(id, senha); 

                if (usuario != null) {
                    SessaoController.login(usuario);
                    Utils.limparTela();
                    abrirView(usuario);
                } else {
                    Utils.limparTela();
                    System.out.println("❌ Credenciais inválidas! Tente novamente.");
                }
            }
        }
    }

    private static void abrirView(Usuario usuario) {
        switch (usuario.getTipoUsuario()) {
            case ALUNO:
                AlunoView.mostrarMenu();
                break;
            case PROFESSOR:
                ProfessorView.mostrarMenu();
                break;
            case SECRETARIA:
                SecretariaView.mostrarMenu();
                break;
        }
    }
}