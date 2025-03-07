package views;

import controller.DisciplinaController;
import controller.ProfessorController;
import models.Curso;
import models.Disciplina;
import models.Matricula;
import models.Professor;
import utils.Utils;

import java.util.List;
import java.util.Scanner;

public class GerenciarDisciplinasView {
    private static final DisciplinaController disciplinaController = new DisciplinaController();
    private static final ProfessorController professorController = new ProfessorController();
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrarMenu() {
        while (true) {
            Utils.limparTela();
            System.out.println("===== GERENCIAR DISCIPLINAS =====");
            System.out.println("1. Adicionar Disciplina");
            System.out.println("2. Editar Disciplina");
            System.out.println("3. Ativar/Desativar Disciplina");
            System.out.println("4. Listar Disciplinas");
            System.out.println("5. Visualizar Detalhes de uma Disciplina");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = Utils.lerInteiro();
            switch (opcao) {
                case 1 -> adicionarDisciplina();
                case 2 -> editarDisciplina();
                case 3 -> alterarStatusDisciplina();
                case 4 -> listarDisciplinas();
                case 5 -> visualizarDisciplina();
                case 0 -> {
                    return;
                }
                default -> System.out.println("❌ Opção inválida.");
            }
        }
    }

    private static void adicionarDisciplina() {
        Utils.limparTela();
        System.out.println("===== ADICIONAR DISCIPLINA =====");

        System.out.print("Nome da Disciplina (0 para cancelar): ");
        String nome = sc.nextLine();
        if (nome.equals("0")) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }

        System.out.print("Carga Horária (0 para cancelar): ");
        int cargaHoraria = Utils.lerInteiro();
        if (cargaHoraria == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }

        System.out.println("\n=== Professores Ativos ===");
        List<Professor> professores = professorController.listarProfessoresAtivos();
        if (professores.isEmpty()) {
            System.out.println("❌ Nenhum professor ativo disponível.");
        } else {
            for (int i = 0; i < professores.size(); i++) {
                System.out.printf("%d - %s (%s)\n", i + 1, professores.get(i).getNome(), professores.get(i).getEmail());
            }
        }
        System.out.print("\nEscolha o número do professor (ENTER para sem professor, 0 para cancelar): ");
        Integer professorIndex = Utils.lerInteiroOpcional();
        if (professorIndex != null && professorIndex == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }

        System.out.print("Valor da Disciplina (0 para cancelar): ");
        double valor = Utils.lerDouble();
        if (valor == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }

