package models;

import models.enums.Status;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final int MAX_ALUNOS = 60;
    private static final int MIN_ALUNOS = 3;

    private String codigo;
    private String nome;
    private int cargaHoraria;
    private Professor professor;
    private double valor;
    private List<Aluno> alunosMatriculados;
    private List<Curso> cursos;
    private Status status;

    public Disciplina(String codigo, String nome, int cargaHoraria, Professor professor, double valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.valor = valor;
        this.alunosMatriculados = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.status = Status.ATIVO;
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

    public Status getStatus() {
        return status;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        if (cursos == null) {
            this.cursos = new ArrayList<>(); 
            
        } else {
            this.cursos = new ArrayList<>(cursos); 
        }    
    }

    public void setAtiva(boolean ativa) {
        this.status = ativa ? Status.ATIVO : Status.INATIVO;
    }

    @Override
    public String toString() {
        String cursosAssociados = cursos.isEmpty() ? "Nenhum curso associado" :
                String.join(", ", cursos.stream().map(Curso::getNome).toList());
        return String.format("Disciplina: %s (%s), Carga Horária: %d, Professor: %s, Valor: %.2f, Cursos: [%s]",
                nome, codigo, cargaHoraria, 
                professor != null ? professor.getNome() : "Nenhum",
                valor, cursosAssociados);
    }
}
