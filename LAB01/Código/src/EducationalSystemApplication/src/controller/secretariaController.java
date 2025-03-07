package controller;

import DAO.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import models.Secretaria;

public class SecretariaController {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final AtomicInteger contadorSecretaria = new AtomicInteger(1);

    private final SecretariaDAO secretariaDAO = SecretariaDAO.getInstance();

    public boolean adicionarUsuario(String nome, String email, String senha) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            System.out.println("❌ Erro: E-mail inválido.");
            return false;
        }
    
        String id = "SEC-" + String.format("%04d", contadorSecretaria.getAndIncrement());
        Secretaria novaSecretaria = new Secretaria(id, nome, email, senha);
        return secretariaDAO.adicionarSecretaria(novaSecretaria);
    }
    
    public boolean editarUsuario(String id, String novoNome, String novoEmail, String novaSenha) {
        Secretaria secretaria = secretariaDAO.buscarSecretariaPorId(id);
        if (secretaria == null) {
            System.out.println("❌ Erro: Usuário não encontrado.");
            return false;
        }
    
        if (!novoEmail.isEmpty() && !EMAIL_PATTERN.matcher(novoEmail).matches()) {
            System.out.println("❌ Erro: E-mail inválido.");
            return false;
        }
    
        if (!novoNome.isEmpty()) {
            secretaria.setNome(novoNome);
        }
        if (!novoEmail.isEmpty()) {
            secretaria.setEmail(novoEmail);
        }
        if (!novaSenha.isEmpty()) {
            secretaria.setSenha(novaSenha);
        }
    
        return secretariaDAO.atualizarSecretaria(secretaria);
    }
    
    public boolean alterarStatusUsuario(String id) {
        Secretaria secretaria = secretariaDAO.buscarSecretariaPorId(id);
        if (secretaria == null) {
            System.out.println("❌ Erro: Usuário não encontrado.");
            return false;
        }
    
        if (secretariaDAO.getContadorSecretarias() == 1) {
            System.out.println("❌ Erro: Não é possível desativar o único usuário administrador.");
            return false;
        }
    
        secretaria.setAtivo(!secretaria.isAtivo());
        return secretariaDAO.atualizarSecretaria(secretaria);
    }
    
    public void listarUsuarios() {
        List<Secretaria> secretarias = secretariaDAO.listarSecretarias();
        if (secretarias.isEmpty()) {
            System.out.println("📌 Nenhum usuário cadastrado.");
            return;
        }
    
        System.out.println("\n=== Lista de Usuários ===");
        System.out.println("ID       | Nome                 | E-mail                | Status");
        System.out.println("--------------------------------------------------------------");
        for (Secretaria s : secretarias) {
            System.out.printf("%-8s | %-20s | %-25s | %s\n", 
                              s.getId(), s.getNome(), s.getEmail(), 
                              s.isAtivo() ? "ATIVO" : "INATIVO");
        }
    }
    

}