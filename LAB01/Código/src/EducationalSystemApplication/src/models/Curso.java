package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import models.enums.Status;

public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String codigo;
    private String nome;
    private int creditos;
    private List<Disciplina> disciplinas;
    private List<Disciplina> disciplinasOptativas;
    private List<Aluno> alunosMatriculados;
    private Status status;

    public Curso(String codigo, String nome, int creditos) {
        this.codigo = codigo;
        this.nome = nome;
        this.creditos = creditos;
        this.disciplinas = new ArrayList<>();
        this.disciplinasOptativas = new ArrayList<>();
        this.alunosMatriculados = new ArrayList<>();
        this.status = Status.ATIVO;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCreditos() {
        return this.creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public List<Disciplina> getDisciplinas() {
        return this.disciplinas;
    }

    public List<Disciplina> getDisciplinasOptativas() {
        return this.disciplinasOptativas;
    }

    public List<Aluno> getAlunosMatriculados() {
        return this.alunosMatriculados;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public void setDisciplinasOptativas(List<Disciplina> disciplinasOptativas) {
        this.disciplinasOptativas = disciplinasOptativas;
    }

    public void setAlunosMatriculados(List<Aluno> alunosMatriculados) {
        this.alunosMatriculados = alunosMatriculados;
    }


    @Override
    public String toString() {
        return "Curso: " + nome + " (" + codigo + "), Créditos: " + creditos +
                ", Disciplinas: " + disciplinas.size() + ", Alunos: " + alunosMatriculados.size() +
                " [" + status + "]";
    }
}
