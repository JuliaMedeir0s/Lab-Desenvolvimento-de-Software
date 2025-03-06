package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import models.Secretaria;

public class SecretariaDAO extends AbstractDao implements Serializable {

    private List<Secretaria> secretarias = new ArrayList<>();
    private static SecretariaDAO instance = new SecretariaDAO();

    private static final String CAMINHO_SECRETARIA = "secretarias.dat";

    public SecretariaDAO() {
        super(CAMINHO_SECRETARIA);
        this.secretarias = new ArrayList<>();
        carregarSecretarias();
    }


    public static SecretariaDAO getInstance() {
        if (instance == null) {
            instance = new SecretariaDAO();
        }
        return instance;
    }

    public boolean adicionarSecretaria(Secretaria secretaria) {
        try{
            this.secretarias.add(secretaria);
            grava(secretarias);
            return true;
        }   catch (Exception e){
            return false;
        }
    }

    public boolean removerSecretaria(Secretaria secretaria) {
        try{
            this.secretarias.remove(secretaria);
            grava(secretarias);
            return true;
        }   catch (Exception e){
            return false;
        }
    }

    public boolean atualizarSecretaria(Secretaria secretaria) {
        try{
            for (Secretaria s : secretarias) {
                if (s.getId().equals(secretaria.getId())) {
                    s.setNome(secretaria.getNome());
                    s.setEmail(secretaria.getEmail());
                    s.setSenha(secretaria.getSenha());
                    grava(secretarias);
                    return true;
                }
            }
            return false;
        }   catch (Exception e){
            return false;
        }
    }

    public List<Secretaria> listarSecretarias() {
        return secretarias;
    }

    public int getContadorSecretarias() {
        return secretarias.size();
    }

    public Secretaria buscarSecretariaPorId(String id) {
        for (Secretaria secretaria : secretarias) {
            if (secretaria.getId().equals(id)) {
                return secretaria;
            }
        }
        return null;
    }

    private void carregarSecretarias() {
        this.secretarias = leitura();
    }

    public List<Secretaria> getSecretarias() {
        return this.secretarias;
    }

}
