package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Semestre {
    private int ano;
    private int periodo;
    private LocalDate inicioInscricoes;
    private LocalDate fimInscricoes;
    private List<Disciplina> disciplinasOfertadas;

    public Semestre(int ano, int periodo, LocalDate dataInicio, LocalDate dataFim) {
        this.ano = ano;
        this.periodo = periodo;
        this.inicioInscricoes = dataInicio;
        this.fimInscricoes = dataFim;
        this.disciplinasOfertadas = new ArrayList<>();
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

    public void adicionarDisciplina(Disciplina disciplina){
        disciplinasOfertadas.add(disciplina);
    }

    public void removerDisciplina(Disciplina disciplina){
        disciplinasOfertadas.remove(disciplina);
    }

    //public boolean estaDentroDoPeriodo(LocalDate dataAtual){
        //return !dataAtual.isBefore(inicioInscricoes) && !dataAtual.isAfter(fimInscricoes);
    //}

    @Override
    public String toString() {
        return "Semestre " + ano + "." + periodo + " | Inscrições: " + inicioInscricoes + " a " + fimInscricoes;
    }
}
