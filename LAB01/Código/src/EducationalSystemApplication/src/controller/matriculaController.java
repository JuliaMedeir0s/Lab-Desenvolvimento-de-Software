package controller;

import java.util.List;

import models.*;
import models.enums.*;

import DAO.*;



public class MatriculaController {
   
    private final AlunoDAO alunoDAO = AlunoDAO.getInstance();


    public List<Aluno> listarAlunos() {
        return alunoDAO.getAlunos();
    }

    public Aluno buscarPorNome(String nome) {
        return alunoDAO.buscarPorNome(nome);
    }

    public Aluno buscarPorMatricula(String matricula) {
        return alunoDAO.buscarPorMatricula(matricula);
    }

    public boolean adicionarAluno(Aluno aluno) {
        if (alunoDAO.buscarPorMatricula(aluno.getMatricula()) != null) {
            return false;
        }
        alunoDAO.adicionarAluno(aluno);
        return true;
    }

    public boolean inscreverEmDisciplina(Aluno aluno, Disciplina disciplina) {
        if (aluno.getMatriculas().size() < 6) {
            Matricula novaMatricula = new Matricula(aluno, disciplina);
            if (novaMatricula.confirmarMatricula()) {
                aluno.getMatriculas().add(novaMatricula);
                return true;
            }
        } else {
            System.out.println("Aluno " + aluno.getNome() + " já está matriculado no máximo permitido de 6 disciplinas.");
        }
        return false;
    }

    public boolean desinscreverDeDisciplina(Aluno aluno, Disciplina disciplina) {
        Matricula matriculaParaCancelar = null;
        for (Matricula m : aluno.getMatriculas()) {
            if (m.getDisciplina().equals(disciplina)) {
                matriculaParaCancelar = m;
            }
        }
        if (matriculaParaCancelar != null) {
            if (matriculaParaCancelar.cancelarMatricula()) {
                aluno.getMatriculas().remove(matriculaParaCancelar);
                return true;
            }
        }
        return false;
    }


}
