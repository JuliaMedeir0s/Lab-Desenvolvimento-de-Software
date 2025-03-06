package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import models.Aluno;
import models.Disciplina;
import models.Matricula;
import models.SistemaCobranca;
import models.enums.StatusMatricula;

import DAO.AlunoDAO;
import DAO.DisciplinaDAO;
import DAO.MatriculaDAO;

public class AlunoController{

    private final AlunoDAO alunoDAO = AlunoDAO.getInstance();
    private final DisciplinaDAO disciplinaDAO = DisciplinaDAO.getInstance();
    private final MatriculaDAO matriculaDAO = MatriculaDAO.getInstance();

    public List<Aluno> listarAlunos() {
        return alunoDAO.getAlunos();
    }

    public Aluno buscarPorNome(String nome) {
        return alunoDAO.buscarPorNome(nome);
    }

    public Aluno buscarPorMatricula(String matricula) {
        return alunoDAO.buscarPorMatricula(matricula);
    }

    public boolean adicionarAluno(Aluno aluno) {
        if (alunoDAO.buscarPorMatricula(aluno.getMatricula()) != null) {
            return false;
        }
        alunoDAO.adicionarAluno(aluno);
        return true;
    }

    public boolean inscreverEmDisciplina(Aluno aluno, Disciplina disciplina) {
        if (aluno.getMatriculas().size() < 6) {
            Matricula novaMatricula = new Matricula(aluno, disciplina);
            if (novaMatricula.confirmarMatricula()) {
                aluno.getMatriculas().add(novaMatricula);
                return true;
            }
        } else {
            System.out.println("Aluno " + aluno.getNome() + " já está matriculado no máximo permitido de 6 disciplinas.");
        }
        return false;
    }

    public boolean desinscreverDeDisciplina(Aluno aluno, Disciplina disciplina) {
        Matricula matriculaParaCancelar = null;
        for (Matricula m : aluno.getMatriculas()) {
            if (m.getDisciplina().equals(disciplina)) {
                matriculaParaCancelar = m;
                break;
            }
        }
        if (matriculaParaCancelar != null) {
            matriculaParaCancelar.cancelarMatricula();
            aluno.getMatriculas().remove(matriculaParaCancelar);
            return true;
        } else {
            System.out.println("Aluno " + aluno.getNome() + " não está matriculado na disciplina " + disciplina.getNome());
            return false;
        }
    }

    public Disciplina buscarDisciplinaPorCodigo(String codigo) {
        return disciplinaDAO.buscarDisciplinaporCodigo(codigo);
    }

    public void listarMatriculas(){
        if(matriculaDAO.getMatriculas().isEmpty()){
            System.out.println("Não está matriculado em nenhuma disciplina.");
            return;
        }
        System.out.println("\nDisciplinas matriculadas:");
        System.out.println("Código | Nome | Carga Horária | Professor | Valor | Status");
        for (Matricula matricula : matriculaDAO.getMatriculas()) {
            System.out.println(matricula.getDisciplina().getCodigo() + " | " + matricula.getDisciplina().getNome() + " | " + matricula.getDisciplina().getCargaHoraria() + " horas | " + matricula.getDisciplina().getProfessor().getNome() + " | R$ " + String.format("%.2f", matricula.getDisciplina().getValor()) + " | " + matricula.getStatus());
            System.out.println();
        }
    }

    public void notificarCobranca(SistemaCobranca sistemaCobranca, Aluno aluno) {
        double total = calcularValorTotal(aluno);
        if (total > 0) {
            sistemaCobranca.gerarCobranca(aluno, total);
        } else {
            System.out.println("Nenhuma cobrança necessária para " + aluno.getNome());
        }
    }

    public double calcularValorTotal(Aluno aluno) {
        double total = 0.0;
        for (Matricula m : aluno.getMatriculas()) {
            if (m.getStatus() == StatusMatricula.ATIVA) {
                total += m.getValor();
            }
        }
        return total;
    }

}
