package DAO;

<<<<<<< HEAD
import models.Aluno;
import models.enums.Status;
=======
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import models.Aluno;
import models.Disciplina;
>>>>>>> 3711e5730b43689194f96f13aba3fdba0a9a4349

import java.util.List;
import java.util.Optional;
import java.util.Comparator;
import controller.AlunoController;

public class AlunoDAO extends AbstractDao<Aluno> {
    private static final String FILE_NAME = "alunos.dat";
    private static AlunoDAO instancia;
    private List<Aluno> alunos;

    private AlunoDAO() {
        super(FILE_NAME);
        alunos = leitura();
        atualizarContadorMatricula();
    }

    public static AlunoDAO getInstance() {
        if (instancia == null) {
            instancia = new AlunoDAO();
        }
        return instancia;
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
        grava(alunos);
        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
        usuarioDAO.adicionarUsuario(aluno);
        atualizarContadorMatricula();
    }

    public Optional<Aluno> buscarPorMatricula(String matricula) {
        return alunos.stream().filter(a -> a.getId().equals(matricula)).findFirst();
    }

    public Optional<Aluno> buscarPorEmail(String email) {
        return alunos.stream().filter(a -> a.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    public List<Aluno> listarAlunos() {
        return alunos;
    }

    public void atualizarAluno(Aluno alunoAtualizado) {
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getId().equals(alunoAtualizado.getId())) {
                alunos.set(i, alunoAtualizado);
                grava(alunos);
                return;
            }
        }
    }

    public boolean alterarStatusAluno(String matricula) {
        Optional<Aluno> alunoOpt = buscarPorMatricula(matricula);
        if (alunoOpt.isPresent()) {
            Aluno aluno = alunoOpt.get();
            aluno.setStatus(aluno.getStatus() == Status.ATIVO ? Status.INATIVO : Status.ATIVO);
            atualizarAluno(aluno);
            return true;
        }
        return false;
    }    

    public boolean removerAluno(String matricula) {
        Optional<Aluno> alunoOpt = buscarPorMatricula(matricula);
        if (alunoOpt.isPresent()) {
            alunos.remove(alunoOpt.get());
            grava(alunos);
            return true;
        }
        return false;
    }

    private void atualizarContadorMatricula() {
        if (!alunos.isEmpty()) {
            int maiorMatricula = alunos.stream()
                    .map(a -> {
                        String[] partes = a.getId().split("-");
                        if (partes.length < 2) return 0;
                        try {
                            return Integer.parseInt(partes[1]);
                        } catch (NumberFormatException e) {
                            return 0;
                        }
                    })
                    .max(Integer::compare)
                    .orElse(0);
            AlunoController.setContadorAluno(maiorMatricula + 1);
        }
    }
<<<<<<< HEAD
}
=======

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
>>>>>>> 3711e5730b43689194f96f13aba3fdba0a9a4349
