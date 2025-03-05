package controller;

import java.util.ArrayList;
import java.util.List;

import models.Aluno;
import models.Disciplina;
import models.Matricula;
import models.SistemaCobranca;
import models.enums.StatusMatricula;

public class AlunoController{

    
    public boolean inscreverEmDisciplina(Disciplina disciplina) {
        if (matriculas.size() < 6) {
            Matricula novaMatricula = new Matricula(this, disciplina);
            if (novaMatricula.confirmarMatricula()) {
                matriculas.add(novaMatricula);
                return true;
            }
        } else {
            System.out.println("Aluno " + getNome() + " já está matriculado no máximo permitido de 6 disciplinas.");
        }
        return false;
    }

    public void desinscreverDeDisciplina(Disciplina disciplina) {
        Matricula matriculaParaCancelar = null;
        for (Matricula m : matriculas) {
            if (m.getDisciplina().equals(disciplina)) {
                matriculaParaCancelar = m;
                break;
            }
        }
        if (matriculaParaCancelar != null) {
            matriculaParaCancelar.cancelarMatricula();
            matriculas.remove(matriculaParaCancelar);
        } else {
            System.out.println("Aluno " + getNome() + " não está matriculado na disciplina " + disciplina.getNome());
        }
    }

    public void listarMatriculas() {
        if (matriculas.isEmpty()) {
            System.out.println(getNome() + " não está matriculado em nenhuma disciplina.");
        } else {
            System.out.println("Disciplinas de " + getNome() + ":");
            for (Matricula m : matriculas) {
                System.out.println("- " + m.getDisciplina().getNome() + " (" + m.getStatus() + ")");
            }
        }
    }

    public double calcularValorTotal() {
        double total = 0.0;
        for (Matricula m : matriculas) {
            if (m.getStatus() == StatusMatricula.ATIVA) {
                total += m.getValor();
            }
        }
        return total;
    }

    public void notificarCobranca(SistemaCobranca sistemaCobranca) {
        double total = calcularValorTotal();
        if (total > 0) {
            sistemaCobranca.gerarCobranca(this, total);
        } else {
            System.out.println("Nenhuma cobrança necessária para " + getNome());
        }
    }

}
