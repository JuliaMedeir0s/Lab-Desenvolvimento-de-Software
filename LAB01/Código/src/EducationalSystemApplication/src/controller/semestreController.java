package controller;

import java.time.LocalDate;
import java.util.List;
import DAO.SemestreDAO;
import models.Disciplina;
import models.Semestre;

public class SemestreController {
    private final SemestreDAO semestreDAO = SemestreDAO.getInstance();

    public void listarDisciplinas(Semestre semestre) {
        List<Disciplina> disciplinasOfertadas = semestre.getDisciplinasOfertadas();
        System.out.println("Disciplinas ofertadas no semestre " + semestre.getAno() + "." + semestre.getPeriodo() + ":");
        for (Disciplina d : disciplinasOfertadas) {
            System.out.println("- " + d.getNome() + " (" + d.getCodigo() + ")");
        }
    }

    public void adicionarDisciplina(Semestre semestre, Disciplina disciplina) {
        if (!semestre.getDisciplinasOfertadas().contains(disciplina)) {
            semestre.getDisciplinasOfertadas().add(disciplina);
            semestreDAO.atualizarSemestre(semestre);
            System.out.println("✅ Disciplina adicionada ao semestre.");
        } else {
            System.out.println("❌ A disciplina já está ofertada neste semestre.");
        }
    }

    public void removerDisciplina(Semestre semestre, Disciplina disciplina) {
        if (semestre.getDisciplinasOfertadas().contains(disciplina)) {
            semestre.getDisciplinasOfertadas().remove(disciplina);
            semestreDAO.atualizarSemestre(semestre);
            System.out.println("✅ Disciplina removida do semestre.");
        } else {
            System.out.println("❌ A disciplina não está cadastrada neste semestre.");
        }
    }

    public boolean estaDentroDoPeriodo(Semestre semestre, LocalDate dataAtual) {
        return !dataAtual.isBefore(semestre.getDataInicioInscricoes()) && !dataAtual.isAfter(semestre.getDataFimInscricoes());
    }

    public void criarSemestre(int ano, int periodo, LocalDate inicio, LocalDate fim) {
        if (inicio.isBefore(fim)) {
            Semestre novoSemestre = new Semestre(ano, periodo, inicio, fim);
            semestreDAO.adicionarSemestre(novoSemestre);
            System.out.println("✅ Semestre " + ano + "." + periodo + " criado com sucesso.");
        } else {
            System.out.println("❌ Erro: A data de início deve ser antes da data de fim.");
        }
    }

    public Semestre buscarSemestre(int ano, int periodo) {
        return semestreDAO.buscarSemestre(ano, periodo);
    }
}