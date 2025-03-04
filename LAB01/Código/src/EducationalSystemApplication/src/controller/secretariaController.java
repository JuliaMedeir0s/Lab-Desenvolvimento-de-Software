package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.Aluno;
import models.Curso;
import models.Disciplina;
import models.Professor;
import models.Secretaria;
import models.Semestre;

public class secretariaController {

    public void criarSemestre(Secretaria secretaria, int ano, int periodo, LocalDate inicio, LocalDate fim) {
        if (inicio.isBefore(fim)) {
            secretaria.setSemestreAtual(new Semestre(ano, periodo, inicio, fim));
            System.out.println("Semestre " + ano + "." + periodo + " criado com sucesso.");
        } else {
            System.out.println("Erro: A data de início deve ser antes da data de fim.");
        }
    }

    public void definirPeriodoInscricao(Secretaria secretaria, LocalDate inicio, LocalDate fim) {
        if (inicio.isBefore(fim)) {
            secretaria.setInicioInscricao(inicio);
            secretaria.setFimInscricao(fim);
            System.out.println("Período de inscrições definido: " + inicio + " a " + fim);
        } else {
            System.out.println("Erro: A data de início deve ser antes da data de fim.");
        }
    }

    public boolean isDentroDoPeriodoInscricao(Secretaria secretaria, LocalDate dataAtual) {
        return secretaria.getInicioInscricao() != null && secretaria.getFimInscricao() != null && !dataAtual.isBefore(secretaria.getInicioInscricao()) && !dataAtual.isAfter(secretaria.getFimInscricao());
    }

    public void validarDisciplinasParaProximoSemestre(Secretaria secretaria) {
        Semestre semestreAtual = secretaria.getSemestreAtual();
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

        public void listarAlunos(Secretaria secretaria) {
        List<Aluno> alunos = secretaria.getAlunos();
        System.out.println("Lista de Alunos:");
        for (Aluno aluno : alunos) {
            System.out.println("- " + aluno.toString());
        }
    }

    public void listarProfessores(Secretaria secretaria) {
        List<Professor> professores = secretaria.getProfessores();
        System.out.println("Lista de Professores:");
        for (Professor professor : professores) {
            System.out.println("- " + professor.toString());
        }
    }

    public void listarCursos(Secretaria secretaria) {
        List<Curso> cursos = secretaria.getCursos();
        System.out.println("Lista de Cursos:");
        for (Curso curso : cursos) {
            System.out.println("- " + curso.toString());
        }
    }

    public void listarDisciplinas(Secretaria secretaria) {
        List<Disciplina> disciplinas = secretaria.getDisciplinas();
        System.out.println("Lista de Disciplinas:");
        for (Disciplina disciplina : disciplinas) {
            System.out.println("- " + disciplina.toString());
        }
    }

    public Disciplina buscarDisciplinaPorCodigo(Secretaria secretaria, String codigo) {
        List<Disciplina> disciplinas = secretaria.getDisciplinas();
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getCodigo().equals(codigo)) {
                return disciplina;
            }
        }
        return null;
    }

    public Curso buscarCursoPorNome(Secretaria secretaria, String nome) {
        List<Curso> cursos = secretaria.getCursos();
        for (Curso curso : cursos) {
            if (curso.getNome().equals(nome)) {
                return curso;
            }
        }
        return null;
    }


}
