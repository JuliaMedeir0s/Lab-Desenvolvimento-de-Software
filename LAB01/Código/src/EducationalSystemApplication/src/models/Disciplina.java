package models;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private static final int MAX_ALUNOS = 60;
    private static final int MIN_ALUNOS = 3;

    private String codigo;
    private String nome;
    private int cargaHoraria;
    private Professor professor;
    private double valor;
    private List<Aluno> alunosMatriculados;

    public Disciplina(String codigo, String nome, int cargaHoraria, Professor professor, double valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.valor = valor;
        this.alunosMatriculados = new ArrayList<>();
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

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    @Override
    public String toString() {
        return "Disciplina: " + nome + " (" + codigo + "), Professor: " + professor.getNome() + ", Alunos: " + alunosMatriculados.size();
    }
}
