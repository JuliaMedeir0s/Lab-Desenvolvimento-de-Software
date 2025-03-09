package views;

import controller.CursoController;
import controller.DisciplinaController;
import models.Curso;
import models.Disciplina;
import utils.Utils;

import java.util.List;
import java.util.Scanner;

import DAO.DisciplinaDAO;

public class GerenciarCursosView {
    private static final CursoController cursoController = new CursoController();
    private static final DisciplinaController disciplinaController = new DisciplinaController();
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrarMenu() {
        while (true) {
            Utils.limparTela();
            System.out.println("===== GERENCIAR CURSOS =====");
            System.out.println("1. Adicionar Curso");
            System.out.println("2. Editar Curso");
            System.out.println("3. Ativar/Desativar Curso");
            System.out.println("4. Listar Cursos");
            System.out.println("5. Adicionar Disciplina ao Curso");
            System.out.println("6. Adicionar Disciplina optativas ao Curso");
            System.out.println("7. Visualizar Curso e Disciplinas");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = Utils.lerInteiro();
            switch (opcao) {
                case 1 -> adicionarCurso();
                case 2 -> editarCurso();
                case 3 -> alterarStatusCurso();
                case 4 -> listarCursos();
                case 5 -> adicionarDisciplinaAoCurso();
                case 6 -> adicionarDisciplinaOptativaAoCurso();
                case 7 -> visualizarCurso();
                case 0 -> {
                    return;
                }
                default -> System.out.println("❌ Opção inválida.");
            }
        }
    }

    private static void adicionarCurso() {
        Utils.limparTela();
        System.out.println("===== ADICIONAR CURSO =====");

        System.out.print("Nome do Curso (0 para cancelar): ");
        String nome = sc.nextLine();
        if (nome.equals("0")) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }

        System.out.print("Créditos do Curso (0 para cancelar): ");
        int creditos = Utils.lerInteiro();
        if (creditos == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }

