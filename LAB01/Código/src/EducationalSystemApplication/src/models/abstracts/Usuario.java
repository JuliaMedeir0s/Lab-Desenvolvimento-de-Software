package models.abstracts;

import java.io.Serializable;

import models.enums.Status;
import models.enums.TipoUsuario;

public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
    private Status status;

    public Usuario(String id, String nome, String email, String senha, TipoUsuario tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.status = Status.ATIVO;
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

    public Status getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isAtivo() {
        return status == Status.ATIVO;
    }

    public void setAtivo(boolean ativo) {
        if(ativo) {
            status = Status.ATIVO;
        } else {
            status = Status.INATIVO;
        }
    }

    @Override
    public String toString() {
        return id + "," + nome + "," + email + "," + tipoUsuario + "," + status;
    }
}
