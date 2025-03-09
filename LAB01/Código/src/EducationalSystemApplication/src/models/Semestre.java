package models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Semestre implements Serializable {
    private static final long serialVersionUID = 1L;

    private int ano;
    private int periodo;
    private boolean fechado;
    private LocalDate inicioInscricoes;
    private LocalDate fimInscricoes;
    private List<Disciplina> disciplinasOfertadas;

    public Semestre(int ano, int periodo, LocalDate inicio, LocalDate fim) {
        this.ano = ano;
        this.periodo = periodo;
        this.inicioInscricoes = inicio;
        this.fimInscricoes = fim;
        this.fechado = false;
        this.disciplinasOfertadas = new ArrayList<>();
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinasOfertadas.add(disciplina);
    }

    public void removerDisciplina(Disciplina disciplina) {
        disciplinasOfertadas.remove(disciplina);
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getAno() {
        return ano;
    }

    public int getPeriodo() {
        return periodo;
    }

    public LocalDate getDataInicioInscricoes() {
        return inicioInscricoes;
    }

    public LocalDate getDataFimInscricoes() {
        return fimInscricoes;
    }

    public List<Disciplina> getDisciplinasOfertadas() {
        return disciplinasOfertadas;
    }

    public void setDataInicioInscricoes(LocalDate inicio) {
        this.inicioInscricoes = inicio;
    }

    public void setDataFimInscricoes(LocalDate fim) {
        this.fimInscricoes = fim;
    }

    public void setFechado() {
        this.fechado = true;
    }

    public boolean isFechado() {
        return fechado;
    }

    public void abrirInscricoes() {
        this.fechado = false;
    }

    @Override
    public String toString() {
        return "Semestre " + ano + "." + periodo + " | Inscrições: " + inicioInscricoes + " a " + fimInscricoes +
                " | Disciplinas: " + disciplinasOfertadas.size();
    }
}
