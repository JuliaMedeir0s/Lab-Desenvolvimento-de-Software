package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import models.Disciplina;

public class DisciplinaDAO extends AbstractDao implements Serializable {
    
    private static List<Disciplina> disciplinas = new ArrayList<>();
    private static DisciplinaDAO instance = new DisciplinaDAO();

    private static final String CAMINHO_DISCIPLINA = "disciplinas.dat";

    private DisciplinaDAO() {
        super(CAMINHO_DISCIPLINA);
        this.disciplinas = new ArrayList<>();
        carregarDisciplinas();
    }

    public static DisciplinaDAO getInstance() {
        if (instance == null) {
            instance = new DisciplinaDAO();
        }
        return instance;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
        grava(disciplinas);
    }

    public void removerDisciplina(Disciplina disciplina) {
        disciplinas.remove(disciplina);
        grava(disciplinas);
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    private void carregarDisciplinas() {
        this.disciplinas = leitura();
    }

    public Disciplina buscarPorCodigo(String codigo) {
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getCodigo().equals(codigo)) {
                return disciplina;
            }
        }
        return null;
    }
}
