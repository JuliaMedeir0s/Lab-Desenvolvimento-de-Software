package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import models.Curso;

public class CursoDAO extends AbstractDao implements Serializable {

    private static List<Curso> cursos = new ArrayList<>();
    private static CursoDAO instance = new CursoDAO();

    private static final String CAMINHO_CURSO = "cursos.dat";

    private CursoDAO() {
        super(CAMINHO_CURSO);
        this.cursos = new ArrayList<>();
        carregarCursos();

    }

    public static CursoDAO getInstance() {
        if (instance == null) {
            instance = new CursoDAO();
        }
        return instance;
    }

    public void adicionarCurso(Curso curso) {
        cursos.add(curso);
        grava(cursos);
    }

    public void removerCurso(Curso curso) {
        cursos.remove(curso);
        grava(cursos);
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    private void carregarCursos() {
        this.cursos = leitura();
    }

    public Curso buscarPorNome(String nome) {
        for (Curso curso : cursos) {
            if (curso.getNome().equals(nome)) {
                return curso;
            }
        }
        return null;
    }

}
