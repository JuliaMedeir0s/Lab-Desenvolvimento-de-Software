package controller;

import java.time.LocalDate;
import java.util.List;

import models.Disciplina;
import models.Semestre;

public class SemestreController {

    public void listarDisciplinas(Semestre semestre) {
        List<Disciplina> disciplinasOfertadas = semestre.getDisciplinasOfertadas();
        System.out.println("Disciplinas ofertadas no semestre " + semestre.getAno() + "." + semestre.getPeriodo() + ":");
        for (Disciplina d : disciplinasOfertadas) {
            System.out.println("- " + d.getNome() + " (" + d.getCodigo() + ")");
        }
    }

    public void adicionarDisciplina(Semestre semestre, Disciplina disciplina) {
        semestre.adicionarDisciplina(disciplina);
    }

    public void removerDisciplina(Semestre semestre, Disciplina disciplina) {
        semestre.removerDisciplina(disciplina);
    }

    public boolean estaDentroDoPeriodo(Semestre semestre, LocalDate dataAtual) {
        return !dataAtual.isBefore(semestre.getDataInicioInscricoes()) && !dataAtual.isAfter(semestre.getDataFimInscricoes());
    }

}
