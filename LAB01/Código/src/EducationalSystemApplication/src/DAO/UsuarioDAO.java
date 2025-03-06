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
        List<Usuario> usuarios = listarUsuarios(); 
        
        return usuarios.stream()
                .filter(u -> u.getId().equalsIgnoreCase(id) && u.getSenha().equals(senha))
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

    // public void imprimirUsuarios() {
    //     List<Usuario> usuarios = listarUsuarios();
    
    //     System.out.println("\n=== Lista de Usuários Cadastrados ===");
    //     if (usuarios.isEmpty()) {
    //         System.out.println("Nenhum usuário cadastrado.");
    //     } else {
    //         System.out.println(" ID        | Nome                | E-mail               | Tipo        | Status   | Senha ");
    //         System.out.println("----------------------------------------------------------------------------------------------");
    //         for (Usuario usuario : usuarios) {
    //             System.out.printf(" %-8s | %-20s | %-20s | %-10s | %-8s | %s \n",
    //                     usuario.getId(), usuario.getNome(), usuario.getEmail(),
    //                     usuario.getTipoUsuario(), usuario.getStatus(), usuario.getSenha());
    //         }
    //     }
    // }    
}
