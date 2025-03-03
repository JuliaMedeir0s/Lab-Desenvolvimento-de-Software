package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.abstracts.Usuario;
import models.enums.TipoUsuario;

public class Secretaria extends Usuario {
    private List<Aluno> alunos;
    private List<Professor> professores;
    private List<Disciplina> disciplinas;
    private List<Curso> cursos;
    private Semestre semestreAtual;
    private LocalDate inicioInscricao;
    private LocalDate fimInscricao;

    public Secretaria(String nome, String email, String senha) {
        super(nome, email, senha, TipoUsuario.SECRETARIA);
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.semestreAtual = null;
        this.inicioInscricao = null;
        this.fimInscricao = null;
    }

    public void criarSemestre(int ano, int periodo, LocalDate inicio, LocalDate fim) {
        this.semestreAtual = new Semestre(ano, periodo, inicio, fim);
        System.out.println("Semestre Atual definido:\n" + semestreAtual.toString());
    }

    public void definirPeriodoInscricao(LocalDate inicio, LocalDate fim) {
        if (inicio.isBefore(fim)) {
            this.inicioInscricao = inicio;
            this.fimInscricao = fim;
            System.out.println("Período de inscrições definido: " + inicio + " a " + fim);
        } else {
            System.out.println("Erro: A data de início deve ser antes da data de fim.");
        }
    }

    public boolean isDentroDoPeriodoInscricao(LocalDate dataAtual) {
        return inicioInscricao != null && fimInscricao != null && !dataAtual.isBefore(inicioInscricao) && !dataAtual.isAfter(fimInscricao);
    }

    public void validarDisciplinasParaProximoSemestre() {
        if (semestreAtual == null) {
            System.out.println("Nenhum semestre definido para validação.");
            return;
        }

        List<Disciplina> disciplinasParaRemover = new ArrayList<>();

        for (Disciplina disciplina : semestreAtual.getDisciplinasOfertadas()) {
            if (!disciplina.verificarMinimoAlunos()) {
                System.out.println("A disciplina " + disciplina.getNome() + " será cancelada por não atingir o número mínimo de alunos.");
                disciplinasParaRemover.add(disciplina);
            }
        }

        semestreAtual.getDisciplinasOfertadas().removeAll(disciplinasParaRemover);
    }

    public void listarAlunos() {
        System.out.println("Lista de Alunos:");
        for (Aluno aluno : alunos) {
            System.out.println("- " + aluno.toString());
        }
    }

    public void listarProfessores() {
        System.out.println("Lista de Professores:");
        for (Professor professor : professores) {
            System.out.println("- " + professor.toString());
        }
    }

    public void listarCursos() {
        System.out.println("Lista de Cursos:");
        for (Curso curso : cursos) {
            System.out.println("- " + curso.toString());
        }
    }

    public void listarDisciplinas() {
        System.out.println("Lista de Disciplinas:");
        for (Disciplina disciplina : disciplinas) {
            System.out.println("- " + disciplina.toString());
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
