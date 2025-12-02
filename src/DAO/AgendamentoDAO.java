package DAO;

import Model.Agendamento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    private static final String ARQUIVO = "agendamentos.txt";

    public void salvar(Agendamento a) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            // id;pacienteCpf;fisioterapeutaCpf;tratamentoNome;data;hora;status;statusPresenca
            bw.write(a.getId() + ";" + a.getPacienteCpf() + ";" + a.getFisioterapeutaCpf() + ";" +
                    a.getTratamentoNome() + ";" + a.getData() + ";" + a.getHora() + ";" + a.getStatus()
                    + ";" + a.getStatusPresenca());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Agendamento> listar() {
        List<Agendamento> lista = new ArrayList<>();
        File f = new File(ARQUIVO);
        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                // backward compatibility: arquivos antigos tinham 7 campos (sem statusPresenca)
                if (d.length >= 7) {
                    String id = d[0];
                    String pacienteCpf = d[1];
                    String fisioterapeutaCpf = d[2];
                    String tratamentoNome = d[3];
                    String data = d[4];
                    String hora = d[5];
                    String status = d[6];
                    String statusPresenca = d.length >= 8 ? d[7] : "PENDENTE";
                    lista.add(new Agendamento(id, pacienteCpf, fisioterapeutaCpf, tratamentoNome, data, hora, status, statusPresenca));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Agendamento buscarPorId(String idBusca) {
        for (Agendamento a : listar()) {
            if (a.getId().equals(idBusca)) return a;
        }
        return null;
    }

    // Atualiza um agendamento (reescreve arquivo)
    public void atualizar(Agendamento atualizado) {
        List<Agendamento> todos = listar();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, false))) {
            for (Agendamento a : todos) {
                if (a.getId().equals(atualizado.getId())) {
                    a = atualizado; // substitui
                }
                // grava cada agendamento (8 campos)
                bw.write(a.getId() + ";" + a.getPacienteCpf() + ";" + a.getFisioterapeutaCpf() + ";" +
                        a.getTratamentoNome() + ";" + a.getData() + ";" + a.getHora() + ";" +
                        a.getStatus() + ";" + a.getStatusPresenca());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
