package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> {
    private static final String PASTA_DADOS = "data/";
    private final String arquivo;

    public AbstractDao(String nomeArquivo) {
        this.arquivo = PASTA_DADOS + nomeArquivo;
        verificarDiretorio();
    }

    private void verificarDiretorio() {
        File pasta = new File(PASTA_DADOS);
        if (!pasta.exists()) {
            pasta.mkdir(); 
        }
    }

    public void grava(List lista) {
        try {
            File file = new File(arquivo);
            if (!file.exists()) {
                file.createNewFile();
            }
            try (ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(file))) {
                oo.writeObject(lista);
            }
            System.out.println("Dados gravados com sucesso em " + arquivo);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> leitura() {
        File file = new File(arquivo);
        if (!file.exists() || file.length() == 0) {
            System.out.println("Arquivo " + arquivo + " não encontrado ou vazio. Retornando lista vazia.");
            return new ArrayList<>();
        }

        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) oi.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar dados de " + arquivo + ". Excluindo arquivo corrompido.");
            file.delete();
            return new ArrayList<>();
        }
    }
}
