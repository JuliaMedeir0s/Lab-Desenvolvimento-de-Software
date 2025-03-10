package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import models.Aluno;
import models.Disciplina;
import models.Matricula;

public class MatriculaDAO extends AbstractDao<Matricula> {

    private List<Matricula> matriculas;
    private static MatriculaDAO instance;

    private static final String CAMINHO_MATRICULAS = "matriculas.dat";

    public MatriculaDAO() {
        super(CAMINHO_MATRICULAS);
        matriculas = leitura();
    }

    public boolean adicionarMatricula(Matricula matricula) {
        try {
            this.matriculas.add(matricula);
            grava(matriculas);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removerMatricula(Matricula matricula) {
        try {
            this.matriculas.remove(matricula);
            grava(matriculas);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Matricula> getMatriculas() {
        return this.matriculas;
    }

    public Optional<Matricula> buscarPorCodigo(String codigo) {
        for (Matricula matricula : matriculas) {
            if (matricula.getDisciplina().getCodigo().equals(codigo)) {
                return Optional.of(matricula);
            }
        }
        return null;
    }

    public static MatriculaDAO getInstance() {
        if (instance == null) {
            instance = new MatriculaDAO();
        }
        return instance;
    }

    public List<Matricula> listarMatriculaPorAluno(Aluno aluno) {
        List<Matricula> matriculasAluno = new ArrayList<>();
        for (Matricula matricula : matriculas) {
            if (matricula.getAluno().equals(aluno)) {
                matriculasAluno.add(matricula);
            }
        }
        return matriculasAluno; 
    }

    public List<Matricula> listarMatriculaPorDisciplina(Disciplina disciplina) {
        List<Matricula> matriculasDisciplina = new ArrayList<>();
        for (Matricula matricula : matriculas) {
            if (matricula.getDisciplina().equals(disciplina)) {
                matriculasDisciplina.add(matricula);
            }
        }
        return matriculasDisciplina;
    }

    public Optional<Matricula> buscarMatricula(Aluno aluno, Disciplina disciplina) {
        for (Matricula matricula : matriculas) {
            if (matricula.getAluno().equals(aluno) && matricula.getDisciplina().equals(disciplina)) {
                return Optional.of(matricula);
            }
        }
        return Optional.empty();
    }

    // public boolean adicionarMatricula(Matricula matricula) {
    //     try{
    //         this.matriculas.add(matricula);
    //         grava(matriculas);
    //         return true;
    //     }   catch (Exception e){
    //         return false;
    //     }
    // }

    // public boolean removerMatricula(Matricula matricula) {
    //     try{
    //         this.matriculas.remove(matricula);
    //         grava(matriculas);
    //         return true;
    //     }   catch (Exception e){
    //         return false;
    //     }
    // }

    // private void carregarMatriculas() {
    //     this.matriculas = leitura();
    // }

    // public List<Matricula> getMatriculas() {
    //     return this.matriculas;
    // }

    // public Matricula buscarPorCodigo(String codigo) {
    //     for (Matricula matricula : matriculas) {
    //         if (matricula.getDisciplina().getCodigo().equals(codigo)) {
    //             return matricula;
    //         }
    //     }
    //     return null;
    // }

    // public static MatriculaDAO getInstance() {
    //     if (instance == null) {
    //         instance = new MatriculaDAO();
    //     }
    //     return instance;
    // }

}
