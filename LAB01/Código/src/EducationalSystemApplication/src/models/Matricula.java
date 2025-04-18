package models;

import models.enums.StatusMatricula;
import java.io.Serializable;

public class Matricula implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigo;
    private Aluno aluno;
    private Disciplina disciplina;
    private StatusMatricula status;
    private double valor;

    public Matricula(String Codigo, Aluno aluno, Disciplina disciplina)  {
        this.codigo = Codigo;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.status = StatusMatricula.ATIVA;
        this.valor = disciplina.getValor();
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