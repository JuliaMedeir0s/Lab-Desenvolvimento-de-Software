package controller;

import java.util.ArrayList;
import java.util.List;

import models.*;
import models.enums.*;

import DAO.*;



public class matriculaController {
    private final Aluno aluno;
    private final Matricula matricula;
    private final Disciplina disciplina;
    private StatusMatricula status;

    public matriculaController(Aluno aluno, Matricula matricula, Disciplina disciplina) {
        this.disciplina = DisciplinaDAO.getInsance();
        this.aluno = aluno;
        this.matricula = matricula;
    }

    public boolean confirmarMatricula() {
        if (disciplina.adicionarAluno(aluno)) {
            this.status = StatusMatricula.ATIVA;
            return true;
        }
        return false;
    }

    public boolean cancelarMatricula() {
        if (this.status == StatusMatricula.ATIVA) {
            disciplina.removerAluno(aluno);
            this.status = StatusMatricula.CANCELADA;
            return true;
        }
        return false;
    }

}
