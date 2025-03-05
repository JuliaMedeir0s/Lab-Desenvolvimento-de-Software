package views;

import java.util.Scanner;
import java.time.LocalDate;
import controller.SemestreController;
import models.Semestre;

public class GerenciarSemestreView {
    private static final SemestreController semestreController = new SemestreController();
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrar() {
        int opcao;
        do {
            limparConsole();
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
                    pausarTela();
            }
        } while (opcao != 0);
    }

    private static void criarSemestre() {
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
        pausarTela();
    }

    private static void editarSemestre() {
        System.out.print("Ano do Semestre: ");
        int ano = sc.nextInt();
        System.out.print("Período do Semestre: ");
        int periodo = sc.nextInt();
        Semestre semestre = semestreController.buscarSemestre(ano, periodo);
        if (semestre != null) {
            System.out.print("Nova data de início (YYYY-MM-DD): ");
            LocalDate novoInicio = LocalDate.parse(sc.next());
            System.out.print("Nova data de fim (YYYY-MM-DD): ");
            LocalDate novoFim = LocalDate.parse(sc.next());
            semestre.setDataInicioInscricoes(novoInicio);
            semestre.setDataFimInscricoes(novoFim);
            System.out.println("✅ Semestre atualizado com sucesso!");
        } else {
            System.out.println("❌ Semestre não encontrado!");
        }
        pausarTela();
    }

    private static void fecharSemestre() {
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
        pausarTela();
    }

    private static void listarSemestres() {
        semestreController.listarSemestres();
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