        String codigo = disciplinaController.gerarCodigo();
        boolean sucesso = disciplinaController.adicionarDisciplina(codigo, nome, cargaHoraria, professorIndex, valor);
        System.out.println(
                sucesso ? "✅ Disciplina adicionada com sucesso! Código: " + codigo : "❌ Erro ao adicionar disciplina.");
        Utils.pausarTela();
    }

    private static void editarDisciplina() {
        listarDisciplinas();
        System.out.print("\nDigite o número da disciplina que deseja editar (0 para cancelar): ");
        int index = Utils.lerInteiro();
        if (index == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }

        Disciplina disciplinaSelecionada = disciplinaController.selecionarDisciplina(index);
        if (disciplinaSelecionada == null) {
            System.out.println("❌ Erro: Disciplina não encontrada.");
            Utils.pausarTela();
            return;
        }

        System.out.println("\n📌 Disciplina Selecionada:");
        System.out.println("Código: " + disciplinaSelecionada.getCodigo());
        System.out.println("Nome: " + disciplinaSelecionada.getNome());
        System.out.println("Carga Horária: " + disciplinaSelecionada.getCargaHoraria());
        System.out.println("Professor: " + disciplinaSelecionada.getProfessor().getNome());
        System.out.println("Valor: " + disciplinaSelecionada.getValor());

        System.out.print("\nNovo Nome (ENTER para manter, 0 para cancelar): ");
        String novoNome = sc.nextLine();
        if (novoNome.equals("0")) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }

        System.out.print("Nova Carga Horária (ENTER para manter, 0 para cancelar): ");
        Integer novaCargaHoraria = Utils.lerInteiroOpcional();
        if (novaCargaHoraria != null && novaCargaHoraria == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }

        System.out.println("\n=== Professores Ativos ===");
        List<Professor> professores = professorController.listarProfessoresAtivos();
        if (professores.isEmpty()) {
            System.out.println("❌ Nenhum professor ativo disponível.");
        } else {
            for (int i = 0; i < professores.size(); i++) {
                System.out.printf("%d - %s (%s)\n", i + 1, professores.get(i).getNome(), professores.get(i).getEmail());
            }
        }
        System.out.print("\nEscolha o número do novo professor (ENTER para manter, 0 para cancelar): ");
        Integer professorIndex = Utils.lerInteiroOpcional();
        if (professorIndex != null && professorIndex == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }

        System.out.print("\nNovo Valor (ENTER para manter, 0 para cancelar): ");
        Double novoValor = Utils.lerDoubleOpcional();
        if (novoValor != null && novoValor == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }

        boolean sucesso = disciplinaController.editarDisciplina(index, novoNome, novaCargaHoraria, professorIndex,
                novoValor);
        System.out.println(sucesso ? "✅ Edição realizada com sucesso!" : "❌ Erro ao editar disciplina.");
        Utils.pausarTela();
    }

    private static void alterarStatusDisciplina() {
        listarDisciplinas();
        System.out.print("\nDigite o número da disciplina que deseja ativar/desativar: ");
        int index = Utils.lerInteiro();

        boolean sucesso = disciplinaController.alterarStatusDisciplina(index);
        System.out.println(sucesso ? "✅ Status da disciplina alterado!" : "❌ Erro: Disciplina não encontrada.");
        Utils.pausarTela();
    }

    private static void listarDisciplinas() {
        Utils.limparTela();
        disciplinaController.listarDisciplinas();
        Utils.pausarTela();
    }

    private static void visualizarDisciplina() {
        listarDisciplinas();
        System.out.print("\nDigite o número da disciplina que deseja visualizar (0 para cancelar): ");
        int index = Utils.lerInteiro();
        if (index == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }

        Disciplina disciplinaSelecionada = disciplinaController.selecionarDisciplina(index);
        if (disciplinaSelecionada == null) {
            System.out.println("❌ Erro: Disciplina não encontrada.");
            Utils.pausarTela();
            return;
        }

        System.out.println("\n===== DETALHES DA DISCIPLINA =====");
        System.out.println("Código: " + disciplinaSelecionada.getCodigo());
        System.out.println("Nome: " + disciplinaSelecionada.getNome());
        System.out.println("Carga Horária: " + disciplinaSelecionada.getCargaHoraria());
        System.out.println("Professor: "
                + (disciplinaSelecionada.getProfessor() != null ? disciplinaSelecionada.getProfessor().getNome()
                        : "N/A"));
        System.out.println("Valor: " + disciplinaSelecionada.getValor());
        System.out.println("Status: " + disciplinaSelecionada.getStatus());

        List<Matricula> matriculas = disciplinaSelecionada.getMatriculas();
        if (matriculas == null || matriculas.isEmpty()) {
            System.out.println("📌 Nenhum aluno matriculado.");
        } else {
            System.out.println("📌 Alunos matriculados:");
            for (Matricula m : matriculas) {
                System.out.println("- " + m.getAluno().getNome());
            }
        }

        if (disciplinaSelecionada.getCursos() == null || disciplinaSelecionada.getCursos().isEmpty()) {
            System.out.println("📌 Nenhum curso associado.");
        } else {
            System.out.println("\n📌 Cursos Associados:");
            for (Curso curso : disciplinaSelecionada.getCursos()) {
                System.out.println("- " + curso.getNome());
            }
        }

        Utils.pausarTela();
    }
}