package controller;

import dao.SistemaCobrancaDAO;
import models.Aluno;
import models.SistemaCobranca;

import java.util.List;

public class SistemaCobrancaController {
    private static SistemaCobrancaDAO sistemaCobrancaDAO = SistemaCobrancaDAO.getInstance();

    public static void criarCobranca(int idCobranca, Aluno aluno, List<Matricula> matriculas) {
        SistemaCobranca sistemaCobranca = new SistemaCobranca(idCobranca, aluno, matriculas);
        sistemaCobrancaDAO.salvarSistemaCobranca(aluno.getId(), sistemaCobranca);
        System.out.println("Cobrança criada com sucesso para o aluno: " + aluno.getNome());
    }

    public static void processarPagamento(Aluno aluno) {
        SistemaCobranca sistemaCobranca = sistemaCobrancaDAO.buscarSistemaCobranca(aluno.getId());

        if (sistemaCobranca != null && !sistemaCobranca.isPaga()) {
            sistemaCobranca.pagar();
            sistemaCobrancaDAO.atualizarSistemaCobranca(aluno.getId(), sistemaCobranca);
            System.out.println("Pagamento realizado com sucesso!");
        } else if (sistemaCobranca == null) {
            System.out.println("Nenhuma cobrança encontrada para o aluno.");
        } else {
            System.out.println("A cobrança já está paga.");
        }
    }
    public static double calcularValorTotal(List<Matricula> matriculas) {
        return matriculas.stream()
                .mapToDouble(m -> m.getDisciplina().getValor())
                .sum();
    }
}