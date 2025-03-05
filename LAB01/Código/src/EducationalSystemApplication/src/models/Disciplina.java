package models;

import models.enums.Status;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final int MAX_ALUNOS = 60;
    private static final int MIN_ALUNOS = 3;

    private String codigo;
    private String nome;
    private int cargaHoraria;
    private Professor professor;
    private double valor;
    private List<Aluno> alunosMatriculados;
    private Status status;

    public Disciplina(String codigo, String nome, int cargaHoraria, Professor professor, double valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.valor = valor;
        this.alunosMatriculados = new ArrayList<>();
        this.status = Status.ATIVO;
    }

    public boolean adicionarAluno(Aluno aluno) {
        if (alunosMatriculados.size() < MAX_ALUNOS) {
            alunosMatriculados.add(aluno);
            return true;
        }
        return false;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }

    public double getValor() {
        return valor;
    }

    public Status getStatus() {
        return status;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    @Override
    public String toString() {
        return "Disciplina: " + nome + " (" + codigo + "), Professor: " +
                (professor != null ? professor.getNome() : "Não definido") +
                ", Alunos: " + alunosMatriculados.size() + " [" + status + "]";
    }
}
