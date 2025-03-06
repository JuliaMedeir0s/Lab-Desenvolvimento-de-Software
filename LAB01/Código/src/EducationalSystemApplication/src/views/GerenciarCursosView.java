package views;

import controller.CursoController;
import models.Curso;
import utils.Utils;
import java.util.Scanner;

public class GerenciarCursosView {
    private static final CursoController cursoController = new CursoController();
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrarMenu() {
        while (true) {
            Utils.limparTela();
            System.out.println("===== GERENCIAR CURSOS =====");
            System.out.println("1. Adicionar Curso");
            System.out.println("2. Editar Curso");
            System.out.println("3. Ativar/Desativar Curso");
            System.out.println("4. Listar Cursos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = Utils.lerInteiro();
            switch (opcao) {
                case 1 -> adicionarCurso();
                case 2 -> editarCurso();
                case 3 -> alterarStatusCurso();
                case 4 -> listarCursos();
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
}