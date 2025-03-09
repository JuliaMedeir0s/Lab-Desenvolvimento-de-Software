package controller;

import dao.SistemaSistemaCobrancaDAO;
import models.Aluno;
import models.SistemaCobranca;

public class SistemaSistemaCobrancaController {
    private static SistemaCobrancaDAO SistemaCobrancaDAO = new SistemaCobrancaDAO();

    public static void processarPagamento(Aluno aluno) {
        SistemaCobranca SistemaCobranca = SistemaCobrancaDAO.buscarSistemaCobranca(aluno.getId());

        if (SistemaCobranca != null && !SistemaCobranca.isPaga()) {
            SistemaCobranca.pagar();
            SistemaCobrancaDAO.atualizarSistemaCobranca(aluno.getId(), SistemaCobranca);
            System.out.println("Pagamento realizado com sucesso!");
        } else {
            System.out.println("Nenhuma cobrança pendente ou já paga.");
        }
    }
}
