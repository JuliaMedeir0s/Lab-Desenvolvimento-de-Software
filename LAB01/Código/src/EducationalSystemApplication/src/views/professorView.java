package views;

import models.Professor;

import java.util.List;

import models.Aluno;

public class professorView {

    public static void exibirMenu() {
        System.out.println("\n=== Menu do Professor ===");
        System.out.println("1. Listar disciplinas");
        System.out.println("2. Sair");
        System.out.print("Escolha uma opção: "); 
    }
    
    public void exibirAlunos(List<Aluno> alunos) {
        if(alunos.isEmpty()){
            System.out.println("Nenhum aluno matriculado na disciplina");
        } else {
            System.out.println("Alunos matriculados na disciplina:");
            for(Aluno a : alunos) {
                System.out.println("- " + a.getNome() + " (" + a.getMatricula() + ")");
            }
        }
    }   
}
