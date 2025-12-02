package DAO;

import Model.Tratamento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TratamentoDAO {

    private static final String ARQUIVO = "tratamentos.txt";

    public void salvar(Tratamento t) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            // nome;descricao;duracaoMinutos;valor
            bw.write(t.getNome() + ";" + t.getDescricao() + ";" + t.getDuracaoMinutos() + ";" + t.getValor());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Tratamento> listar() {
        List<Tratamento> lista = new ArrayList<>();
        File f = new File(ARQUIVO);
        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                if (d.length >= 4) {
                    String nome = d[0];
                    String descricao = d[1];
                    int duracao = Integer.parseInt(d[2]);
                    double valor = Double.parseDouble(d[3]);
                    lista.add(new Tratamento(nome, descricao, duracao, valor));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Tratamento buscarPorNome(String nomeBusca) {
        for (Tratamento t : listar()) {
            if (t.getNome().equalsIgnoreCase(nomeBusca)) return t;
        }
        return null;
    }
}

