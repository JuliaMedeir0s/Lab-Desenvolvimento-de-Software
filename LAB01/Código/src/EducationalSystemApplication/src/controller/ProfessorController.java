package controller;

import java.util.ArrayList;
import java.util.List;

import DAO.professorDAO;
import

import models.Aluno;
import models.Disciplina;
import models.Professor;
import models.Secretaria;
import models.abstracts.Usuario;
import views.professorView;

import controller.secretariaController;

public class ProfessorController {

    private professorDAO professorDAO;


    public void menu() {

    }

    public void listarDisciplinas(Object professor) {
        List<Disciplina> disciplinas = new ArrayList<>();
        disciplinas = ((Professor) professor).getDisciplinasLecionadas();
        if(disciplinas.isEmpty()){
            System.out.println("O professor " + ((Usuario) professor).getNome() + " não está lecionando nenhuma disciplina");
        } else {
            System.out.println("Disciplinas lecionadas por " + ((Usuario) professor).getNome() + ":");
            for(Disciplina d : disciplinas) {
                System.out.println("- " + d.getNome() + " (" + d.getCodigo() + ")");
            }
        }
    }

    public void listarAlunos(String codigo) {
        Disciplina disciplina;
        try{
        disciplina = buscarDisciplinaPorCodigo(codigo);
        } catch (Exception e) {
            System.out.println("Disciplina não encontrada!");
            return;
        }
        List<Aluno> alunos = new ArrayList<>();
        alunos = disciplina.getAlunosMatriculados();
        if(alunos.isEmpty()){
            System.out.println("Nenhum aluno matriculado na disciplina " + disciplina.getNome());
        } else {
            System.out.println("Alunos matriculados na disciplina " + disciplina.getNome() + ":");
            for(Aluno a : alunos) {
                System.out.println("- " + a.getNome() + " (" + a.getMatricula() + ")");
            }
        }
    }
    
}
