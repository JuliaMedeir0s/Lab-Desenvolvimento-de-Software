package DAO;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import models.Matricula;

public class matriculaDAO extends AbstractDao implements Serializable {

    private List<Matricula> matriculas = new ArrayList<>();
    private static matriculaDAO instance = new matriculaDAO();

    private static final String CAMINHO_MATRICULAS = "matriculas.dat";

    public matriculaDAO() {
        super(CAMINHO_MATRICULAS);
        this.matriculas = new ArrayList<>();
        carregarMatriculas();
    }

    public boolean adicionarMatricula(Matricula matricula) {
        try{
            this.matriculas.add(matricula);
            grava(matriculas);
            return true;
        }   catch (Exception e){
            return false;
        }
    }

    public boolean removerMatricula(Matricula matricula) {
        try{
            this.matriculas.remove(matricula);
            grava(matriculas);
            return true;
        }   catch (Exception e){
            return false;
        }
    }

    private void carregarMatriculas() {
        this.matriculas = leitura();
    }

    public List<Matricula> getMatriculas() {
        return this.matriculas;
    }


    public static matriculaDAO getInstance() {
        if (instance == null) {
            instance = new matriculaDAO();
        }
        return instance;
    }

}
