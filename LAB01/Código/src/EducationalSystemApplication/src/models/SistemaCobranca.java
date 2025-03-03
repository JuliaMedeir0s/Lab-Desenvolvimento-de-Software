package models;

import data.ArquivoCobranca;

public class SistemaCobranca {
    public void gerarCobranca(Aluno aluno, double valorTotal) {
        if (valorTotal > 0) {
            System.out.println("Gerando cobrança para " + aluno.getNome() + ": R$" + valorTotal);
            ArquivoCobranca.salvarCobranca(aluno, valorTotal);
        } else {
            System.out.println("Nenhuma cobrança necessária para " + aluno.getNome());
        }
    }
}
