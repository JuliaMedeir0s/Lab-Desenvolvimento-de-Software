package controller;

import models.Aluno;
import models.Disciplina;

public class DisciplinaController {

    public Boolean adicionarAluno(Disciplina disciplina, Aluno aluno) {
        try{
            disciplina.adicionarAluno(aluno);
            System.out.println("Aluno " + aluno.getNome() + " adicionado com sucesso na disciplina " + disciplina.getNome());
            return true;
        }catch (Exception e){
            System.out.println("A disciplina " + disciplina.getNome() + " já atingiu o limite de alunos!");
            return false;
        }
    }

        public void listarAlunos(Disciplina disciplina) {
            if (disciplina.getAlunosMatriculados().isEmpty()) {
                System.out.println("Nenhum aluno matriculado na disciplina " + disciplina.getNome());
            } else {
                System.out.println("Alunos matriculados em " + disciplina.getNome() + ":");
                for (Aluno aluno : disciplina.getAlunosMatriculados()) {
                    System.out.println("- " + aluno.getNome());
                }
            }
        }
    
}

