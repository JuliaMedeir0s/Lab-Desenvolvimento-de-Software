package views;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.AlunoController;
import models.Aluno;
import models.Professor;
import models.Secretaria;
import models.abstracts.Usuario;

public class loginView {
    private static List<Usuario> usuarios = new ArrayList<>();
    public static void main(String[] args) {
        //usuários de teste
        inicializarUsuarios();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Sistema de Matrículas ===");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        Usuario usuarioLogado = autenticarUsuario(email, senha);

        // tem que verificar o tipo de quem está logado
        if (usuarioLogado != null) {
            System.out.println("\nBem-vindo, " + usuarioLogado.getNome() + "!");

            if (usuarioLogado instanceof Aluno) {
                AlunoController alunoController = new AlunoController((Aluno) usuarioLogado);
                AlunoView.menu((Aluno) usuarioLogado);
            } else if (usuarioLogado instanceof Professor) {
                ProfessorController professorController = new ProfessorController((Professor) usuarioLogado);
                ProfessorView.menu((Professor) usuarioLogado);
            } else if (usuarioLogado instanceof Secretaria) {
                SecretariaController secretariaController = new SecretariaController((Secretaria) usuarioLogado);
                SecretariaView.menu((Secretaria) usuarioLogado);
            }
        } else {
            System.out.println("\nLogin falhou! Verifique suas credenciais.");
        }

        scanner.close();
    }
    // usuários pra fazer um teste
    private static void inicializarUsuarios() {
        usuarios.add(new Aluno("Lucas", "lucas@email.com", "1234", "2023001", null));
        usuarios.add(new Professor("Carlos", "carlos@email.com", "prof123"));
        usuarios.add(new Secretaria("Ana", "ana@email.com", "admin123"));
    }

    private static Usuario autenticarUsuario(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }
}