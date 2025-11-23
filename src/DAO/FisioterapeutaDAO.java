package DAO;

import Model.Fisioterapeuta;

import java.io.*;
import java.util.*;

public class FisioterapeutaDAO {

    private static final String ARQUIVO = "fisioterapeutas.txt";

    public void salvar(Fisioterapeuta f) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            bw.write(f.getNome() + ";" + f.getCpf() + ";" + f.getTelefone() + ";" + f.getCrefito());
            bw.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Fisioterapeuta> listar() {
        List<Fisioterapeuta> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Fisioterapeuta f = new Fisioterapeuta(dados[0], dados[1], dados[2], dados[3]);
                lista.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Fisioterapeuta buscarPorCPF(String cpf) {
        return listar().stream()
                .filter(f -> f.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }
}

