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
    
        System.out.print("Nome do Aluno (0 para cancelar): ");
        String nome = sc.nextLine();
        if (nome.equals("0")) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        System.out.print("E-mail do Aluno (0 para cancelar): ");
        String email = sc.nextLine();
        if (email.equals("0")) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        System.out.print("Senha do Aluno (0 para cancelar): ");
        String senha = sc.nextLine();
        if (senha.equals("0")) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        System.out.println("\nLista de Cursos Ativos:");
        cursoController.listarCursosAtivos();
        System.out.print("\nEscolha o número do curso do aluno (ENTER para deixar sem curso, 0 para cancelar): ");
        String cursoIndexStr = sc.nextLine().trim();
        if (cursoIndexStr.equals("0")) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
        Integer cursoIndex = cursoIndexStr.isEmpty() ? null : Utils.converterParaInteiro(cursoIndexStr);
    
        boolean sucesso = alunoController.adicionarAluno(nome, email, senha, cursoIndex);
        System.out.println(sucesso ? "✅ Aluno adicionado com sucesso!" : "❌ Erro ao adicionar aluno.");
        Utils.pausarTela();
    }    

    private static void editarAluno() {
        Utils.limparTela();
        System.out.println("===== EDITAR ALUNO =====");
        listarAlunos();
        System.out.print("\nDigite o número do aluno que deseja editar (0 para cancelar): ");
        int index = Utils.lerInteiro();
        if (index == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        Aluno alunoSelecionado = alunoController.selecionarAluno(index);
        if (alunoSelecionado == null) {
            System.out.println("❌ Erro: Aluno não encontrado.");
            Utils.pausarTela();
            return;
        }
    
        System.out.println("\n📌 Aluno Selecionado:");
        System.out.println("Nome: " + alunoSelecionado.getNome());
        System.out.println("E-mail: " + alunoSelecionado.getEmail());
    
        if (alunoSelecionado.getCurso() != null) {
            System.out.println("Curso Atual: " + alunoSelecionado.getCurso().getNome());
        } else {
            System.out.println("Curso Atual: ❌ Nenhum curso atribuído");
        }
    
        System.out.print("\nNovo Nome (ENTER para manter, 0 para cancelar): ");
        String novoNome = sc.nextLine();
        if (novoNome.equals("0")) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        System.out.print("Novo E-mail (ENTER para manter, 0 para cancelar): ");
        String novoEmail = sc.nextLine();
        if (novoEmail.equals("0")) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        System.out.print("Nova Senha (ENTER para manter, 0 para cancelar): ");
        String novaSenha = sc.nextLine();
        if (novaSenha.equals("0")) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
        System.out.println("\nLista de Cursos Ativos:");
        cursoController.listarCursosAtivos();
        System.out.print("\nEscolha o número do novo curso do aluno (ENTER para manter, 0 para cancelar): ");
        Integer cursoIndex = Utils.lerInteiroOpcional();
        if (cursoIndex != null && cursoIndex == 0) {
            System.out.println("❌ Operação cancelada.");
            Utils.pausarTela();
            return;
        }
    
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
        Utils.limparTela();
        System.out.println("===== ALTERAR STATUS ALUNO =====");
        listarAlunos();
        System.out.print("\nDigite o número do aluno que deseja ativar/desativar: ");
        int index = Utils.lerInteiro();

        boolean sucesso = alunoController.alterarStatusAluno(index);
        System.out.println(sucesso ? "✅ Status do aluno alterado!" : "❌ Erro: Aluno não encontrado.");
        Utils.pausarTela();
    }

    private static void listarAlunos() {
        alunoController.listarAlunos();
        Utils.pausarTela();
    }
}
