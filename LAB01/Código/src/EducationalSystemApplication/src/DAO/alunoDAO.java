package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import models.Aluno;

public class alunoDAO extends AbstractDao implements Serializable {

    private List<Aluno> alunos = new ArrayList<>();
    private static alunoDAO instance = new alunoDAO();

    private static final String CAMINHO_ALUNOS = "alunos.dat";

    private alunoDAO() {
        super(CAMINHO_ALUNOS);
        this.alunos = new ArrayList<>();
        carregarAlunos();
    }

    public static alunoDAO getInstance() {
        if (instance == null) {
            instance = new alunoDAO();
        }
        return instance;
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
        grava(alunos);
    }

    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
        grava(alunos);

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

    private void carregarAlunos(){
        this.alunos = leitura();
    }

}
