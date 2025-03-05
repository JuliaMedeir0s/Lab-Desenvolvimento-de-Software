package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import models.Disciplina;

public class disciplinaDAO extends AbstractDao implements Serializable {
    
    private static List<Disciplina> disciplinas = new ArrayList<>();
    private static disciplinaDAO instance = new disciplinaDAO();

    private static final String CAMINHO_DISCIPLINA = "disciplinas.dat";

    private disciplinaDAO() {
        super(CAMINHO_DISCIPLINA);
        this.disciplinas = new ArrayList<>();
        carregarDisciplinas();
    }

    public static disciplinaDAO getInstance() {
        if (instance == null) {
            instance = new disciplinaDAO();
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