        boolean sucesso = cursoController.adicionarCurso(nome, creditos);
        System.out.println(sucesso ? "✅ Curso adicionado com sucesso!" : "❌ Erro ao adicionar curso.");
        Utils.pausarTela();
    }

    private static void editarCurso() {
        listarCursos();
        System.out.print("\nDigite o número do curso que deseja editar (0 para cancelar): ");
        int index = Utils.lerInteiro();
        if (index == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }

        Curso cursoSelecionado = cursoController.selecionarCurso(index);
        if (cursoSelecionado == null) {
            System.out.println("❌ Erro: Curso não encontrado.");
            Utils.pausarTela();
            return;
        }

        System.out.println("\n📌 Curso Selecionado:");
        System.out.println("Código: " + cursoSelecionado.getCodigo());
        System.out.println("Nome: " + cursoSelecionado.getNome());
        System.out.println("Créditos: " + cursoSelecionado.getCreditos());

        System.out.print("\nNovo Nome (ENTER para manter, 0 para cancelar): ");
        String novoNome = sc.nextLine();
        if (novoNome.equals("0")) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }

        System.out.print("Novos Créditos (ENTER para manter, 0 para cancelar): ");
        Integer novosCreditos = Utils.lerInteiroOpcional();
        if (novosCreditos != null && novosCreditos == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }

        boolean sucesso = cursoController.editarCurso(index, novoNome, novosCreditos);
        System.out.println(sucesso ? "✅ Edição realizada com sucesso!" : "❌ Erro ao editar curso.");
        Utils.pausarTela();
    }

    private static void alterarStatusCurso() {
        listarCursos();
        System.out.print("\nDigite o número do curso que deseja ativar/desativar: ");
        int index = Utils.lerInteiro();

        boolean sucesso = cursoController.alterarStatusCurso(index);
        System.out.println(sucesso ? "✅ Status do curso alterado!" : "❌ Erro: Curso não encontrado.");
        Utils.pausarTela();
    }

    private static void listarCursos() {
        Utils.limparTela();
        cursoController.listarCursos();
        Utils.pausarTela();
    }

    private static void adicionarDisciplinaAoCurso() {
        listarCursos();
    
        //seleciona o curso pelo índice
        System.out.print("\nDigite o número do curso ao qual deseja adicionar uma disciplina (0 para cancelar): ");
        int cursoIndex = Utils.lerInteiro();
        if (cursoIndex == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        Curso cursoSelecionado = cursoController.selecionarCurso(cursoIndex);
        if (cursoSelecionado == null) {
            System.out.println("❌ Erro: Curso não encontrado.");
            Utils.pausarTela();
            return;
        }
    
        //faz a lista com as disciplinas disponíveis
        List<Disciplina> disciplinasDisponiveis = cursoController.listarDisciplinasDisponiveisParaCurso(cursoSelecionado);
        if (disciplinasDisponiveis.isEmpty()) {
            System.out.println("📌 Nenhuma disciplina disponível para adicionar.");
            Utils.pausarTela();
            return;
        }
    
        //mostra as disciplinas disponíveis com numeração
        System.out.println("\n=== Disciplinas Disponíveis ===");
        System.out.println(" Nº | Código   | Nome               | Carga Horária | Professor         | Valor   | Status ");
        System.out.println("--------------------------------------------------------------------------------------------");
    
        int i = 1;
        for (Disciplina disciplina : disciplinasDisponiveis) {
            String professorNome = disciplina.getProfessor() != null ? disciplina.getProfessor().getNome() : "N/A";
            System.out.printf(" %2d | %-8s | %-20s | %-13d | %-18s | %.2f | %s \n",
                    i, disciplina.getCodigo(), disciplina.getNome(), disciplina.getCargaHoraria(), professorNome,
                    disciplina.getValor(), disciplina.getStatus());
            i++;
        }
    
        //seleciona a disciplina pelo índice
        System.out.print("\nDigite o número da disciplina que deseja adicionar ao curso (0 para cancelar): ");
        int disciplinaIndex = Utils.lerInteiro();
        if (disciplinaIndex == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        Disciplina disciplinaSelecionada = disciplinaController.selecionarDisciplina(disciplinaIndex);
        if (disciplinaSelecionada == null) {
            System.out.println("❌ Erro: Disciplina não encontrada.");
            Utils.pausarTela();
            return;
        }
    
        //adiciona a disciplina ao curso
        boolean sucesso = cursoController.adicionarDisciplinaAoCurso(cursoSelecionado.getCodigo(), disciplinaSelecionada.getCodigo());
        System.out.println(sucesso ? "✅ Disciplina adicionada ao curso!" : "❌ Erro ao adicionar disciplina.");
        Utils.pausarTela();
    }    

    private static void adicionarDisciplinaOptativaAoCurso() {
        listarCursos();
    
        System.out.print("\nDigite o número do curso ao qual deseja adicionar uma disciplina optativa (0 para cancelar): ");
        int cursoIndex = Utils.lerInteiro();
        if (cursoIndex == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        Curso cursoSelecionado = cursoController.selecionarCurso(cursoIndex);
        if (cursoSelecionado == null) {
            System.out.println("❌ Erro: Curso não encontrado.");
            Utils.pausarTela();
            return;
        }
    
        List<Disciplina> disciplinasDisponiveis = cursoController.listarDisciplinasDisponiveisParaCurso(cursoSelecionado);
    
        if (disciplinasDisponiveis.isEmpty()) {
            System.out.println("📌 Nenhuma disciplina disponível para adicionar.");
            Utils.pausarTela();
            return;
        }
    
        //mostra as disciplinas disponíveis com numeração
        System.out.println("\n=== Disciplinas Disponíveis ===");
        System.out.println(" Nº | Código   | Nome               | Carga Horária | Status ");
        System.out.println("--------------------------------------------------------------");
    
        int i = 1;
        for (Disciplina disciplina : disciplinasDisponiveis) {
            System.out.printf(" %2d | %-8s | %-20s | %-13d | %s\n",
                    i, disciplina.getCodigo(), disciplina.getNome(), disciplina.getCargaHoraria(),
                    disciplina.getStatus());
            i++;
        }
    
        //seleciona a disciplina pelo índice
        System.out.print("\nDigite o número da disciplina que deseja adicionar como optativa (0 para cancelar): ");
        int disciplinaIndex = Utils.lerInteiro();
        if (disciplinaIndex == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        Disciplina disciplinaSelecionada = disciplinaController.selecionarDisciplina(disciplinaIndex);
        if (disciplinaSelecionada == null) {
            System.out.println("❌ Erro: Disciplina não encontrada.");
            Utils.pausarTela();
            return;
        }
    
        //adiciona a disciplina optativa ao curso
        boolean sucesso = cursoController.adicionarDisciplinaOptativaAoCurso(cursoSelecionado.getCodigo(), disciplinaSelecionada.getCodigo());
        System.out.println(sucesso ? "✅ Disciplina optativa adicionada ao curso!" : "❌ Erro ao adicionar disciplina.");
        Utils.pausarTela();
    }    

    private static void visualizarCurso() {
        listarCursos();
        System.out.print("\nDigite o número do curso que deseja visualizar (0 para cancelar): ");
        int cursoIndex = Utils.lerInteiro();
        if (cursoIndex == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }

        Curso cursoSelecionado = cursoController.visualizarCurso(cursoIndex);
        if (cursoSelecionado == null) {
            System.out.println("❌ Erro: Curso não encontrado.");
            Utils.pausarTela();
            return;
        }

        System.out.println("\n===== DETALHES DO CURSO =====");
        System.out.println("Código: " + cursoSelecionado.getCodigo());
        System.out.println("Nome: " + cursoSelecionado.getNome());
        System.out.println("Créditos: " + cursoSelecionado.getCreditos());
        System.out.println("Status: " + cursoSelecionado.getStatus());

        if (cursoSelecionado.getDisciplinas() != null && !cursoSelecionado.getDisciplinas().isEmpty()) {
            System.out.println("\n📌 Disciplinas Associadas:");
            for (Disciplina disciplina : cursoSelecionado.getDisciplinas()) {
                System.out.printf("- %s (%s)\n", disciplina.getNome(), disciplina.getCodigo());
            }
        } else {
            System.out.println("📌 Nenhuma disciplina associada.");
        }

        if (cursoSelecionado.getDisciplinasOptativas() != null && !cursoSelecionado.getDisciplinasOptativas().isEmpty()) {
            System.out.println("\n📌 Disciplinas Optativas:");
            for (Disciplina disciplina : cursoSelecionado.getDisciplinasOptativas()) {
                System.out.printf("- %s (%s)\n", disciplina.getNome(), disciplina.getCodigo());
            }
        } else {
            System.out.println("📌 Nenhuma disciplina associada.");
        }

        Utils.pausarTela();
    }
}