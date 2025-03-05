package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import models.Secretaria;

public class secretariaDAO extends AbstractDao implements Serializable {

    private List<Secretaria> secretarias;
    private static secretariaDAO instance;

    public static secretariaDAO getInstance() {
        if (instance == null) {
            instance = new secretariaDAO();
        }
        return instance;
    }

    public secretariaDAO() {
        this.secretarias = new ArrayList<>();
    }

    public boolean adicionarSecretaria(Secretaria secretaria) {
        return this.secretarias.add(secretaria);
    }

    public boolean removerSecretaria(Secretaria secretaria) {
        return this.secretarias.remove(secretaria);
    }

    public List<Secretaria> getSecretarias() {
        return this.secretarias;
    }

}
