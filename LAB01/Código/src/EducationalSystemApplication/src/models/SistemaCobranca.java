package models;

import java.util.List;

public class SistemaCobranca {
    private Aluno aluno;
    private double valorTotal;
    private boolean paga;

    public SistemaCobranca(Aluno aluno, List<Matricula> matriculas) {
        this.aluno = aluno;
        this.valorTotal = calcularValorTotal(matriculas);
        this.paga = false;
    }

    private double calcularValorTotal(List<Matricula> matriculas) {
        return matriculas.stream().mapToDouble(m -> m.getDisciplina().getValor()).sum();
    }

    public Aluno getAluno() {
        return aluno;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public boolean isPaga() {
        return paga;
    }

    public void setPaga(boolean paga) {
        this.paga = paga;
    }

    public void pagar() {
        this.paga = true;
    }
}
