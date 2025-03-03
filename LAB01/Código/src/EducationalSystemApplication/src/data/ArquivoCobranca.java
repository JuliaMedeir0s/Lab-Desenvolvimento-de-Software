package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import models.Aluno;
import models.Matricula;

public class ArquivoCobranca {
    private static final String DIRETORIO = "data/files/";

    private static void verificarDiretorio() {
        File diretorio = new File(DIRETORIO);
        if (!diretorio.exists()) {
            diretorio.mkdirs();  
        }
    }

    public static void salvarCobranca(Aluno aluno, double valor) {
        verificarDiretorio(); 

        String caminhoArquivo = DIRETORIO + "cobranca_" + aluno.getId() + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo))) {
            writer.println("Aluno: " + aluno.getNome() + " (" + aluno.getId() + ")");
            writer.println("Valor Total: R$" + valor);
            writer.println("Disciplinas Matriculadas:");

            for (Matricula matricula : aluno.getMatriculas()) {
                writer.println("    - " + matricula.getDisciplina().getNome() + " (" + matricula.getDisciplina().getCodigo() + ") - R$" + matricula.getValor());
            }

            writer.println("\nRegistro salvo em: " + caminhoArquivo);
            System.out.println("Cobrança salva em " + caminhoArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar cobrança: " + e.getMessage());
        }
    }
}
