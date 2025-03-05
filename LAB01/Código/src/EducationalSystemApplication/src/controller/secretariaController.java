package controller;

import DAO.*;
import java.time.LocalDate;
import java.util.List;
import models.Aluno;
import models.Curso;
import models.Disciplina;
import models.Professor;
import models.Semestre;

public class SecretariaController {
    private final AlunoDAO alunoDAO;
    private final ProfessorDAO professorDAO;
    private final CursoDAO cursoDAO;
    private final DisciplinaDAO disciplinaDAO;
    private final SemestreDAO semestreDAO;

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
        return cursoDAO.buscarPorNome(nome).orElse(null);
    }
}