package DAO;

import Model.Evolucao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EvolucaoDAO {

    private static final String ARQUIVO = "evolucoes.txt";

    public void salvar(Evolucao e) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            // id;pacienteCpf;data;descricao
            // cuidado: descricao pode conter ponto-e-vírgula; aqui assumimos que professor aceita texto simples sem ';'
            bw.write(e.getId() + ";" + e.getPacienteCpf() + ";" + e.getData() + ";" + e.getDescricao());
            bw.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<Evolucao> listar() {
        List<Evolucao> lista = new ArrayList<>();
        File f = new File(ARQUIVO);
        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                if (d.length >= 4) {
                    String id = d[0];
                    String pacienteCpf = d[1];
                    String data = d[2];
                    String descricao = d[3];
                    lista.add(new Evolucao(id, pacienteCpf, data, descricao));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public List<Evolucao> listarPorPaciente(String cpf) {
        List<Evolucao> resultado = new ArrayList<>();
        for (Evolucao e : listar()) {
            if (e.getPacienteCpf().equals(cpf)) resultado.add(e);
        }
        return resultado;
    }
}
