package models;

import java.util.ArrayList;
import java.util.List;

import models.abstracts.Usuario;
import models.enums.TipoUsuario;

public class Professor extends Usuario {
    private List<Disciplina> disciplinasLecionadas;

    public Professor(String nome, String email, String senha) {
        super(nome, email, senha, TipoUsuario.PROFESSOR);
        this.disciplinasLecionadas = new ArrayList<>();
    }

    public List<Disciplina> getDisciplinasLecionadas() {
        return disciplinasLecionadas;
    }

    public void adicionarDisciplina(Disciplina disciplina){
        disciplinasLecionadas.add(disciplina);
    }

    public void removerDisciplina(Disciplina disciplina){
        disciplinasLecionadas.remove(disciplina);
    }

    @Override
    public String toString() {
        return super.toString() + ", Disciplinas Lecionadas: " + disciplinasLecionadas.size();
    }
}
