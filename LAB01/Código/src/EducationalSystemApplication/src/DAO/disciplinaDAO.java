package DAO;

import java.util.List;
import java.util.Optional;
import models.Disciplina;

public class DisciplinaDAO extends AbstractDao<Disciplina> {
    private static final String FILE_NAME = "disciplinas.dat";
    private static DisciplinaDAO instancia = new DisciplinaDAO();
    private List<Disciplina> disciplinas;

    private DisciplinaDAO() {
        super(FILE_NAME);
        disciplinas = leitura(); 
    }

    public static DisciplinaDAO getInstance() {
        return instancia;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
        grava(disciplinas); 
    }

    public Disciplina buscarDisciplinaporCodigo(String codigo) {
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getCodigo().equals(codigo)) {
                return disciplina;
            }
        }
        return null;
    }

    public void editarDisciplina(String codigo, String novoNome, int novaCargaHoraria, double novoValor) {
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getCodigo().equals(codigo)) {
                disciplina.setNome(novoNome);
                disciplina.setCargaHoraria(novaCargaHoraria);
                disciplina.setValor(novoValor);
                grava(disciplinas);
                return;
            }
        }
    }

    public void alterarStatusDisciplina(String codigo, boolean ativa) {
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getCodigo().equals(codigo)) {
                disciplina.setAtiva(ativa);
                grava(disciplinas);
                return;
            }
        }
    }

    public Optional<Disciplina> buscarPorCodigo(String codigo) {
        return disciplinas.stream()
                .filter(d -> d.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinas;
    }

    public void atualizarDisciplina(Disciplina disciplinaAtualizada) {
        for (int i = 0; i < disciplinas.size(); i++) {
            if (disciplinas.get(i).getCodigo().equalsIgnoreCase(disciplinaAtualizada.getCodigo())) {
                disciplinas.set(i, disciplinaAtualizada);
                grava(disciplinas);
                return;
            }
        }
    }
}