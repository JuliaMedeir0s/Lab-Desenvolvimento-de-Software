package DAO;

import java.util.List;
import models.Semestre;

public class SemestreDAO extends AbstractDao<Semestre> {
    
    private static final String FILE_NAME = "semestres.dat";
    private static SemestreDAO instancia = new SemestreDAO();
    private List<Semestre> semestres;

    private SemestreDAO() {
        super(FILE_NAME);
        semestres = leitura();
    }

    public static SemestreDAO getInstance() {
        return instancia;
    }

    public void adicionarSemestre(Semestre semestre) {
        semestres.add(semestre);
        grava(semestres);
    }

    public void atualizarSemestre(Semestre semestreAtualizado) {
        for (int i = 0; i < semestres.size(); i++) {
            if (semestres.get(i).getAno() == semestreAtualizado.getAno() &&
                semestres.get(i).getPeriodo() == semestreAtualizado.getPeriodo()) {
                semestres.set(i, semestreAtualizado);
                grava(semestres);
                return;
            }
        }
    }

    public List<Semestre> listarSemestres() {
        return semestres;
    }

    public Semestre buscarSemestre(int ano, int periodo) {
        return semestres.stream()
                .filter(s -> s.getAno() == ano && s.getPeriodo() == periodo)
                .findFirst()
                .orElse(null);
    }
}