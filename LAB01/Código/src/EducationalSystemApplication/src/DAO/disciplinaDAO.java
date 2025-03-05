package DAO;

import java.util.ArrayList;
import java.util.List;

import models.Disciplina;

public class disciplinaDAO {
    private static List<Disciplina> disciplinas = new ArrayList<>();
    private static disciplinaDAO instance;

    private disciplinaDAO() {

    }

    public static disciplinaDAO getInstance() {
        if (instance == null) {
            instance = new disciplinaDAO();
        }
        return instance;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    public void removerDisciplina(Disciplina disciplina) {
        disciplinas.remove(disciplina);
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
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
