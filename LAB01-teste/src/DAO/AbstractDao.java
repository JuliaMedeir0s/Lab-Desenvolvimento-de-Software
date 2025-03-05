package DAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> {
    private final String arquivo;

    public AbstractDao(String nomeArquivo) {
        this.arquivo = "data/" + nomeArquivo;
        verificarDiretorio();
    }

    private void verificarDiretorio() {
        File pasta = new File("data");
        if (!pasta.exists()) {
            pasta.mkdir();
        }
    }

    public void grava(List<T> lista) {
        try (ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oo.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> leitura() {
        File file = new File(arquivo);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) oi.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}