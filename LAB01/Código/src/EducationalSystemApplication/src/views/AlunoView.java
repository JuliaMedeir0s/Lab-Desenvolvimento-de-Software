package views;

import models.Curso;
import models.Disciplina;
import models.Matricula;
import utils.Utils;
import models.Aluno;

import java.util.InputMismatchException;
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
            Utils.limparTela();
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
                    listarDisciplinas(); 
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

    public static void listarDisciplinasMatriculados(List<Matricula> matriculas) {
        System.out.println("\nDisciplinas matriculadas:");
        if (matriculas.isEmpty()) {
            System.out.println("Você não está matriculado em nenhuma disciplina.");
            return;
        }

        List<Disciplina> disciplinasObrigatorias = aluno.getCurso().getDisciplinas();
        List<Disciplina> disciplinasOptativas = aluno.getCurso().getDisciplinasOptativas();

        System.out.println("Disciplinas Obrigatórias:");
        for (Disciplina disciplina : disciplinasObrigatorias) {
            System.out.println("- Código: " + disciplina.getCodigo());
            System.out.println("  Nome: " + disciplina.getNome());
            System.out.println("  Carga Horária: " + disciplina.getCargaHoraria() + " horas");
            System.out.println("  Professor: " + disciplina.getProfessor().getNome());
            System.out.println("  Valor: R$ " + String.format("%.2f", disciplina.getValor()));
            System.out.println();
        }

        System.out.println("Disciplinas Optativas:");
        for (Disciplina disciplina : disciplinasOptativas) {
            System.out.println("- Código: " + disciplina.getCodigo());
            System.out.println("  Nome: " + disciplina.getNome());
            System.out.println("  Carga Horária: " + disciplina.getCargaHoraria() + " horas");
            System.out.println("  Professor: " + disciplina.getProfessor().getNome());
            System.out.println("  Valor: R$ " + String.format("%.2f", disciplina.getValor()));
            System.out.println();
        }

        System.out.println("Digite o código da disciplina para cancelar a matrícula ou 0 para voltar: ");
        String codigo = sc.nextLine();
        if (codigo.equals("0")) {
            mostrarMenu();
        } else {
            Optional<Disciplina> disciplina = alunoController.buscarDisciplinaPorCodigo(codigo);
            if (disciplina.isPresent()) {
                cancelarMatricula(disciplina.get());
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

    public static void listarDisciplinas() {
        List<Disciplina> disciplinasObrigatorias = aluno.getCurso().getDisciplinas();
        List<Disciplina> disciplinasOptativas = aluno.getCurso().getDisciplinasOptativas();
    
        System.out.println("\nEscolha o tipo de disciplina para listar:");
        System.out.println("1 - Disciplinas Obrigatórias");
        System.out.println("2 - Disciplinas Optativas");
        System.out.print("Digite a opção desejada (ou 0 para voltar): ");
        int opcao = sc.nextInt();
        sc.nextLine(); 
    
        List<Disciplina> disciplinasDisponiveis;
    
        switch (opcao) {
            case 1:
                disciplinasDisponiveis = disciplinasObrigatorias;
                System.out.println("\nDisciplinas Obrigatórias Disponíveis:");
                break;
            case 2:
                disciplinasDisponiveis = disciplinasOptativas;
                System.out.println("\nDisciplinas Optativas Disponíveis:");
                break;
            case 0:
                mostrarMenu();
                return;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                Utils.limparTela();
                listarDisciplinas();
                return;
        }
  
        if (disciplinasDisponiveis.isEmpty()) {
            System.out.println("Não há disciplinas disponíveis para inscrição.");
            Utils.limparTela();
            listarDisciplinas();
            return;
        }
    
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
                listarDisciplinas();
            }
        }
    }

    
}