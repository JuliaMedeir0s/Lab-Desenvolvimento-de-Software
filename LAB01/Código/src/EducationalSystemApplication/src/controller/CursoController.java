package controller;

import DAO.CursoDAO;
import DAO.DisciplinaDAO;
import models.Curso;
import models.Disciplina;
import models.enums.Status;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class CursoController {
    private final CursoDAO cursoDAO = CursoDAO.getInstance();
    private final DisciplinaDAO disciplinaDAO = DisciplinaDAO.getInstance();
    private final DisciplinaController disciplinaController = new DisciplinaController();
    private static final AtomicInteger contadorCurso = new AtomicInteger(1);

    public static void setContadorCurso(int valor) {
        contadorCurso.set(valor);
    }

    public boolean adicionarCurso(String nome, int creditos) {
        String codigo = gerarCodigo();
        Curso novoCurso = new Curso(codigo, nome, creditos);
        cursoDAO.adicionarCurso(novoCurso);
        return true;
    }

    private String gerarCodigo() {
        return "CUR-" + String.format("%04d", contadorCurso.getAndIncrement());
    }

    public boolean editarCurso(int index, String novoNome, Integer novosCreditos) {
        List<Curso> cursos = cursoDAO.listarCursos();
        if (index < 1 || index > cursos.size()) {
            return false;
        }

        Curso curso = cursos.get(index - 1);
        if (!novoNome.isEmpty())
            curso.setNome(novoNome);
        if (novosCreditos != null)
            curso.setCreditos(novosCreditos);

        cursoDAO.atualizarCurso(curso);
        return true;
    }

    public boolean alterarStatusCurso(int index) {
        List<Curso> cursos = cursoDAO.listarCursos();
        if (index < 1 || index > cursos.size()) {
            return false;
        }

        Curso curso = cursos.get(index - 1);
        curso.setStatus(curso.getStatus() == Status.ATIVO ? Status.INATIVO : Status.ATIVO);
        cursoDAO.atualizarCurso(curso);
        return true;
    }

    public void listarCursos() {
        List<Curso> cursos = cursoDAO.listarCursos();
        if (cursos.isEmpty()) {
            System.out.println("📌 Nenhum curso cadastrado.");
        } else {
            System.out.println("\n=== Lista de Cursos ===");
            System.out.println(" Nº | Código   | Nome               | Créditos | Status ");
            System.out.println("------------------------------------------------------");
            int i = 1;
            for (Curso curso : cursos) {
                System.out.printf(" %2d | %-8s | %-20s | %-8d | %s \n",
                        i, curso.getCodigo(), curso.getNome(), curso.getCreditos(), curso.getStatus());
                i++;
            }
        }
    }

    public Curso selecionarCurso(int index) {
        List<Curso> cursos = cursoDAO.listarCursos();
        if (index < 1 || index > cursos.size()) {
            return null;
        }
        return cursos.get(index - 1);
    }

    public void listarCursosAtivos() {
        List<Curso> cursos = cursoDAO.listarCursosAtivos();
        if (cursos.isEmpty()) {
            System.out.println("📌 Nenhum curso ativo disponível.");
            return;
        }

        System.out.println("\n=== Lista de Cursos Ativos ===");
        System.out.println(" Nº | Nome               | Código   ");
        System.out.println("-----------------------------------");
        int i = 1;
        for (Curso curso : cursos) {
            System.out.printf(" %2d | %-20s | %-8s \n", i, curso.getNome(), curso.getCodigo());
            i++;
        }
    }

    public Curso selecionarCursoAtivos(int index) {
        List<Curso> cursos = cursoDAO.listarCursosAtivos();
        if (index < 1 || index > cursos.size()) {
            return null;
        }
        return cursos.get(index - 1);
    }

    public boolean adicionarDisciplinaAoCurso(String cursoCodigo, String disciplinaCodigo) {
        Optional<Curso> cursoOpt = cursoDAO.buscarPorCodigo(cursoCodigo);
        Optional<Disciplina> disciplinaOpt = disciplinaDAO.buscarPorCodigo(disciplinaCodigo);

        if (cursoOpt.isEmpty()) {
            System.out.println("❌ Erro: Curso não encontrado.");
            return false;
        }

        if (disciplinaOpt.isEmpty()) {
            System.out.println("❌ Erro: Disciplina não encontrada.");
            return false;
        }

        Curso curso = cursoOpt.get();
        Disciplina disciplina = disciplinaOpt.get();

        if (!curso.getDisciplinas().contains(disciplina)) {
            curso.getDisciplinas().add(disciplina); // 🔥 Adiciona a disciplina ao curso
            cursoDAO.atualizarCurso(curso);
        } else {
            System.out.println("❌ Esta disciplina já está associada a este curso.");
            return false;
        }

        return disciplinaController.adicionarCursoADisciplina(disciplina.getCodigo(), curso);
    }

    public Curso visualizarCurso(int cursoIndex) {
        List<Curso> cursos = cursoDAO.listarCursos();
        if (cursoIndex < 1 || cursoIndex > cursos.size()) {
            return null;
        }
        return cursos.get(cursoIndex - 1);
    }

    public Curso buscarCursoPorCodigo(String codigo) {
        return cursoDAO.listarCursos().stream()
            .filter(curso -> curso.getCodigo().equalsIgnoreCase(codigo))
            .findFirst()
            .orElse(null);
    }    
}