package DAO;

import java.util.List;
import java.util.stream.Collectors;
import models.Professor;
import models.enums.Status;

public class ProfessorDAO extends AbstractDao<Professor> {
    private static final String FILE_NAME = "professores.dat";
    private static ProfessorDAO instancia = new ProfessorDAO();
    private List<Professor> professores;

    private ProfessorDAO() {
        super(FILE_NAME);
        professores = leitura(); 
    }

    public static ProfessorDAO getInstance() {
        return instancia;
    }

    public void adicionarProfessor(Professor professor) {
        professores.add(professor);
        grava(professores); 
    }

    public List<Professor> listarProfessores() {
        return professores;
    }

    public List<Professor> listarProfessoresAtivos() {
        return professores.stream()
                .filter(professor -> professor.getStatus() == Status.ATIVO)
                .collect(Collectors.toList());
    }
}