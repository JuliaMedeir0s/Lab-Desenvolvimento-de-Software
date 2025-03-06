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
            semestre.setDataInicioInscricoes(inicio);
            semestre.setDataFimInscricoes(fim);
            semestreDAO.atualizarSemestre(semestre);
            System.out.println("✅ Período de inscrições definido: " + inicio + " a " + fim);
        } else {
            System.out.println("❌ Erro: A data de início deve ser antes da data de fim.");
        }
    }

    public boolean isDentroDoPeriodoInscricao(Semestre semestre, LocalDate dataAtual) {
        return semestre.getDataInicioInscricoes() != null && semestre.getDataFimInscricoes() != null
                && !dataAtual.isBefore(semestre.getDataInicioInscricoes())
                && !dataAtual.isAfter(semestre.getDataFimInscricoes());
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
        List<Aluno> alunos = alunoDAO.getAlunos();
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
        List<Curso> cursos = cursoDAO.getCursos();
        if (cursos.isEmpty()) {
            System.out.println("📌 Nenhum curso cadastrado.");
        } else {
            AtomicInteger i = new AtomicInteger(0);
            System.out.println("\n=== Lista de Cursos ===");
            cursos.forEach(curso -> System.out.println(i.getAndIncrement() +" - " + curso));
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

    public void adicionarAluno(String nome, String email, String senha, String matricula, Curso curso) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            System.out.println("❌ Erro: E-mail inválido.");
            return;
        }

        if (alunoDAO.buscarPorEmail(email).getEmail() != null) {
            System.out.println("❌ Erro: Já existe um aluno com esse e-mail.");
            return;
        }

        String id = gerarId();
        Aluno aluno = new Aluno(id, nome, email, senha, matricula, curso);
        try {
            alunoDAO.adicionarAluno(aluno);
            System.out.println("✅ Aluno adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao adicionar aluno: " + e.getMessage());
        }
    }

    public Curso getCurso(String codigo) {
        return cursoDAO.buscarPorNome(codigo);
    }

    public void editarAluno(String matricula, String novoNome) {
        try {
            Aluno aluno = alunoDAO.buscarPorMatricula(matricula);
            if (aluno != null) {
                aluno.setNome(novoNome);
                alunoDAO.atualizarAluno(aluno);
                System.out.println("✅ Aluno editado com sucesso!");
            } else {
                System.out.println("❌ Aluno não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao editar aluno: " + e.getMessage());
        }
    }

    public void alterarStatusAluno(String matricula) {
        try {
            Aluno aluno = alunoDAO.buscarPorMatricula(matricula);
            if (aluno != null) {
                aluno.setAtivo(!aluno.isAtivo());
                alunoDAO.atualizarAluno(aluno);
                System.out.println("✅ Status do aluno alterado com sucesso!");
            } else {
                System.out.println("❌ Aluno não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao alterar status do aluno: " + e.getMessage());
        }
    }

    public void adicionarCurso(String nome, int codigo) {
        Curso curso = new Curso(nome, codigo);
        try {
            cursoDAO.adicionarCurso(curso);
            System.out.println("✅ Curso adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao adicionar curso: " + e.getMessage());
        }
    }

    public void editarCurso(String codigo, String novoNome) {
        try {
            Curso curso = cursoDAO.buscarPorNome(codigo);
            if (curso != null) {
                curso.setNome(novoNome);
                cursoDAO.atualizarCurso(curso);
                System.out.println("✅ Curso editado com sucesso!");
            } else {
                System.out.println("❌ Curso não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao editar curso: " + e.getMessage());
        }
    }

    public void excluirCurso(String codigo) {
        try {
            Curso curso = cursoDAO.buscarPorNome(codigo);
            if (curso != null) {
                cursoDAO.removerCurso(curso);
                System.out.println("✅ Curso excluído com sucesso!");
            } else {
                System.out.println("❌ Curso não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao excluir curso: " + e.getMessage());
        }
    }



}