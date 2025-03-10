package controller;

import DAO.AlunoDAO;
import DAO.DisciplinaDAO;
import models.Aluno;
import models.Curso;
import models.Disciplina;
import models.enums.Status;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class AlunoController {

    private final AlunoDAO alunoDAO = AlunoDAO.getInstance();
    private final DisciplinaDAO disciplinaDAO = DisciplinaDAO.getInstance();
    private final CursoController cursoController = new CursoController();
    private static final AtomicInteger contadorAluno = new AtomicInteger(1);
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static void setContadorAluno(int valor) {
        contadorAluno.set(valor);
    }

    public boolean adicionarAluno(String nome, String email, String senha, Integer cursoIndex) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            System.out.println("❌ Erro: E-mail inválido.");
            return false;
        }
    
        if (alunoDAO.buscarPorEmail(email).isPresent()) {
            System.out.println("❌ Erro: Já existe um aluno com esse e-mail.");
            return false;
        }
    
        Curso curso = (cursoIndex != null) ? cursoController.selecionarCurso(cursoIndex) : null;
        
        String id = gerarId();
        Aluno novoAluno = new Aluno(id, nome, email, senha, curso);
        alunoDAO.adicionarAluno(novoAluno);
        return true;
    }

    private String gerarId() {
        return "ALU-" + String.format("%04d", contadorAluno.getAndIncrement());
    }

    public boolean editarAluno(int index, String novoNome, String novoEmail, String novaSenha, Integer cursoIndex) {
        List<Aluno> alunos = alunoDAO.listarAlunos();
        if (index < 1 || index > alunos.size()) {
            return false;
        }

        Aluno aluno = alunos.get(index - 1);
        if (!novoEmail.isEmpty() && !EMAIL_PATTERN.matcher(novoEmail).matches()) {
            System.out.println("❌ Erro: E-mail inválido.");
            return false;
        }

        if (!novoNome.isEmpty()) aluno.setNome(novoNome);
        if (!novoEmail.isEmpty()) aluno.setEmail(novoEmail);
        if (!novaSenha.isEmpty()) aluno.setSenha(novaSenha);

        if (cursoIndex != null) {
            Curso novoCurso = cursoController.selecionarCurso(cursoIndex);
            if (novoCurso != null) {
                aluno.setCurso(novoCurso);
            } else {
                System.out.println("❌ Erro: Curso inválido.");
                return false;
            }
        }

        alunoDAO.atualizarAluno(aluno);
        return true;
    }

    public boolean alterarStatusAluno(int index) {
        List<Aluno> alunos = alunoDAO.listarAlunos();
        if (index < 1 || index > alunos.size()) {
            return false;
        }

        Aluno aluno = alunos.get(index - 1);
        aluno.setStatus(aluno.getStatus() == Status.ATIVO ? Status.INATIVO : Status.ATIVO);
        alunoDAO.atualizarAluno(aluno);
        return true;
    }

    public void listarAlunos() {
        List<Aluno> alunos = alunoDAO.listarAlunos();
        if (alunos.isEmpty()) {
            System.out.println("📌 Nenhum aluno cadastrado.");
        } else {
            System.out.println("\n=== Lista de Alunos ===");
            System.out.println(" Nº | ID        | Nome                | E-mail               | Status ");
            System.out.println("---------------------------------------------------------------");
            int i = 1;
            for (Aluno aluno : alunos) {
                System.out.printf(" %2d | %-8s | %-20s | %-20s | %s \n",
                        i, aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getStatus());
                i++;
            }
        }
    }

    public Aluno selecionarAluno(int index) {
        List<Aluno> alunos = alunoDAO.listarAlunos();
        if (index < 1 || index > alunos.size()) {
            return null;
        }
        return alunos.get(index - 1);
    }
    
    public Optional<Disciplina> buscarDisciplinaPorCodigo(String codigo) {
        return disciplinaDAO.buscarPorCodigo(codigo);
    }

    public boolean inscreverEmDisciplina(Aluno aluno, Disciplina disciplina) {
        if (aluno.getMatriculas().size() >= Disciplina.getMatriculaMaxima()) {
            System.out.println("❌ Erro: Limite de matrículas atingido.");
            return false;
        }
    
        if (disciplina.getStatus() == Status.INATIVO) {
            System.out.println("❌ Erro: Disciplina inativa.");
            return false;
        }
    
        if (aluno.getMatriculas().stream().anyMatch(m -> m.getDisciplina().equals(disciplina))) {
            System.out.println("❌ Erro: Aluno já matriculado nessa disciplina.");
            return false;
        }
        MatriculaController matriculaController = new MatriculaController();
        boolean sucesso = matriculaController.matricularAluno(aluno, disciplina);
    
        if (sucesso) {
            alunoDAO.atualizarAluno(aluno);
            System.out.println("✅ Matrícula realizada com sucesso!");
            return true;
        } else {
            System.out.println("❌ Erro: Não foi possível realizar a matrícula.");
            return false;
        }
    }

    public boolean desinscreverDeDisciplina(Aluno aluno, Disciplina disciplina) {
        if (aluno.getMatriculas().isEmpty()) {
            System.out.println("❌ Erro: Aluno não está matriculado em nenhuma disciplina.");
            return false;
        }

        if (!aluno.getMatriculas().removeIf(m -> m.getDisciplina().equals(disciplina))) {
            System.out.println("❌ Erro: Aluno não está matriculado nessa disciplina.");
            return false;
        }

        alunoDAO.atualizarAluno(aluno);
        return true;
    }
}