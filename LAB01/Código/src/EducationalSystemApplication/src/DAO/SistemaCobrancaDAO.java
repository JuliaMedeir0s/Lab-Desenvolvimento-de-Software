package DAO;

import models.SistemaSistemaCobranca;

import java.util.HashMap;
import java.util.Map;

public class SistemaCobrancaDAO {
    private Map<Integer, SistemaCobranca> SistemaCobrancas = new HashMap<>();

    public void salvarSistemaCobranca(int idAluno, SistemaCobranca SistemaCobranca) {
        SistemaCobrancas.put(idAluno, SistemaCobranca);
    }

    public SistemaCobranca buscarSistemaCobranca(int idAluno) {
        return SistemaCobrancas.get(idAluno);
    }

    public void atualizarSistemaCobranca(int idAluno, SistemaCobranca SistemaCobranca) {
        SistemaCobrancas.put(idAluno, SistemaCobranca);
    }
}
