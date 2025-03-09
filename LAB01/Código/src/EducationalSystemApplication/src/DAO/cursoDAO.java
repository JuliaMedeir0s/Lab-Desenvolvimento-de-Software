package DAO;

import models.Curso;
import models.Disciplina;
import models.enums.Status;

import java.util.List;
import java.util.Optional;

import controller.CursoController;

public class CursoDAO extends AbstractDao<Curso> {
    private static final String FILE_NAME = "cursos.dat";
    private static CursoDAO instancia;
    private List<Curso> cursos;

    private CursoDAO() {
        super(FILE_NAME);
        cursos = leitura();
        atualizarContadorCodigo();
    }

    public static CursoDAO getInstance() {
        if (instancia == null) {
            instancia = new CursoDAO();
        }
        return instancia;
    }

    public void adicionarCurso(Curso curso) {
        cursos.add(curso);
        grava(cursos);
        atualizarContadorCodigo(); 
    }

    public Optional<Curso> buscarPorCodigo(String codigo) {
        return cursos.stream().filter(c -> c.getCodigo().equals(codigo)).findFirst();
    }

    public List<Curso> listarCursos() {
        return cursos;
    }

    public List<Curso> listarCursosAtivos() {
        return cursos.stream().filter(c -> c.getStatus() == models.enums.Status.ATIVO).toList();
    }

    public void atualizarCurso(Curso cursoAtualizado) {
        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getCodigo().equals(cursoAtualizado.getCodigo())) {
                cursos.set(i, cursoAtualizado);
                grava(cursos);
                return;
            }
        }
    }

    public boolean alterarStatusCurso(String codigo) {
        Optional<Curso> cursoOpt = buscarPorCodigo(codigo);
        if (cursoOpt.isPresent()) {
            Curso curso = cursoOpt.get();
            curso.setStatus(curso.getStatus() == Status.ATIVO ? Status.INATIVO : Status.ATIVO);
            atualizarCurso(curso);
            return true;
        }
        return false;
    }

    private void atualizarContadorCodigo() {
        if (!cursos.isEmpty()) {
            int maiorCodigo = cursos.stream()
                    .map(c -> {
                        String[] partes = c.getCodigo().split("-");
                        if (partes.length < 2) return 0;
                        try {
                            return Integer.parseInt(partes[1]);
                        } catch (NumberFormatException e) {
                            return 0;
                        }
                    })
                    .max(Integer::compare)
                    .orElse(0);
            CursoController.setContadorCurso(maiorCodigo + 1);
        }
    }

    public void adicionarDisciplinaOptativaAoCurso(Curso curso, Disciplina disciplina) {
    if (!curso.getDisciplinasOptativas().contains(disciplina)) {
        curso.getDisciplinasOptativas().add(disciplina);
        atualizarCurso(curso);
    }
}

}