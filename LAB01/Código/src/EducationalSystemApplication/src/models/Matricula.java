package models;

import models.enums.StatusMatricula;

public class Matricula {
    private Aluno aluno;
    private Disciplina disciplina;
    private StatusMatricula status;
    private double valor;

    public Matricula(Aluno aluno, Disciplina disciplina) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.status = StatusMatricula.ATIVA;
        this.valor = disciplina.getValor();
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public StatusMatricula getStatus() {
        return status;
    }

    public double getValor() {
        return valor;
    }

    public boolean confirmarMatricula() {
        if (disciplina.adicionarAluno(aluno)) {
            this.status = StatusMatricula.ATIVA;
            return true;
        }
        return false;
    }

    public boolean cancelarMatricula() {
        if (this.status == StatusMatricula.ATIVA) {
            disciplina.removerAluno(aluno);
            this.status = StatusMatricula.CANCELADA;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Matrícula: " + aluno.getNome() + " em " + disciplina.getNome() + " - Status: " + status;
    }
}
