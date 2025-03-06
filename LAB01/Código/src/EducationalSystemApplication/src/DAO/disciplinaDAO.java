package DAO;

import models.Disciplina;
import models.enums.Status;

import java.util.List;
import java.util.Optional;

import controller.DisciplinaController;

import java.util.Comparator;

public class DisciplinaDAO extends AbstractDao<Disciplina> {

    private static final String FILE_NAME = "disciplinas.dat";
    private static DisciplinaDAO instancia;
    private List<Disciplina> disciplinas;

    private DisciplinaDAO() {
        super(FILE_NAME);
        disciplinas = leitura();
        atualizarContadorCodigo();
    }

    public static DisciplinaDAO getInstance() {
        if (instancia == null) {
            instancia = new DisciplinaDAO();
        }
        return instancia;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
        grava(disciplinas);
        atualizarContadorCodigo();
    }

    public Optional<Disciplina> buscarPorCodigo(String codigo) {
        return disciplinas.stream().filter(d -> d.getCodigo().equals(codigo)).findFirst();
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinas;
    }

    public void atualizarDisciplina(Disciplina disciplinaAtualizada) {
        for (int i = 0; i < disciplinas.size(); i++) {
            if (disciplinas.get(i).getCodigo().equals(disciplinaAtualizada.getCodigo())) {
                disciplinas.set(i, disciplinaAtualizada);
                grava(disciplinas);
                return;
            }
        }
    }

    public boolean alterarStatusDisciplina(String codigo) {
        Optional<Disciplina> disciplinaOpt = buscarPorCodigo(codigo);
        if (disciplinaOpt.isPresent()) {
            Disciplina disciplina = disciplinaOpt.get();
            disciplina.setStatus(disciplina.getStatus()== Status.ATIVO ? Status.INATIVO : Status.ATIVO);
            atualizarDisciplina(disciplina);
            return true;
        }
        return false;
    }

    private void atualizarContadorCodigo() {
        if (!disciplinas.isEmpty()) {
            int maiorCodigo = disciplinas.stream()
                    .map(d -> Integer.parseInt(d.getCodigo().split("-")[1]))
                    .max(Comparator.naturalOrder())
                    .orElse(0);
            DisciplinaController.setContadorDisciplina(maiorCodigo + 1);
        }
    }
}