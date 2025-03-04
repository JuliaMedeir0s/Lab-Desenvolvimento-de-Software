package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.abstracts.Usuario;
import models.enums.TipoUsuario;

public class Secretaria extends Usuario {
    private List<Aluno> alunos;
    private List<Professor> professores;
    private List<Disciplina> disciplinas;
    private List<Curso> cursos;
    private Semestre semestreAtual;
    private LocalDate inicioInscricao;
    private LocalDate fimInscricao;

    public Secretaria(String nome, String email, String senha) {
        super(nome, email, senha, TipoUsuario.SECRETARIA);
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.semestreAtual = null;
        this.inicioInscricao = null;
        this.fimInscricao = null;
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

    public void setInicioInscricao(LocalDate inicioInscricao) {
        this.inicioInscricao = inicioInscricao;
    }

    public void setFimInscricao(LocalDate fimInscricao) {
        this.fimInscricao = fimInscricao;
    }

    public LocalDate getInicioInscricao() {
        return inicioInscricao;
    }

    public LocalDate getFimInscricao() {
        return fimInscricao;
    }

    public Semestre getSemestreAtual() {
        return semestreAtual;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static Secretaria getInstance() {
        throw new UnsupportedOperationException("Unimplemented method 'getInstance'");
    }
}
