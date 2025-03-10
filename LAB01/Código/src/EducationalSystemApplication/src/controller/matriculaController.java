package controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import models.*;
import models.enums.*;

import DAO.*;



public class MatriculaController {
    
    private static final AtomicInteger contadorMatricula = new AtomicInteger(1);
    private final AlunoDAO alunoDAO = AlunoDAO.getInstance();
    private final MatriculaDAO matriculaDAO = MatriculaDAO.getInstance();

    private static String gerarCodigo() {
        return String.format("MAT-%04d", contadorMatricula.getAndIncrement());
    }

    public List<Aluno> listarAlunos() {
        return alunoDAO.getAlunos();
    }

    public Aluno buscarPorNome(String nome) {
        return alunoDAO.buscarPorNome(nome);
    }

    public Aluno buscarPorMatricula(String matricula) {
        return alunoDAO.buscarPorMatricula(matricula).get();
    }

    public boolean matricularAluno(Aluno aluno, Disciplina disciplina) {
        if (aluno == null || disciplina == null) {
            System.out.println("❌ Erro: Aluno ou disciplina inválida.");
            return false;
        }
        
        Optional<Matricula> matriculaExistente = matriculaDAO.buscarMatricula(aluno, disciplina);
        if (matriculaExistente.isPresent()) {
            System.out.println("❌ Erro: O aluno já está matriculado nesta disciplina.");
            return false;
        }

        Matricula novaMatricula = new Matricula(gerarCodigo() ,aluno, disciplina);
    
        boolean sucesso = matriculaDAO.adicionarMatricula(novaMatricula);
    
        if (sucesso) {
            System.out.println("✅ Matrícula realizada com sucesso! Código: " + novaMatricula.getCodigo());
            
            return true;
        } else {
            System.out.println("❌ Erro: Não foi possível realizar a matrícula.");
            return false;
        }
    }

    public boolean cancelarMatricula(String codigoMatricula) {
        Optional<Matricula> matriculaOpt = matriculaDAO.buscarPorCodigo(codigoMatricula);
        if (matriculaOpt.isEmpty()) {
            System.out.println("❌ Erro: Matrícula não encontrada.");
            return false;
        }

        Matricula matricula = matriculaOpt.get();
        matriculaDAO.removerMatricula(matricula);
        System.out.println("✅ Matrícula " + matricula.getCodigo() + " cancelada com sucesso!");
        return true;
    }

    public void listarMatriculas() {
        List<Matricula> matriculas = matriculaDAO.getMatriculas();
        if (matriculas.isEmpty()) {
            System.out.println("📌 Nenhuma matrícula registrada.");
            return;
        }

        System.out.println("\n=== Lista de Matrículas ===");
        System.out.println("Código   | Aluno                     | Disciplina");
        System.out.println("-------------------------------------------------");

        for (Matricula m : matriculas) {
            System.out.printf("%-8s | %-25s | %-20s\n", m.getCodigo(), m.getAluno().getNome(), m.getDisciplina().getNome());
        }
    }

    public List<Matricula> listarMatriculasPorAluno(Aluno aluno) {
        return matriculaDAO.listarMatriculaPorAluno(aluno);
    }

    public List<Matricula> listarMatriculasPorDisciplina(Disciplina disciplina) {
        return matriculaDAO.listarMatriculaPorDisciplina(disciplina);
    }

}
