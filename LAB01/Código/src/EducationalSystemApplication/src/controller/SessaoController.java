package controller;

import DAO.UsuarioDAO;
import models.Secretaria;
import models.abstracts.Usuario;
import models.enums.TipoUsuario;

public class SessaoController {
    private static Usuario usuarioLogado = null;

    public static void iniciarSessao() {
        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
        verificarUsuarioPadrao(usuarioDAO);
    }

    private static void verificarUsuarioPadrao(UsuarioDAO usuarioDAO) {
        //ve se ja tem um usuario padrão criado
        boolean secretariaExiste = usuarioDAO.listarUsuarios().stream()
            .anyMatch(u -> u.getTipoUsuario() == TipoUsuario.SECRETARIA);
        //se não tiver cria o usuario padrão
    if (!secretariaExiste) {
        Secretaria admin = new Secretaria("SEC-0000", "Admin", "admin@email.com", "admin123");
        usuarioDAO.adicionarUsuario(admin);
        System.out.println("✅ Usuário padrão criado: SEC-0000 / admin123");
    }
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
}
