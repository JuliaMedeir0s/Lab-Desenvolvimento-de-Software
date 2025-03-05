package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import models.abstracts.Usuario;
import models.enums.TipoUsuario;

public class Secretaria extends Usuario {
    private static final long serialVersionUID = 1L;
    private List<Aluno> alunos;
    private List<Professor> professores;
    private List<Disciplina> disciplinas;
    private List<Curso> cursos;
    private Semestre semestreAtual;

    public Secretaria(String id, String nome, String email, String senha) {
        super(id, nome, email, senha, TipoUsuario.SECRETARIA);
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.semestreAtual = null;
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void adicionarProfessor(Professor professor) {
        professores.add(professor);
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    public void adicionarCurso(Curso curso) {
        cursos.add(curso);
    }

    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
    }

    public void removerProfessor(Professor professor) {
        professores.remove(professor);
    }

    public void removerDisciplina(Disciplina disciplina) {
        disciplinas.remove(disciplina);
    }

    public void removerCurso(Curso curso) {
        cursos.remove(curso);
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setSemestreAtual(Semestre semestreAtual) {
        this.semestreAtual = semestreAtual;
    }

    public Semestre getSemestreAtual() {
        return semestreAtual;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
