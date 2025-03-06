package controller;

import java.util.List;

import DAO.UsuarioDAO;
import models.Secretaria;
import models.abstracts.Usuario;
import models.enums.Status;

public class SessaoController {
    private static Usuario usuarioLogado;
    private static final UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();

    public static void verificarUsuarioPadrao() {
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();

        //se não há usuários cadastrados, criamos um padrão
        if (usuarios.isEmpty()) {
            System.out.println("📌 Nenhum usuário encontrado. Criando usuário padrão...");
            Secretaria admin = new Secretaria("SEC-0000", "Administrador", "admin@email.com", "admin123");
            admin.setStatus(Status.ATIVO);
            usuarioDAO.adicionarUsuario(admin);
            System.out.println("✅ Usuário padrão criado: SEC-0000 / admin123");
        }
    }

    public static Usuario iniciarSessao(String id, String senha) {
        Usuario usuario = usuarioDAO.autenticar(id, senha);

        if (usuario == null) {
            System.out.println("❌ Erro: ID ou senha incorretos.");
            return null;
        }

        usuarioLogado = usuario;
        System.out.println("✅ Login realizado com sucesso! Bem-vindo, " + usuario.getNome());
        return usuario;
    }

    public static void login(Usuario usuario) {
        usuarioLogado = usuario;
    }

    public static void encerrarSessao() {
        usuarioLogado = null;
        System.out.println("✅ Sessão encerrada com sucesso!");
    }

    public static boolean isLogado() {
        return usuarioLogado != null;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void logout() {
        encerrarSessao();
    }
}
