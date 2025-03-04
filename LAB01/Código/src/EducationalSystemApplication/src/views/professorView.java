package views;

import java.util.Scanner;

import controller.TeacherController;


public class professorView {


    public static void menu() {
        TeacherController teacherController = new TeacherController();
        System.out.println("\n=== Menu do Professor ===");
        System.out.println("1. Listar disciplinas");
        System.out.println("2. Sair");
        System.out.print("Escolha uma opção: ");
        Scanner sc = new Scanner(System.in);
       
        int op = sc.nextInt();

        if(op == 1) {
            teacherController.listarDisciplinas(null);;
        } else if(op == 2) {
            System.out.println("Saindo...");
        } else {
            System.out.println("Opção inválida!");
        }

    }

    public static void listarDisciplinas(Object professor) {
        TeacherController teacherController = new TeacherController();
        System.out.println("\n=== Disciplinas Lecionadas ===");
        teacherController.listarDisciplinas(professor);
        System.out.println("Digite o código da disciplina para ver os alunos matriculados ou 0 para voltar: ");
        Scanner sc = new Scanner(System.in);
        String codigo = sc.nextLine();
        if(!codigo.equals("0")) {
            teacherController.listarAlunos(codigo);
        }
        
    }
    
    
}
