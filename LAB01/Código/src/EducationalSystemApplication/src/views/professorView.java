package views;

import models.Professor;

import java.util.List;
import java.util.Scanner;

import controller.SessaoController;
import models.Aluno;

public class ProfessorView {

    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU PROFESSOR =====");
            System.out.println("1 - Listar Alunos");
            System.out.println("2 - Gerenciar Notas");
            System.out.println("3 - Criar Nova Disciplina");
            System.out.println("0 - Logout");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Listando alunos...");
                    break;
                case 2:
                    System.out.println("Gerenciando notas...");
                    break;
                case 3:
                    System.out.println("Criando nova disciplina...");
                    break;
                case 0:
                    SessaoController.logout();
                    System.out.println("Sessão encerrada.");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
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
