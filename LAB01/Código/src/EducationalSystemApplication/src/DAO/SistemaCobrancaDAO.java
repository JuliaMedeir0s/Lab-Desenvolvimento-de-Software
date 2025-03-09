package DAO;

import models.SistemaCobranca;

import java.util.HashMap;
import java.util.Map;

public class SistemaCobrancaDAO extends AbstractDao<SistemaCobranca> {

    private static final String FILE_NAME = "sistemaCobranca.dat";
    private static SistemaCobrancaDAO instance = new SistemaCobrancaDAO();
    private Map<Integer, SistemaCobranca> sistemaCobrancas;

    private SistemaCobrancaDAO() {
        super(FILE_NAME);
        sistemaCobrancas = new HashMap<>();
    }

    public static SistemaCobrancaDAO getInstance() {
        return instance;
    }

    public void salvarSistemaCobranca(int idAluno, SistemaCobranca sistemaCobranca) {
        sistemaCobrancas.put(idAluno, sistemaCobranca);
    }

    public SistemaCobranca buscarSistemaCobranca(int idAluno) {
        return sistemaCobrancas.get(idAluno);
    }

    public void atualizarSistemaCobranca(int idAluno, SistemaCobranca sistemaCobranca) {
        sistemaCobrancas.put(idAluno, sistemaCobranca);
    }

    public void excluirSistemaCobranca(int idAluno) {
        sistemaCobrancas.remove(idAluno);
    }

}
