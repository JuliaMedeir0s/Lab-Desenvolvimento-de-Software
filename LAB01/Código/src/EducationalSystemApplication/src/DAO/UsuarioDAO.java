package DAO;

import java.util.List;

import models.Secretaria;
import models.abstracts.Usuario;
import models.enums.TipoUsuario;

public class UsuarioDAO extends AbstractDao<Usuario> {
    private static final String FILE_NAME = "usuarios.dat";
    private static UsuarioDAO instancia = new UsuarioDAO();
    private List<Usuario> usuarios;

    private UsuarioDAO() {
        super(FILE_NAME);
        usuarios = leitura();
        //criar um perfil padrão pra secretaria
        verificarAcessoPadrao();
    }

    public static UsuarioDAO getInstance() {
        return instancia;
    }

    private void verificarAcessoPadrao() {
        boolean secretariaExiste = usuarios.stream()
                .anyMatch(u -> u.getTipoUsuario() == TipoUsuario.SECRETARIA);

        if (!secretariaExiste) {
            Secretaria admin = new Secretaria("SEC-0001", "Admin", "admin@email.com", "admin123");
            usuarios.add(admin);
            grava(usuarios);
            System.out.println("Secretaria padrão criada: admin / admin123");
        }
    }

    public Usuario autenticar(String id, String senha) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }
}
