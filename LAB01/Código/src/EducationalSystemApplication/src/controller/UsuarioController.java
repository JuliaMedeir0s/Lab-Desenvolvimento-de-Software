package controller;

import java.util.List;

import DAO.UsuarioDAO;
import models.abstracts.Usuario;

public class UsuarioController {
    private UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();

    public boolean login(String id, String senha) {
        Usuario usuario = usuarioDAO.autenticar(id, senha);
        if (usuario != null) {
            SessaoController.login(usuario);
            return true;
        }
        return false;
    }

    public void logout() {
        SessaoController.logout();
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }
}
