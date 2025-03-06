package views;

import controller.AlunoController;
import controller.CursoController;
import models.Aluno;
import utils.Utils;

import java.util.Scanner;

public class GerenciarAlunosView {
    private static final AlunoController alunoController = new AlunoController();
    private static final CursoController cursoController = new CursoController();
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrarMenu() {
        while (true) {
            Utils.limparTela();
            System.out.println("===== GERENCIAR ALUNOS =====");
            System.out.println("1. Adicionar Aluno");
            System.out.println("2. Editar Aluno");
            System.out.println("3. Ativar/Desativar Aluno");
            System.out.println("4. Listar Alunos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = Utils.lerInteiro();
            switch (opcao) {
                case 1 -> adicionarAluno();
                case 2 -> editarAluno();
                case 3 -> alterarStatusAluno();
                case 4 -> listarAlunos();
                case 0 -> {
                    return;
                }
                default -> System.out.println("❌ Opção inválida.");
            }
        }
    }

    private static void adicionarAluno() {
        Utils.limparTela();
        System.out.println("===== ADICIONAR ALUNO =====");
    
        System.out.print("Nome do Aluno: ");
        String nome = sc.nextLine();
    
        System.out.print("E-mail do Aluno: ");
        String email = sc.nextLine();
    
        System.out.print("Senha do Aluno: ");
        String senha = sc.nextLine();
    
        System.out.println("\nLista de Cursos Ativos:");
        cursoController.listarCursosAtivos();
        System.out.print("\nEscolha o número do curso do aluno: ");
        int cursoIndex = Utils.lerInteiro();
    
        boolean sucesso = alunoController.adicionarAluno(nome, email, senha, cursoIndex);
        System.out.println(sucesso ? "✅ Aluno adicionado com sucesso!" : "❌ Erro ao adicionar aluno.");
        Utils.pausarTela();
    }
    
    private static void editarAluno() {
        listarAlunos();
        System.out.print("\nDigite o número do aluno que deseja editar: ");
        int index = Utils.lerInteiro();
    
        Aluno alunoSelecionado = alunoController.selecionarAluno(index);
        if (alunoSelecionado == null) {
            System.out.println("❌ Erro: Aluno não encontrado.");
            Utils.pausarTela();
            return;
        }
    
        System.out.println("\n📌 Aluno Selecionado:");
        System.out.println("Nome: " + alunoSelecionado.getNome());
        System.out.println("E-mail: " + alunoSelecionado.getEmail());
        System.out.println("Curso Atual: " + alunoSelecionado.getCurso().getNome());
    
        System.out.print("\nNovo Nome (ENTER para manter): ");
        String novoNome = sc.nextLine();
        System.out.print("Novo E-mail (ENTER para manter): ");
        String novoEmail = sc.nextLine();
        System.out.print("Nova Senha (ENTER para manter): ");
        String novaSenha = sc.nextLine();
    
        System.out.println("\nLista de Cursos Ativos:");
        cursoController.listarCursosAtivos();
        System.out.print("\nEscolha o número do novo curso do aluno (ENTER para manter): ");
        Integer cursoIndex = Utils.lerInteiroOpcional();
    
        System.out.print("\nConfirmar edição? (S/N): ");
        String confirmacao = sc.nextLine().trim().toUpperCase();
        if (!confirmacao.equals("S")) {
            System.out.println("❌ Edição cancelada.");
            Utils.pausarTela();
            return;
        }
    
        boolean sucesso = alunoController.editarAluno(index, novoNome, novoEmail, novaSenha, cursoIndex);
        System.out.println(sucesso ? "✅ Edição realizada com sucesso!" : "❌ Erro ao editar aluno.");
        Utils.pausarTela();
    }
    
    private static void alterarStatusAluno() {
        listarAlunos();
        System.out.print("\nDigite o número do aluno que deseja ativar/desativar: ");
        int index = Utils.lerInteiro();

        boolean sucesso = alunoController.alterarStatusAluno(index);
        System.out.println(sucesso ? "✅ Status do aluno alterado!" : "❌ Erro: Aluno não encontrado.");
        Utils.pausarTela();
    }

    private static void listarAlunos() {
        Utils.limparTela();
        alunoController.listarAlunos();
        Utils.pausarTela();
    }
}
