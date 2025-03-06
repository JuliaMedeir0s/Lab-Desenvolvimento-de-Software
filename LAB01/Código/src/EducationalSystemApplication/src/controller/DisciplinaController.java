package controller;

import DAO.DisciplinaDAO;
import DAO.ProfessorDAO;
import models.Disciplina;
import models.Professor;
import models.enums.Status;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class DisciplinaController {
    private final DisciplinaDAO disciplinaDAO = DisciplinaDAO.getInstance();
    private final ProfessorDAO professorDAO = ProfessorDAO.getInstance();
    private static final AtomicInteger contadorDisciplina = new AtomicInteger(1);

    public static void setContadorDisciplina(int valor) {
        contadorDisciplina.set(valor);
    }

    public String gerarCodigo() {
        int proximoCodigo = disciplinaDAO.listarDisciplinas().size() + 1;
        return "DIS-" + String.format("%04d", proximoCodigo);
    }

    public boolean adicionarDisciplina(String codigo, String nome, int cargaHoraria, Integer professorIndex, double valor) {
        List<Professor> professores = listarProfessoresAtivos();
        Professor professor = (professorIndex != null && professorIndex > 0 && professorIndex <= professores.size())
                ? professores.get(professorIndex - 1)
                : null;

        Disciplina novaDisciplina = new Disciplina(codigo, nome, cargaHoraria, professor, valor);
        disciplinaDAO.adicionarDisciplina(novaDisciplina);
        return true;
    }

    public boolean editarDisciplina(int index, String novoNome, Integer novaCargaHoraria, Integer professorIndex, Double novoValor) {
        List<Disciplina> disciplinas = disciplinaDAO.listarDisciplinas();
        if (index < 1 || index > disciplinas.size()) {
            return false;
        }

        Disciplina disciplina = disciplinas.get(index - 1);
        if (!novoNome.isEmpty()) disciplina.setNome(novoNome);
        if (novaCargaHoraria != null) disciplina.setCargaHoraria(novaCargaHoraria);
        if (professorIndex != null) {
            List<Professor> professores = listarProfessoresAtivos();
            if (professorIndex >= 1 && professorIndex <= professores.size()) {
                disciplina.setProfessor(professores.get(professorIndex - 1));
            } else {
                System.out.println("❌ Erro: Número de professor inválido.");
                return false;
            }
        }
        if (novoValor != null) disciplina.setValor(novoValor);

        disciplinaDAO.atualizarDisciplina(disciplina);
        return true;
    }

    public boolean alterarStatusDisciplina(int index) {
        List<Disciplina> disciplinas = disciplinaDAO.listarDisciplinas();
        if (index < 1 || index > disciplinas.size()) {
            return false;
        }

        Disciplina disciplina = disciplinas.get(index - 1);
        disciplina.setStatus(disciplina.getStatus() == Status.ATIVO ? Status.INATIVO : Status.ATIVO);
        disciplinaDAO.atualizarDisciplina(disciplina);
        return true;
    }

    public void listarDisciplinas() {
        List<Disciplina> disciplinas = disciplinaDAO.listarDisciplinas();
        if (disciplinas.isEmpty()) {
            System.out.println("📌 Nenhuma disciplina cadastrada.");
        } else {
            System.out.println("\n=== Lista de Disciplinas ===");
            System.out.println(" Nº | Código   | Nome               | Carga Horária | Professor         | Valor   | Status ");
            System.out.println("--------------------------------------------------------------------------------------------");
            IntStream.range(0, disciplinas.size()).forEach(i -> {
                Disciplina disciplina = disciplinas.get(i);
                String professorNome = disciplina.getProfessor() != null ? disciplina.getProfessor().getNome() : "N/A";
                System.out.printf(" %2d | %-8s | %-20s | %-13d | %-18s | %.2f | %s \n",
                        i + 1, disciplina.getCodigo(), disciplina.getNome(), disciplina.getCargaHoraria(),
                        professorNome, disciplina.getValor(), disciplina.getStatus());
            });
        }
    }

    public List<Professor> listarProfessoresAtivos() {
        return professorDAO.listarProfessores().stream()
                .filter(professor -> professor.getStatus() == Status.ATIVO)
                .toList();
    }

    public Disciplina selecionarDisciplina(int index) {
        List<Disciplina> disciplinas = disciplinaDAO.listarDisciplinas();
        if (index < 1 || index > disciplinas.size()) {
            return null;
        }
        return disciplinas.get(index - 1);
    }
}