package views;

import models.Disciplina;
import models.Matricula;
import utils.Utils;
import models.Aluno;

import java.util.List;
import java.util.Optional;
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
            System.out.println("3 - Ver Fatura");
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
                    System.out.println("Disciplinas Disponíveis");
                    listarDisciplinas(aluno.getCurso().getDisciplinas()); 
                    break;
                case 3:
                    Utils.limparTela();
                    verificarCobranca();
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

    public static void verificarCobranca() {
        if (aluno == null) {
            System.out.println("Erro: Nenhum aluno definido.");
            return;
        }

        double valorTotal = SistemaCobrancaController.calcularValorTotal(aluno.getMatriculas());

        System.out.println("Valor total a ser pago: R$ " + String.format("%.2f", valorTotal));
        System.out.println("Deseja realizar o pagamento? (1 - Sim, 0 - Voltar)");

        int escolha = -1;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                escolha = sc.nextInt();
                sc.nextLine(); 

                if (escolha == 1 || escolha == 0) {
                    entradaValida = true;
                } else {
                    System.out.println("Entrada inválida. Digite 1 para pagar ou 0 para voltar.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número (1 para pagar ou 0 para voltar).");
                sc.nextLine();
            }
        }
        
        if (escolha == 1) {
            SistemaCobrancaController.processarPagamento(aluno);
            System.out.println("Pagamento realizado com sucesso!");
        } else {
            System.out.println("Voltando ao menu principal...");
            mostrarMenu();
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
            Optional<Disciplina> disciplina = alunoController.buscarDisciplinaPorCodigo(codigo);
            if (disciplina.isPresent()) {
                realizarMatricula(disciplina.get());
            } else {
                System.out.println("Disciplina não encontrada.");
                Utils.limparTela();
                listarDisciplinas(disciplinasDisponiveis);
            }
        }
    }

    
}