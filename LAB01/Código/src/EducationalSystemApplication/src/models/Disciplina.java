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

    public boolean adicionarAluno(Aluno aluno) {
        if (alunosMatriculados.size() < MAX_ALUNOS) { //verifica se não atingiu a lotação máxima antes
            alunosMatriculados.add(aluno);
            return true;
        }
        System.out.println("A disciplina " + nome + " já atingiu o limite de alunos!");
        return false;
    }

    public void removerAluno(Aluno aluno) {
        alunosMatriculados.remove(aluno);
    }

    public boolean verificarLotacao() { //verifica se o máximo de alunos foi atingido
        return alunosMatriculados.size() >= MAX_ALUNOS;
    }

    public boolean verificarMinimoAlunos() { //verifica se chegou a mínimo de alunos matriculados
        return alunosMatriculados.size() >= MIN_ALUNOS;
    }

    public void listarAlunos() {
        if (alunosMatriculados.isEmpty()) {
            System.out.println("Nenhum aluno matriculado na disciplina " + nome);
        } else {
            System.out.println("Alunos matriculados em " + nome + ":");
            for (Aluno aluno : alunosMatriculados) {
                System.out.println("- " + aluno.getNome());
            }
        }
    }

    @Override
    public String toString() {
        return "Disciplina: " + nome + " (" + codigo + "), Professor: " + professor.getNome() + ", Alunos: " + alunosMatriculados.size();
    }
}
