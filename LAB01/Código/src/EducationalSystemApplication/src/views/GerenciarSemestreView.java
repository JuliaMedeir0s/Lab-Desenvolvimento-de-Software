package views;

import java.util.Scanner;

import DAO.SemestreDAO;

import java.time.LocalDate;
import controller.SemestreController;
import models.Semestre;
import utils.Utils;

public class GerenciarSemestreView {

    private static final SemestreDAO semestreDAO = SemestreDAO.getInstance();
    private static final SemestreController semestreController = new SemestreController();
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrarMenu() {
        int opcao;
        do {
            Utils.limparTela();
            System.out.println("\n===== GERENCIAR SEMESTRES =====");
            System.out.println("1. Criar Semestre");
            System.out.println("2. Editar Semestre");
            System.out.println("3. Fechar Semestre");
            System.out.println("4. Listar Semestres");
            System.out.println("0. Voltar ao Menu Anterior");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    criarSemestre();
                    break;
                case 2:
                    editarSemestre();
                    break;
                case 3:
                    fecharSemestre();
                    break;
                case 4:
                    listarSemestres();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.");
                    Utils.pausarTela();
            }
        } while (opcao != 0);
    }

    private static void criarSemestre() {
        Utils.limparTela();
        System.out.println("===== CRIAR SEMESTRE =====");
        System.out.print("Ano: ");
        int ano = sc.nextInt();
        System.out.print("Período: ");
        int periodo = sc.nextInt();
        System.out.print("Data de início (YYYY-MM-DD): ");
        LocalDate inicio = LocalDate.parse(sc.next());
        System.out.print("Data de fim (YYYY-MM-DD): ");
        LocalDate fim = LocalDate.parse(sc.next());
        semestreController.criarSemestre(ano, periodo, inicio, fim);
        System.out.println("✅ Semestre criado com sucesso!");
        Utils.pausarTela();
    }

    private static void editarSemestre() {
        Utils.limparTela();
        System.out.println("===== EDITAR SEMESTRE =====");
        System.out.print("Ano do Semestre: ");
        int ano = sc.nextInt();
        System.out.print("Período do Semestre: ");
        int periodo = sc.nextInt();
        Semestre semestre = semestreController.buscarSemestre(ano, periodo);
        if (semestre != null) {
            System.out.println("Deseja alterar a data de início do semestre (S/N)?");
            String resposta = sc.next();
            if (resposta.equalsIgnoreCase("S")) {
                System.out.print("Nova data de início (YYYY-MM-DD): ");
                LocalDate novoInicio = LocalDate.parse(sc.next());
                semestre.setDataInicioInscricoes(novoInicio);
            }
            System.out.println("Deseja alterar a data de fim do semestre (S/N)?");
            resposta = sc.next();
            if (resposta.equalsIgnoreCase("S")) {
                System.out.print("Nova data de fim (YYYY-MM-DD): ");
                LocalDate novoFim = LocalDate.parse(sc.next());
                semestre.setDataFimInscricoes(novoFim);
            }
            System.out.println("Deseja atualizar a situacao do semestre (S/N)?");
            System.out.println("O semestre está " + semestre.getStatus());
            resposta = sc.next();
            if (resposta.equalsIgnoreCase("S")) {
                semestre.abrirInscricoes();
                System.out.println("O semestre está " + semestre.getStatus());
            }
            semestreDAO.atualizarSemestre(semestre);
            System.out.println("✅ Semestre atualizado com sucesso!");
        } else {
            System.out.println("❌ Semestre não encontrado!");
        }
        Utils.pausarTela();
    }

    private static void fecharSemestre() {
        Utils.limparTela();
        System.out.println("===== FECHAR SEMESTRE =====");
        System.out.print("Ano do Semestre: ");
        int ano = sc.nextInt();
        System.out.print("Período do Semestre: ");
        int periodo = sc.nextInt();
        Semestre semestre = semestreController.buscarSemestre(ano, periodo);
        if (semestre != null) {
            semestreController.fecharSemestre(semestre);
            System.out.println("✅ Semestre fechado com sucesso!");
        } else {
            System.out.println("❌ Semestre não encontrado!");
        }
        Utils.pausarTela();
    }

    private static void listarSemestres() {
        Utils.limparTela();
        System.out.println("\n===== SEMESTRES CADASTRADOS =====");
        semestreController.listarSemestres().forEach(semestre -> {
            System.out.println("- " + semestre.getAno() + "." + semestre.getPeriodo() + " - " + semestre.getStatus());
        });
        Utils.pausarTela();
    }

}