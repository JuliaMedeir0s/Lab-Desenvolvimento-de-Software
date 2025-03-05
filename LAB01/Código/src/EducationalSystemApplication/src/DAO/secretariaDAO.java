package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import models.Secretaria;

public class secretariaDAO extends AbstractDao implements Serializable {

    private List<Secretaria> secretarias = new ArrayList<>();
    private static secretariaDAO instance = new secretariaDAO();

    private static final String CAMINHO_SECRETARIA = "secretarias.dat";

    public secretariaDAO() {
        super(CAMINHO_SECRETARIA);
        this.secretarias = new ArrayList<>();
        carregarSecretarias();
    }


    public static secretariaDAO getInstance() {
        if (instance == null) {
            instance = new secretariaDAO();
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

    private void carregarSecretarias() {
        this.secretarias = leitura();
    }

    public List<Secretaria> getSecretarias() {
        return this.secretarias;
    }

}
