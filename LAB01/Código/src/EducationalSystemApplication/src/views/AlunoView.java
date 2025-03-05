package views;

import models.Disciplina;
import models.Aluno;

import java.util.Scanner;

import controller.SessaoController;

public class AlunoView {
    public static void mostrarMenu(Aluno aluno, List<Disciplina> disciplinasDisponiveis) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU ALUNO =====");
            System.out.println("1 - Visualizar Disciplinas");
            System.out.println("2 - Realizar Matrícula");
            System.out.println("3 - Cancelar Matrícula");
            System.out.println("0 - Logout");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarDisciplinas(disciplinasDisponiveis);
                    break;
                case 2:
                    System.out.println("Realizando matrícula...");
                    break;
                case 3:
                    System.out.println("Cancelando matrícula...");
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
    public static void listarDisciplinas(List<Disciplina> disciplinasDisponiveis) {
        if (disciplinasDisponiveis.isEmpty()) {
            System.out.println("Não há disciplinas disponíveis para inscrição.");
            return;
        }

        System.out.println("\nDisciplinas disponíveis para inscrição:");
        for (Disciplina disciplina : disciplinasDisponiveis) {
            System.out.println("- Código: " + disciplina.getCodigo());
            System.out.println("  Nome: " + disciplina.getNome());
            System.out.println("  Carga Horária: " + disciplina.getCargaHoraria() + " horas");
            System.out.println("  Professor: " + disciplina.getProfessor().getNome());
            System.out.println("  Valor: R$ " + String.format("%.2f", disciplina.getValor()));
            System.out.println();
    }
}
}