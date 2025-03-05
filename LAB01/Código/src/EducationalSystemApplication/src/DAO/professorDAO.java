package DAO;

import java.util.ArrayList;
import java.util.List;
import models.Professor;

public class professorDAO {

    private static List<Professor> professores = new ArrayList<>();
    private static professorDAO instance;

    private professorDAO() {

    }

    public static professorDAO getInstance() {
        if (instance == null) {
            instance = new professorDAO();
        }
        return instance;
    }

    public void adicionarProfessor(Professor professor) {
        professores.add(professor);
    }

    public void removerProfessor(Professor professor) {
        professores.remove(professor);
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public Professor buscarPorNome(String nome) {
        for (Professor professor : professores) {
            if (professor.getNome().equals(nome)) {
                return professor;
            }
        }
        return null;
    }
}
