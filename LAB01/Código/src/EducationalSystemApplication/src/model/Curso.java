package model;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nome;
    private int creditos;
    private List<Disciplina> disciplinas;
    private List<Aluno> alunosMatriculados;

    public Curso(String nome, int creditos){
        this.nome = nome;
        this.creditos = creditos;
        this.disciplinas = new ArrayList<>();
        this.alunosMatriculados = new ArrayList<>();
    }

    public String getNome(){
        return nome;
    }

    public int getCreditos() {
        return this.creditos;
    }

    public List<Disciplina> getDisciplinas() {
        return this.disciplinas;
    }

    public List<Aluno> getAlunosMatriculados() {
        return this.alunosMatriculados;
    }

    public void adicionarDisciplina(Disciplina disciplina){
        disciplinas.add(disciplina);
    }

    public void removerDisciplina(Disciplina disciplina){
        disciplinas.remove(disciplina);
    }

    public void matricularAluno(Aluno aluno){
        alunosMatriculados.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        alunosMatriculados.remove(aluno);
    }

    public void listarDisciplinas() {
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada para este curso.");
        } else {
            System.out.println("Disciplinas do curso " + nome + ":");
            for (Disciplina disciplina : disciplinas) {
                System.out.println("- " + disciplina.getNome());
            }
        }
    }

    public void listarAlunos() {
        if (alunosMatriculados.isEmpty()) {
            System.out.println("Nenhum aluno matriculado neste curso.");
        } else {
            System.out.println("Alunos matriculados no curso " + nome + ":");
            for (Aluno aluno : alunosMatriculados) {
                System.out.println("- " + aluno.getNome());
            }
        }
    }

    @Override
    public String toString() {
        return "Curso: " + nome + ", Créditos: " + creditos + ", Disciplinas: " + disciplinas.size() + ", Alunos: " + alunosMatriculados.size();
    }
}
