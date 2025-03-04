package controller;

import java.util.List;

import models.Aluno;
import models.Curso;
import models.Disciplina;

public class CursoController {

    public void listarAlunos(Curso curso) {
        List<Aluno> alunosMatriculados = curso.getAlunosMatriculados();
        if (alunosMatriculados.isEmpty()) {
            System.out.println("Nenhum aluno matriculado neste curso.");
        } else {
            System.out.println("Alunos matriculados no curso " + curso.getNome() + ":");
            for (Aluno aluno : alunosMatriculados) {
                System.out.println("- " + aluno.getNome());
            }
        }
    }

    public void listarDisciplinas(Curso curso) {
        List<Disciplina> disciplinas = curso.getDisciplinas();
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada para este curso.");
        } else {
            System.out.println("Disciplinas do curso " + curso.getNome() + ":");
            for (Disciplina disciplina : disciplinas) {
                System.out.println("- " + disciplina.getNome());
            }
        }
    }
}
