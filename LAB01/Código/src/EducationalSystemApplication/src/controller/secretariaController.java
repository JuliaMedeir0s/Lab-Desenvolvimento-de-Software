package controller;

import DAO.*;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import models.Aluno;
import models.Curso;
import models.Disciplina;
import models.Professor;
import models.Secretaria;
import models.Semestre;

public class SecretariaController {
    private final AlunoDAO alunoDAO;
    private final ProfessorDAO professorDAO;
    private final CursoDAO cursoDAO;
    private final DisciplinaDAO disciplinaDAO;
    private final SemestreDAO semestreDAO;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final AtomicInteger contadorSecretaria = new AtomicInteger(1);

    private final SecretariaDAO secretariaDAO = SecretariaDAO.getInstance();

    public SecretariaController() {
        this.alunoDAO = AlunoDAO.getInstance();
        this.professorDAO = ProfessorDAO.getInstance();
        this.cursoDAO = CursoDAO.getInstance();
        this.disciplinaDAO = DisciplinaDAO.getInstance();
        this.semestreDAO = SemestreDAO.getInstance();
    }

    public void criarSemestre(int ano, int periodo, LocalDate inicio, LocalDate fim) {
        if (inicio.isBefore(fim)) {
            Semestre novoSemestre = new Semestre(ano, periodo, inicio, fim);
            semestreDAO.adicionarSemestre(novoSemestre);
            System.out.println("✅ Semestre " + ano + "." + periodo + " criado com sucesso.");
        } else {
            System.out.println("❌ Erro: A data de início deve ser antes da data de fim.");
        }
    }

    public void definirPeriodoInscricao(Semestre semestre, LocalDate inicio, LocalDate fim) {
        if (inicio.isBefore(fim)) {
            semestre.setInicioInscricao(inicio);
            semestre.setFimInscricao(fim);
            semestreDAO.atualizarSemestre(semestre);
            System.out.println("✅ Período de inscrições definido: " + inicio + " a " + fim);
        } else {
            System.out.println("❌ Erro: A data de início deve ser antes da data de fim.");
        }
    }

    public boolean isDentroDoPeriodoInscricao(Semestre semestre, LocalDate dataAtual) {
        return semestre.getInicioInscricao() != null && semestre.getFimInscricao() != null
                && !dataAtual.isBefore(semestre.getInicioInscricao())
                && !dataAtual.isAfter(semestre.getFimInscricao());
    }

    public void listarUsuarios() {
        List<Secretaria> secretarias = secretariaDAO.listarSecretarias();
        if (secretarias.isEmpty()) {
            System.out.println("📌 Nenhuma secretaria cadastrada.");
        } else {
            System.out.println("\n=== Lista de Secretarias ===");
            secretarias.forEach(secretaria -> System.out.println("- " + secretaria));
        }
    }

    public void listarAlunos() {
        List<Aluno> alunos = alunoDAO.listarAlunos();
        if (alunos.isEmpty()) {
            System.out.println("📌 Nenhum aluno cadastrado.");
        } else {
            System.out.println("\n=== Lista de Alunos ===");
            alunos.forEach(aluno -> System.out.println("- " + aluno));
        }
    }

    public void listarProfessores() {
        List<Professor> professores = professorDAO.listarProfessores();
        if (professores.isEmpty()) {
            System.out.println("📌 Nenhum professor cadastrado.");
        } else {
            System.out.println("\n=== Lista de Professores ===");
            professores.forEach(professor -> System.out.println("- " + professor));
        }
    }

    public void listarCursos() {
        List<Curso> cursos = cursoDAO.listarCursos();
        if (cursos.isEmpty()) {
            System.out.println("📌 Nenhum curso cadastrado.");
        } else {
            System.out.println("\n=== Lista de Cursos ===");
            cursos.forEach(curso -> System.out.println("- " + curso));
        }
    }

    public void listarDisciplinas() {
        List<Disciplina> disciplinas = disciplinaDAO.listarDisciplinas();
        if (disciplinas.isEmpty()) {
            System.out.println("📌 Nenhuma disciplina cadastrada.");
        } else {
            System.out.println("\n=== Lista de Disciplinas ===");
            disciplinas.forEach(disciplina -> System.out.println("- " + disciplina));
        }
    }

    public Disciplina buscarDisciplinaPorCodigo(String codigo) {
        return disciplinaDAO.buscarPorCodigo(codigo).orElse(null);
    }

    public Curso buscarCursoPorNome(String nome) {
        return cursoDAO.buscarPorNome(nome);
    }

    public void adicionarUsuario(String nome, String email, String senha) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            System.out.println("❌ Erro: E-mail inválido.");

        }

        if (professorDAO.buscarPorEmail(email).isPresent()) {
            System.out.println("❌ Erro: Já existe um professor com esse e-mail.");
        }
        String id = gerarId();
        Secretaria secretaria = new Secretaria(id, nome, email, senha);
        try {
            secretariaDAO.adicionarSecretaria(secretaria);
            System.out.println("✅ Usuário adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao adicionar usuário: " + e.getMessage());
        }

    }

    public void editarUsuario(String id, String novoNome) {
        try {
            Secretaria secretaria = secretariaDAO.buscarSecretariaPorId(id);
            if (secretaria != null) {
                secretaria.setNome(novoNome);
                secretariaDAO.atualizarSecretaria(secretaria);
                System.out.println("✅ Usuário editado com sucesso!");
            } else {
                System.out.println("❌ Usuário não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao editar usuário: " + e.getMessage());
        }

    }

    public void alterarStatusUsuario(String id) {
        try {
            Secretaria secretaria = secretariaDAO.buscarSecretariaPorId(id);
            if (secretaria != null) {
                secretaria.setAtivo(!secretaria.isAtivo());
                secretariaDAO.atualizarSecretaria(secretaria);
                System.out.println("✅ Status do usuário alterado com sucesso!");
            } else {
                System.out.println("❌ Usuário não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao alterar status do usuário: " + e.getMessage());
        }

    }

    private String gerarId() {
        return "SEC-" + String.format("%04d", contadorSecretaria.getAndIncrement());
    }

}