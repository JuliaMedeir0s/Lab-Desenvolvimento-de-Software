package DAO;

import models.SistemaCobranca;

import java.util.HashMap;
import java.util.Map;

public class SistemaCobrancaDAO {

    private static final String FILE_NAME = "sistemaCobranca.dat";
    private static SistemaCobrancaDAO instance = new SistemaCobrancaDAO();
    private Map<Integer, SistemaCobranca> sistemaCobrancas;

    private SistemaCobrancaDAO() {
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
    

    // private Map<Integer, SistemaCobranca> SistemaCobrancas = new HashMap<>();

    // public void salvarSistemaCobranca(int idAluno, SistemaCobranca SistemaCobranca) {
    //     SistemaCobrancas.put(idAluno, SistemaCobranca);
    // }

    // public SistemaCobranca buscarSistemaCobranca(int idAluno) {
    //     return SistemaCobrancas.get(idAluno);
    // }

    // public void atualizarSistemaCobranca(int idAluno, SistemaCobranca SistemaCobranca) {
    //     SistemaCobrancas.put(idAluno, SistemaCobranca);
    // }
}
