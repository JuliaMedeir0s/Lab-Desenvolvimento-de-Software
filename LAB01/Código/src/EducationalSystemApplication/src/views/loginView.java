package views;

import java.util.Scanner;

import DAO.UsuarioDAO;
import controller.SessaoController;
import models.abstracts.Usuario;

public class LoginView {
    public static void mostrarLogin() {
        Scanner scanner = new Scanner(System.in);
        // precisa salvar o usuário que iniciou a sessão
        while (!SessaoController.isLogado()) {
            System.out.print("ID ou Matrícula: ");
            String id = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
            Usuario usuario = usuarioDAO.autenticar(id, senha);
            if (usuario != null) {
                SessaoController.login(usuario);
                System.out.println("Bem-vindo, " + usuario.getNome() + "! Você está logado.");
                abrirView(usuario);
            } else {
                System.out.println("Credenciais inválidas! Tente novamente.");
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