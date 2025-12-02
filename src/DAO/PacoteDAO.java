package DAO;

import Model.Pacote;
import Enums.PacoteStatus;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PacoteDAO {

    private static final String ARQUIVO = "pacotes.txt";

    public void salvar(Pacote p) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            // id;pacienteCpf;tratamentoNome;totalSessoes;sessoesUsadas;valorTotal;status
            bw.write(p.getId() + ";" + p.getPacienteCpf() + ";" + (p.getTratamentoNome()==null?"":p.getTratamentoNome())
                    + ";" + p.getTotalSessoes() + ";" + p.getSessoesUsadas() + ";" + p.getValorTotal() + ";" + p.getStatus());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Pacote> listar() {
        List<Pacote> lista = new ArrayList<>();
        File f = new File(ARQUIVO);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                if (d.length >= 7) {
                    String id = d[0];
                    String cpf = d[1];
                    String tratamento = d[2].isEmpty() ? null : d[2];
                    int total = Integer.parseInt(d[3]);
                    int usadas = Integer.parseInt(d[4]);
                    double valor = Double.parseDouble(d[5]);
                    PacoteStatus status = PacoteStatus.valueOf(d[6]);
                    lista.add(new Pacote(id, cpf, tratamento, total, usadas, valor, status));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Pacote buscarPorId(String idBusca) {
        for (Pacote p : listar()) if (p.getId().equals(idBusca)) return p;
        return null;
    }

    public List<Pacote> buscarPorPaciente(String cpf) {
        List<Pacote> res = new ArrayList<>();
        for (Pacote p : listar()) if (p.getPacienteCpf().equals(cpf)) res.add(p);
        return res;
    }

    public void atualizar(Pacote atualizado) {
        List<Pacote> todos = listar();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, false))) {
            for (Pacote p : todos) {
                if (p.getId().equals(atualizado.getId())) p = atualizado;
                bw.write(p.getId() + ";" + p.getPacienteCpf() + ";" + (p.getTratamentoNome()==null?"":p.getTratamentoNome())
                        + ";" + p.getTotalSessoes() + ";" + p.getSessoesUsadas() + ";" + p.getValorTotal() + ";" + p.getStatus());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

