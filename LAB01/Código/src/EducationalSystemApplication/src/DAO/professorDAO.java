package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import models.Professor;

public class ProfessorDAO extends AbstractDao implements Serializable {

    private static List<Professor> professores = new ArrayList<>();
    private static ProfessorDAO instance = new ProfessorDAO();

    private static final String CAMINHO_PRFESSORES = "professores.dat";

    private ProfessorDAO()  {
        super(CAMINHO_PRFESSORES);
        this.professores = new ArrayList<>();
        carregarProfessores();
    }

    public static ProfessorDAO getInstance() {
        if (instance == null) {
            instance = new ProfessorDAO();
        }
        return instance;
    }

    public void adicionarProfessor(Professor professor) {
        professores.add(professor);
        grava(professores);
    }

    public void removerProfessor(Professor professor) {
        professores.remove(professor);
        grava(professores);
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    private void carregarProfessores() {
        this.professores = leitura();
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
