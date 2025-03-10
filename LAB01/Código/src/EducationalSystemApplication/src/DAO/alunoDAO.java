package DAO;

import models.Aluno;
import models.enums.Status;

import java.util.List;
import java.util.Optional;
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
            synchronized (AlunoDAO.class) {
                if (instancia == null) {
                    instancia = new AlunoDAO();
                }
            }
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

    public Aluno buscarPorNome(String nome) {
        return alunos.stream().filter(a -> a.getNome().equalsIgnoreCase(nome)).findFirst().orElse(null);
    }

    public List<Aluno> getAlunos(){
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
            UsuarioDAO.getInstance().removerUsuario(alunoOpt.get());
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
}
