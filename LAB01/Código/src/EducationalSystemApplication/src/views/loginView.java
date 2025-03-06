package views;

import java.util.Scanner;
import utils.Utils;

import controller.SessaoController;
import models.abstracts.Usuario;

public class LoginView {
    public static void mostrarLogin() {
        Scanner scanner = new Scanner(System.in);

        if (SessaoController.isLogado()) {
            Usuario usuarioLogado = SessaoController.getUsuarioLogado();
            System.out.println("✅ Usuário " + usuarioLogado.getNome() + " já está logado.");
            abrirView(usuarioLogado);
            return;
        }

        while (!SessaoController.isLogado()) {
            System.out.print("ID ou Matrícula: ");
            String id = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            Usuario usuario = SessaoController.iniciarSessao(id, senha); 

            if (usuario != null) {
                SessaoController.login(usuario);
                Utils.limparTela();
                System.out.println("✅ Bem-vindo, " + usuario.getNome() + "! Você está logado.");
                abrirView(usuario);
            } else {
                Utils.limparTela();
                System.out.println("❌ Credenciais inválidas! Tente novamente.");
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