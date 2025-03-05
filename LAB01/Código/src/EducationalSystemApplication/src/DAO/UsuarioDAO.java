package DAO;

import java.util.List;

import models.abstracts.Usuario;

public class UsuarioDAO extends AbstractDao<Usuario> {
    private static final String FILE_NAME = "usuarios.dat";
    private static UsuarioDAO instancia = new UsuarioDAO();
    private List<Usuario> usuarios;

    private UsuarioDAO() {
        super(FILE_NAME);
        usuarios = leitura();
    }

    public static UsuarioDAO getInstance() {
        return instancia;
    }

    public Usuario autenticar(String id, String senha) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        grava(usuarios); 
        System.out.println("✅ Usuário " + usuario.getNome() + " (" + usuario.getTipoUsuario() + ") adicionado.");
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }
}
