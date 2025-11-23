package DAO;

import Model.Paciente;

import java.io.*;
import java.util.*;

public class PacienteDAO {

    private static final String ARQUIVO = "pacientes.txt";

    public void salvar(Paciente p) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            bw.write(p.getNome() + ";" + p.getCpf() + ";" + p.getTelefone() + ";" + p.getDataNascimento());
            bw.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Paciente> listar() {
        List<Paciente> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Paciente p = new Paciente(dados[0], dados[1], dados[2], dados[3]);
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Paciente buscarPorCPF(String cpf) {
        return listar().stream()
                .filter(p -> p.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }
}

