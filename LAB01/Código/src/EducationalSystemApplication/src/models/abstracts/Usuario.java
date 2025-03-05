package models.abstracts;

import java.util.concurrent.atomic.AtomicInteger;

import models.enums.StatusUsuario;
import models.enums.TipoUsuario;

public abstract class Usuario {
    private static final AtomicInteger contadorAluno = new AtomicInteger(1);
    private static final AtomicInteger contadorProfessor = new AtomicInteger(1);
    private static final AtomicInteger contadorSecretaria = new AtomicInteger(1);

    private String id;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
    private StatusUsuario status;

    public Usuario(String nome, String email, String senha, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.id = gerarId(tipoUsuario);
        this.status = StatusUsuario.ATIVO;
    }

    private String gerarId(TipoUsuario tipo) {
        switch (tipo) {
            case ALUNO:
                return "ALU-" + String.format("%04d", contadorAluno.getAndIncrement());
            case PROFESSOR:
                return "PRO-" + String.format("%04d", contadorProfessor.getAndIncrement());
            case SECRETARIA:
                return "SEC-" + String.format("%04d", contadorSecretaria.getAndIncrement());
            default:
                throw new IllegalArgumentException("Tipo de usuário inválido");
        }
    }

    public void ativarUsuario() {
        this.status = StatusUsuario.ATIVO;
        System.out.println(nome + " foi ativado.");
    }

    public void desativarUsuario() {
        this.status = StatusUsuario.INATIVO;
        System.out.println(nome + " foi desativado.");
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + "," + nome + "," + email + "," + senha + "," + status;
    }
}
