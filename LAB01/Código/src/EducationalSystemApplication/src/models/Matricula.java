package models;

import models.enums.StatusMatricula;

public class Matricula {
    private String codigo;
    private Aluno aluno;
    private Disciplina disciplina;
    private StatusMatricula status;
    private double valor;

    public Matricula(String codigo, Aluno aluno, Disciplina disciplina) {
        this.codigo = codigo;
        this.aluno = aluno;
        this.disciplina = disciplina;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public StatusMatricula getStatus() {
        return status;
    }

    public void setStatus(StatusMatricula status) {
        this.status = status;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Matrícula: " + aluno.getNome() + " em " + disciplina.getNome() + " - Status: " + status;
    }
}