package DAO;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import models.Matricula;

public class matriculaDAO extends AbstractDao implements Serializable {

    private List<Matricula> matriculas;
    private static matriculaDAO instance;

    public static matriculaDAO getInstance() {
        if (instance == null) {
            instance = new matriculaDAO();
        }
        return instance;
    }

    public matriculaDAO() {
        this.matriculas = new ArrayList<>();
    }

    public boolean adicionarMatricula(Matricula matricula) {
        return this.matriculas.add(matricula);
    }

    public boolean removerMatricula(Matricula matricula) {
        return this.matriculas.remove(matricula);
    }

    public List<Matricula> getMatriculas() {
        return this.matriculas;
    }


}
