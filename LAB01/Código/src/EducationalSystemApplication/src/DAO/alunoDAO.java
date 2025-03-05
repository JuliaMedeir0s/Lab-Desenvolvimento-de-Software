package DAO;

import java.util.ArrayList;
import java.util.List;

import models.Aluno;

public class alunoDAO {
    private static List<Aluno> alunos = new ArrayList<>();
    private static alunoDAO instance;

    private alunoDAO() {

    }

    public static alunoDAO getInstance() {
        if (instance == null) {
            instance = new alunoDAO();
        }
        return instance;
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public Aluno buscarPorNome(String nome) {
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equals(nome)) {
                return aluno;
            }
        }
        return null;
    }
}
