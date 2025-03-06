package views;

import models.Disciplina;
import models.Matricula;
import utils.Utils;
import models.Aluno;

import java.util.List;
import java.util.Scanner;

import controller.AlunoController;
import controller.SessaoController;



public class AlunoView {

    private static final Aluno aluno = (Aluno) SessaoController.getUsuarioLogado();
    private static Scanner sc = new Scanner(System.in);
    private static final AlunoController alunoController = new AlunoController();

    public static void mostrarMenu() {
        int opcao;

        do {
            System.out.println("\n===== MENU ALUNO =====");
            System.out.println("1 - Visualizar minhas Disciplinas");
            System.out.println("2 - Realizar Matrícula");
            System.out.println("0 - Logout");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    Utils.limparTela();
                    listarDisciplinasMatriculados(aluno.getMatriculas());
                    break;
                case 2:
                    Utils.limparTela();
                    System.out.println("Disciplinas Disponiveis");
                    listarDisciplinas(aluno.getCurso().getDisciplinas()); 
                    break;
                case 0:
                SessaoController.encerrarSessao();
                    System.out.println("Sessão encerrada.");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    public static void listarDisciplinasMatriculados(List<Matricula> matriculas){
        System.out.println("\nDisciplinas matriculadas:");
        if (matriculas.isEmpty()) {
            System.out.println("Você não está matriculado em nenhuma disciplina.");
            return;
        }

        for (Matricula matricula : matriculas) {
            System.out.println("- Código: " + matricula.getDisciplina().getCodigo());
            System.out.println("  Nome: " + matricula.getDisciplina().getNome());
            System.out.println("  Carga Horária: " + matricula.getDisciplina().getCargaHoraria() + " horas");
            System.out.println("  Professor: " + matricula.getDisciplina().getProfessor().getNome());
            System.out.println("  Valor: R$ " + String.format("%.2f", matricula.getDisciplina().getValor()));
            System.out.println();
        }

        System.out.println("Digite o código da disciplina para cancelar a matrícula ou 0 para voltar: ");
        String codigo = sc.nextLine();
        if (codigo.equals("0")) {
            mostrarMenu();
        } else {
            Disciplina disciplina = alunoController.buscarDisciplinaPorCodigo(codigo);
            if (disciplina != null) {
                cancelarMatricula(disciplina);
            } else {
                System.out.println("Disciplina não encontrada.");
                Utils.limparTela();
                listarDisciplinasMatriculados(matriculas);
            }
        }

    }

    public static void realizarMatricula(Disciplina disciplina) {
        if (alunoController.inscreverEmDisciplina(aluno, disciplina)) {
            System.out.println("Matrícula realizada com sucesso!");
        } else {
            System.out.println("Não foi possível realizar a matrícula.");
        }
    }

    public static void cancelarMatricula(Disciplina disciplina) {
        alunoController.desinscreverDeDisciplina(aluno, disciplina);
        System.out.println("Matrícula cancelada com sucesso!");
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

        System.out.println("Digite o código da disciplina para se inscrever ou 0 para voltar: ");
        String codigo = sc.nextLine();
        if (codigo.equals("0")) {
            mostrarMenu();
        } else {
            Disciplina disciplina = alunoController.buscarDisciplinaPorCodigo(codigo);
            if (disciplina != null) {
                realizarMatricula(disciplina);
            } else {
                System.out.println("Disciplina não encontrada.");
                Utils.limparTela();
                listarDisciplinas(disciplinasDisponiveis);
            }
        }
    }
}