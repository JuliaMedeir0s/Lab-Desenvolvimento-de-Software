package models;

import java.util.ArrayList;
import java.util.List;

import models.abstracts.Usuario;
import models.enums.TipoUsuario;

public class Aluno extends Usuario {
    private Curso curso;
    private List<Matricula> matriculas;

    public Aluno(String id, String nome, String email, String senha, Curso curso) {
        super(id, nome, email, senha, TipoUsuario.ALUNO);
        this.curso = curso;
        this.matriculas = new ArrayList<>();
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    @Override
    public String toString() {
        return super.toString() + ", Curso: " + (curso != null ? curso.getNome() : "Nenhum") + 
               ", Disciplinas Matriculadas: " + (matriculas != null ? matriculas.size() : 0);
    }
}
