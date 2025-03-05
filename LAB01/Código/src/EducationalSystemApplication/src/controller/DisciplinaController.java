package controller;

import DAO.DisciplinaDAO;
import DAO.ProfessorDAO;
import models.Disciplina;
import models.Professor;
import models.enums.Status;
import java.util.List;
import java.util.Optional;

public class DisciplinaController {
    private final DisciplinaDAO disciplinaDAO = DisciplinaDAO.getInstance();
    private final ProfessorDAO professorDAO = ProfessorDAO.getInstance();

    public boolean adicionarDisciplina(String nome, String codigo, int cargaHoraria, Professor professor, double valor) {
        if (disciplinaDAO.buscarPorCodigo(codigo).isPresent()) {
            return false;
        }
        Disciplina novaDisciplina = new Disciplina(codigo, nome, cargaHoraria, professor, valor);
        disciplinaDAO.adicionarDisciplina(novaDisciplina);
        return true;
    }

    public boolean editarNomeDisciplina(String codigo, String novoNome) {
        Optional<Disciplina> disciplinaOpt = disciplinaDAO.buscarPorCodigo(codigo);
        if (disciplinaOpt.isPresent()) {
            Disciplina disciplina = disciplinaOpt.get();
            disciplina.setNome(novoNome);
            disciplinaDAO.atualizarDisciplina(disciplina);
            return true;
        }
        return false;
    }
    
    public boolean editarCargaHorariaDisciplina(String codigo, int novaCargaHoraria) {
        Optional<Disciplina> disciplinaOpt = disciplinaDAO.buscarPorCodigo(codigo);
        if (disciplinaOpt.isPresent()) {
            Disciplina disciplina = disciplinaOpt.get();
            disciplina.setCargaHoraria(novaCargaHoraria);
            disciplinaDAO.atualizarDisciplina(disciplina);
            return true;
        }
        return false;
    }
    
    public boolean editarProfessorDisciplina(String codigo, Professor novoProfessor) {
        Optional<Disciplina> disciplinaOpt = disciplinaDAO.buscarPorCodigo(codigo);
        if (disciplinaOpt.isPresent()) {
            Disciplina disciplina = disciplinaOpt.get();
            disciplina.setProfessor(novoProfessor);
            disciplinaDAO.atualizarDisciplina(disciplina);
            return true;
        }
        return false;
    }
    
    public boolean editarValorDisciplina(String codigo, double novoValor) {
        Optional<Disciplina> disciplinaOpt = disciplinaDAO.buscarPorCodigo(codigo);
        if (disciplinaOpt.isPresent()) {
            Disciplina disciplina = disciplinaOpt.get();
            disciplina.setValor(novoValor);
            disciplinaDAO.atualizarDisciplina(disciplina);
            return true;
        }
        return false;
    }

    public boolean alterarStatusDisciplina(String codigo) {
        Optional<Disciplina> disciplinaOpt = disciplinaDAO.buscarPorCodigo(codigo);
        if (disciplinaOpt.isPresent()) {
            Disciplina disciplina = disciplinaOpt.get();
            disciplina.setStatus(disciplina.getStatus() == Status.ATIVO ? Status.INATIVO : Status.ATIVO);
            disciplinaDAO.atualizarDisciplina(disciplina);
            return true;
        }
        return false;
    }

    public void listarDisciplinas() {
        List<Disciplina> disciplinas = disciplinaDAO.listarDisciplinas();
        if (disciplinas.isEmpty()) {
            System.out.println("📌 Nenhuma disciplina cadastrada.");
        } else {
            System.out.println("\n=== Lista de Disciplinas ===");
            disciplinas.forEach(System.out::println);
        }
    }

    public List<Professor> listarProfessoresAtivos() {
        return professorDAO.listarProfessores().stream()
                .filter(professor -> professor.getStatus() == Status.ATIVO)
                .toList();
    }
}
