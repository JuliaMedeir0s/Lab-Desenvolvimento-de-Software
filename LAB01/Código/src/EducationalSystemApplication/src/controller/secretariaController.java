package controller;

import DAO.*;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import models.Aluno;
import models.Curso;
import models.Disciplina;
import models.Professor;
import models.Secretaria;
import models.Semestre;

public class SecretariaController {
    private final AlunoDAO alunoDAO;
    private final ProfessorDAO professorDAO;
    private final CursoDAO cursoDAO;
    private final DisciplinaDAO disciplinaDAO;
    private final SemestreDAO semestreDAO;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final AtomicInteger contadorSecretaria = new AtomicInteger(1);

    private final SecretariaDAO secretariaDAO = SecretariaDAO.getInstance();

    public SecretariaController() {
        this.alunoDAO = AlunoDAO.getInstance();
        this.professorDAO = ProfessorDAO.getInstance();
        this.cursoDAO = CursoDAO.getInstance();
        this.disciplinaDAO = DisciplinaDAO.getInstance();
        this.semestreDAO = SemestreDAO.getInstance();
    }

}