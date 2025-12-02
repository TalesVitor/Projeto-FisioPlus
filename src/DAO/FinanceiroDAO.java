package DAO;

import Model.TransacaoFinanceira;
import Enums.TipoTransacao;
import Enums.MetodoPagamento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FinanceiroDAO {

    private static final String ARQUIVO = "financeiro.txt";

    public void salvar(TransacaoFinanceira t) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            // id;tipo;metodo;valor;data;descricao;pacienteCpf
            bw.write(t.getId() + ";" + t.getTipo() + ";" + t.getMetodo() + ";" + t.getValor()
                    + ";" + t.getData() + ";" + t.getDescricao().replaceAll(";", ",") + ";" + (t.getPacienteCpf()==null?"":t.getPacienteCpf()));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<TransacaoFinanceira> listar() {
        List<TransacaoFinanceira> lista = new ArrayList<>();
        File f = new File(ARQUIVO);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                if (d.length >= 7) {
                    String id = d[0];
                    TipoTransacao tipo = TipoTransacao.valueOf(d[1]);
                    MetodoPagamento metodo = MetodoPagamento.valueOf(d[2]);
                    double valor = Double.parseDouble(d[3]);
                    String data = d[4];
                    String descricao = d[5];
                    String cpf = d[6].isEmpty() ? null : d[6];
                    lista.add(new TransacaoFinanceira(id, tipo, metodo, valor, data, descricao, cpf));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
