package controller;

import DAO.*;
import java.time.LocalDate;
import java.util.List;
import models.Aluno;
import models.Curso;
import models.Disciplina;
import models.Professor;
import models.Semestre;

public class SecretariaController {
    private final AlunoDAO alunoDAO;
    private final ProfessorDAO professorDAO;
    private final CursoDAO cursoDAO;
    private final DisciplinaDAO disciplinaDAO;
    private final SemestreDAO semestreDAO;

    public SecretariaController() {
        this.alunoDAO = AlunoDAO.getInstance();
        this.professorDAO = ProfessorDAO.getInstance();
        this.cursoDAO = CursoDAO.getInstance();
        this.disciplinaDAO = DisciplinaDAO.getInstance();
        this.semestreDAO = SemestreDAO.getInstance();
    }

}