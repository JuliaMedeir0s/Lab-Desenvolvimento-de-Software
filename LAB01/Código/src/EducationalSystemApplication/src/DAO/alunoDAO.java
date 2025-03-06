package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import models.Aluno;
import models.Disciplina;

public class AlunoDAO extends AbstractDao implements Serializable {

    private List<Aluno> alunos = new ArrayList<>();
    private static AlunoDAO instance = new AlunoDAO();

    private static final String CAMINHO_ALUNOS = "alunos.dat";

    private AlunoDAO() {
        super(CAMINHO_ALUNOS);
        this.alunos = new ArrayList<>();
        carregarAlunos();
    }

    public static AlunoDAO getInstance() {
        if (instance == null) {
            instance = new AlunoDAO();
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

    public Aluno buscarPorMatricula(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }

    private void carregarAlunos(){
        this.alunos = leitura();
    }

    public Aluno buscarPorEmail(String email) {
        for (Aluno aluno : alunos) {
            if (aluno.getEmail().equals(email)) {
                return aluno;
            }
        }
        return null;
    }

    public void atualizarAluno(Aluno aluno) {
        Optional<Aluno> alunoOptional = alunos.stream().filter(a -> a.getId().equals(aluno.getId())).findFirst();
        if (alunoOptional.isPresent()) {
            Aluno alunoAtualizado = alunoOptional.get();
            alunoAtualizado.setNome(aluno.getNome());
            alunoAtualizado.setEmail(aluno.getEmail());
            alunoAtualizado.setSenha(aluno.getSenha());
            alunoAtualizado.setMatricula(aluno.getMatricula());
            alunoAtualizado.setCurso(aluno.getCurso());
            alunoAtualizado.setMatriculas(aluno.getMatriculas());
            grava(alunos);
        }
    }

}
