package models;

import models.enums.Status;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigo;
    private String nome;
    private int cargaHoraria;
    private Professor professor;
    private double valor;
    private List<Matricula> matriculas;
    private List<Curso> cursos;
    private Status status;
    private static final int MATRICULA_MINIMA = 3;
    private static final int MATRICULA_MAXIMA = 60;

    public Disciplina(String codigo, String nome, int cargaHoraria, Professor professor, double valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.valor = valor;
        this.matriculas = new ArrayList<>();
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

    public static int getMatriculaMinima() {
        return MATRICULA_MINIMA;
    }

    public static int getMatriculaMaxima() {
        return MATRICULA_MAXIMA;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos == null ? new ArrayList<>() : new ArrayList<>(cursos);
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void adicionarMatricula(Matricula matricula) {
        matriculas.add(matricula);
    }

    public void removerMatricula(Matricula matricula) {
        matriculas.remove(matricula);
    }

    public void adicionarCurso(Curso curso) {
        cursos.add(curso);
    }

    public void removerCurso(Curso curso) {
        cursos.remove(curso);
    }

    public boolean isMatriculasCheias() {
        return matriculas.size() >= MATRICULA_MAXIMA;
    }

    public boolean isMatriculasMinimas() {
        return matriculas.size() >= MATRICULA_MINIMA;
    }

    @Override
    public String toString() {
        return String.format(
                "Disciplina: %s (%s), Carga Horária: %d, Professor: %s, Valor: %.2f, Min: %d, Max: %d, Status: %s",
                nome, codigo, cargaHoraria,
                professor != null ? professor.getNome() : "Nenhum",
                valor, MATRICULA_MINIMA, MATRICULA_MAXIMA, status);
    }
}