package views;

import java.util.Scanner;
import controller.DisciplinaController;
import models.Professor;
import java.util.List;

public class GerenciarDisciplinasView {
    private static final DisciplinaController disciplinaController = new DisciplinaController();
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrarMenu() {
        int opcao;
        do {
            limparConsole();
            System.out.println("\n===== GERENCIAR DISCIPLINAS =====");
            System.out.println("1. Adicionar Disciplina");
            System.out.println("2. Editar Disciplina");
            System.out.println("3. Desativar/Ativar Disciplina");
            System.out.println("4. Listar Disciplinas");
            System.out.println("0. Voltar ao Menu Anterior");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    adicionarDisciplina();
                    break;
                case 2:
                    editarDisciplina();
                    break;
                case 3:
                    alterarStatusDisciplina();
                    break;
                case 4:
                    disciplinaController.listarDisciplinas();
                    pausarTela();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.");
                    pausarTela();
            }
        } while (opcao != 0);
    }

    private static void adicionarDisciplina() {
        System.out.print("Nome da Disciplina: ");
        String nome = sc.nextLine();
        System.out.print("Código da Disciplina: ");
        String codigo = sc.nextLine();
        System.out.print("Carga Horária: ");
        int cargaHoraria = sc.nextInt();
        System.out.print("Valor da Disciplina: ");
        double valor = sc.nextDouble();
        sc.nextLine(); 
    
        //tem que listar professores antes da seleção
        List<Professor> professores = disciplinaController.listarProfessoresAtivos();
        Professor professorSelecionado = null;
        if (!professores.isEmpty()) {
            System.out.println("\n=== Professores Disponíveis ===");
            for (int i = 0; i < professores.size(); i++) {
                System.out.println((i + 1) + ". " + professores.get(i).getNome() + " (ID: " + professores.get(i).getId() + ")");
            }
            System.out.print("Escolha um professor (digite o número ou 0 para sem professor): ");
            int escolhaProfessor = sc.nextInt();
            sc.nextLine();
    
            if (escolhaProfessor > 0 && escolhaProfessor <= professores.size()) {
                professorSelecionado = professores.get(escolhaProfessor - 1);
            }
        }
    
        boolean sucesso = disciplinaController.adicionarDisciplina(nome, codigo, cargaHoraria, professorSelecionado, valor);
        System.out.println(sucesso ? "✅ Disciplina adicionada com sucesso!" : "❌ Erro: Já existe uma disciplina com esse código.");
        pausarTela();
    }

    private static void editarDisciplina() {
        // Listar disciplinas antes da edição
        System.out.println("\n=== Disciplinas Disponíveis ===");
        disciplinaController.listarDisciplinas();
    
        System.out.print("\nDigite o código da disciplina que deseja editar: ");
        String codigo = sc.nextLine();
    
        int opcao;
        do {
            limparConsole();
            System.out.println("\n=== Editar Disciplina ===");
            System.out.println("1. Editar Nome");
            System.out.println("2. Editar Carga Horária");
            System.out.println("3. Editar Professor");
            System.out.println("4. Editar Valor");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();
    
            boolean sucesso = false;
            switch (opcao) {
                case 1:
                    System.out.print("Novo Nome: ");
                    String novoNome = sc.nextLine();
                    sucesso = disciplinaController.editarNomeDisciplina(codigo, novoNome);
                    break;
                case 2:
                    System.out.print("Nova Carga Horária: ");
                    int novaCargaHoraria = sc.nextInt();
                    sc.nextLine();
                    sucesso = disciplinaController.editarCargaHorariaDisciplina(codigo, novaCargaHoraria);
                    break;
                case 3:
                    //listar professores antes
                    List<Professor> professores = disciplinaController.listarProfessoresAtivos();
                    Professor professorSelecionado = null;
                    if (!professores.isEmpty()) {
                        System.out.println("\n=== Professores Disponíveis ===");
                        for (int i = 0; i < professores.size(); i++) {
                            System.out.println((i + 1) + ". " + professores.get(i).getNome() + " (ID: " + professores.get(i).getId() + ")");
                        }
                        System.out.print("Escolha um professor (digite o número ou 0 para sem professor): ");
                        int escolhaProfessor = sc.nextInt();
                        sc.nextLine();
    
                        if (escolhaProfessor > 0 && escolhaProfessor <= professores.size()) {
                            professorSelecionado = professores.get(escolhaProfessor - 1);
                        }
                    }
                    sucesso = disciplinaController.editarProfessorDisciplina(codigo, professorSelecionado);
                    break;
                case 4:
                    System.out.print("Novo Valor: ");
                    double novoValor = sc.nextDouble();
                    sc.nextLine();
                    sucesso = disciplinaController.editarValorDisciplina(codigo, novoValor);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.");
            }
    
            System.out.println(sucesso ? "✅ Edição realizada com sucesso!" : "❌ Erro: Disciplina não encontrada.");
            pausarTela();
        } while (opcao != 0);
    }

    private static void alterarStatusDisciplina() {
        //listar as disciplinas antes
        System.out.println("\n=== Disciplinas Disponíveis ===");
        disciplinaController.listarDisciplinas();
    
        System.out.print("\nDigite o código da disciplina que deseja ativar/desativar: ");
        String codigo = sc.nextLine();
    
        boolean sucesso = disciplinaController.alterarStatusDisciplina(codigo);
        System.out.println(sucesso ? "✅ Status da disciplina alterado com sucesso!" : "❌ Erro: Disciplina não encontrada.");
        pausarTela();
    }

    private static void limparConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pausarTela() {
        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
    }
}
