package models.abstracts;

import models.enums.StatusUsuario;
import models.enums.TipoUsuario;

public abstract class Usuario {
    private String id;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
    private StatusUsuario status;

    public Usuario(String id, String nome, String email, String senha, TipoUsuario tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.status = StatusUsuario.ATIVO;
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
        return id + "," + nome + "," + email + "," + status;
    }
}
