package models;

import java.util.ArrayList;
import java.util.List;

import models.abstracts.Usuario;
import models.enums.StatusMatricula;
import models.enums.TipoUsuario;

public class Aluno extends Usuario {
    private String matricula;
    private Curso curso;
    private List<Matricula> matriculas;

    public Aluno(String nome, String email, String senha, String matricula, Curso curso) {
        super(nome, email, senha, TipoUsuario.ALUNO);
        this.matricula = matricula;
        this.curso = curso;
        this.matriculas = new ArrayList<>();
    }

    public String getMatricula() {
        return matricula;
    }

    public Curso getCurso() {
        return curso;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void adicionarMatricula(Matricula matricula) {
        this.matriculas.add(matricula);
    }

    public void removerMatricula(Matricula matricula) {
        this.matriculas.remove(matricula);
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    @Override
    public String toString() {
        return super.toString() + ", Matrícula: " + matricula + ", Curso: " + curso.getNome() + ", Disciplinas Matriculadas: " + matriculas.size();
    }
}
