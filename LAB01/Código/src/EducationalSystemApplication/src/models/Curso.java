package models;

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

    public void setNome(String nome) {
        this.nome = nome;
    }  

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public void setAlunosMatriculados(List<Aluno> alunosMatriculados) {
        this.alunosMatriculados = alunosMatriculados;
    }


    @Override
    public String toString() {
        return "Curso: " + nome + ", Créditos: " + creditos + ", Disciplinas: " + disciplinas.size() + ", Alunos: " + alunosMatriculados.size();
    }
}
