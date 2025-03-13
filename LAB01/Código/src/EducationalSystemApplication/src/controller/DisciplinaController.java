package controller;

import DAO.DisciplinaDAO;
import DAO.ProfessorDAO;
import models.Curso;
import models.Disciplina;
import models.Professor;
import models.enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public boolean adicionarDisciplina(String codigo, String nome, int cargaHoraria, Integer professorIndex,
            double valor) {
        List<Professor> professores = listarProfessoresAtivos();
        Professor professor = (professorIndex != null && professorIndex > 0 && professorIndex <= professores.size())
                ? professores.get(professorIndex - 1)
                : null;

        Disciplina novaDisciplina = new Disciplina(codigo, nome, cargaHoraria, professor, valor);
        disciplinaDAO.adicionarDisciplina(novaDisciplina);
        if (professor != null) { //evita o erro de quando prof é null
            professor.adicionarDisciplina(novaDisciplina);
            professorDAO.atualizarProfessor(professor);
        }

        return true;
    }

    public boolean editarDisciplina(int index, String novoNome, Integer novaCargaHoraria, Integer professorIndex,
            Double novoValor) {
        List<Disciplina> disciplinas = disciplinaDAO.listarDisciplinas();
        if (index < 1 || index > disciplinas.size()) {
            return false;
        }

        Disciplina disciplina = disciplinas.get(index - 1);
        if (!novoNome.isEmpty())
            disciplina.setNome(novoNome);
        if (novaCargaHoraria != null)
            disciplina.setCargaHoraria(novaCargaHoraria);
        if (professorIndex != null) {
            List<Professor> professores = listarProfessoresAtivos();
            if (professorIndex >= 1 && professorIndex <= professores.size()) {
                disciplina.setProfessor(professores.get(professorIndex - 1));
                professorDAO.atualizarProfessor(disciplina.getProfessor());
            } else {
                System.out.println("❌ Erro: Número de professor inválido.");
                return false;
            }
        }
        if (novoValor != null)
            disciplina.setValor(novoValor);

        disciplinaDAO.atualizarDisciplina(disciplina);

        return true;
    }

    public boolean verificarAtivacaoDisciplina(Disciplina disciplina) {
        int totalMatriculados = disciplina.getMatriculas().size();

        if (totalMatriculados >= Disciplina.getMatriculaMinima()) {
            disciplina.setStatus(Status.ATIVO);
            return true;
        } else {
            disciplina.setStatus(Status.INATIVO);
            return false;
        }
    }

    public boolean alterarStatusDisciplina(int index) {
        List<Disciplina> disciplinas = disciplinaDAO.listarDisciplinas();
        if (index < 1 || index > disciplinas.size()) {
            return false;
        }

        Disciplina disciplina = disciplinas.get(index - 1);
        //boolean podeAtivar = verificarAtivacaoDisciplina(disciplina);

        // if (!podeAtivar && disciplina.getStatus() == Status.ATIVO) {
        //     System.out.println("❌ Erro: Disciplina não pode ser ativada porque não atingiu o mínimo de alunos.");
        //     return false;
        // }

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
            System.out.println(
                    " Nº | Código   | Nome               | Carga Horária | Professor         | Valor   | Status ");
            System.out.println(
                    "--------------------------------------------------------------------------------------------");
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

    public boolean adicionarCursoADisciplina(String codigoDisciplina, Curso curso) {
        Optional<Disciplina> disciplinaOpt = disciplinaDAO.buscarPorCodigo(codigoDisciplina);
        if (disciplinaOpt.isEmpty()) {
            System.out.println("❌ Erro: Disciplina não encontrada.");
            return false;
        }

        Disciplina disciplina = disciplinaOpt.get();
        if (disciplina.getCursos() == null) {
            disciplina.setCursos(new ArrayList<>());
        }

        if (!disciplina.getCursos().contains(curso)) {
            disciplina.getCursos().add(curso);
            disciplinaDAO.atualizarDisciplina(disciplina);
            return true;
        }
        System.out.println("❌ Esta disciplina já está associada a este curso.");
        return false;
    }

    public List<Disciplina> listarDisciplinasNaoAssociadas(Curso curso) {
        List<Disciplina> todasDisciplinas = disciplinaDAO.listarDisciplinas();
        List<Disciplina> disciplinasDisponiveis = new ArrayList<>();

        for (Disciplina disciplina : todasDisciplinas) {
            if (!curso.getDisciplinas().contains(disciplina)) {
                disciplinasDisponiveis.add(disciplina);
            }
        }
        return disciplinasDisponiveis;
    }

    public boolean removerCursoDeDisciplina(String codigoDisciplina, Curso curso) {
        Optional<Disciplina> disciplinaOpt = disciplinaDAO.buscarPorCodigo(codigoDisciplina);
        if (disciplinaOpt.isEmpty()) {
            System.out.println("❌ Erro: Disciplina não encontrada.");
            return false;
        }

        Disciplina disciplina = disciplinaOpt.get();
        if (disciplina.getCursos().contains(curso)) {
            disciplina.getCursos().remove(curso);
            disciplinaDAO.atualizarDisciplina(disciplina);
            return true;
        }
        return false;
    }
}