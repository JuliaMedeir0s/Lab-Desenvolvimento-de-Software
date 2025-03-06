package DAO;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import controller.ProfessorController;
import models.Professor;

public class ProfessorDAO extends AbstractDao<Professor> {
    private static final String FILE_NAME = "professores.dat";
    private static ProfessorDAO instancia = new ProfessorDAO();
    private List<Professor> professores;

    private ProfessorDAO() {
        super(FILE_NAME);
        professores = leitura();
        atualizarContadorID();
    }

    public static ProfessorDAO getInstance() {
        return instancia;
    }

    public void adicionarProfessor(Professor professor) {
        professores.add(professor);
        grava(professores);
        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
        usuarioDAO.adicionarUsuario(professor);
    }

    public Optional<Professor> buscarPorId(String id) {
        return professores.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Optional<Professor> buscarPorEmail(String email) {
        return professores.stream().filter(p -> p.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    public List<Professor> listarProfessores() {
        return professores;
    }

    public void atualizarProfessor(Professor professorAtualizado) {
        for (int i = 0; i < professores.size(); i++) {
            if (professores.get(i).getId().equals(professorAtualizado.getId())) {
                professores.set(i, professorAtualizado);
                grava(professores);
                return;
            }
        }
    }

    private void atualizarContadorID() {
        if (!professores.isEmpty()) {
            int maiorID = professores.stream()
                    .map(p -> Integer.parseInt(p.getId().split("-")[1])) 
                    .max(Comparator.naturalOrder()) 
                    .orElse(0);
            ProfessorController.setContadorProfessor(maiorID + 1); 
        }
    }
}