package Service;

import DAO.FinanceiroDAO;
import Model.TransacaoFinanceira;
import Enums.TipoTransacao;
import Enums.MetodoPagamento;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FinanceiroService {

    private FinanceiroDAO dao = new FinanceiroDAO();

    public void registrarEntrada(MetodoPagamento metodo, double valor, String data, String descricao, String pacienteCpf) throws Exception {
        if (valor <= 0) throw new Exception("Valor inválido.");
        String id = UUID.randomUUID().toString();
        TransacaoFinanceira t = new TransacaoFinanceira(id, TipoTransacao.ENTRADA, metodo, valor, data, descricao, pacienteCpf);
        dao.salvar(t);
    }

    public void registrarSaida(MetodoPagamento metodo, double valor, String data, String descricao) throws Exception {
        if (valor <= 0) throw new Exception("Valor inválido.");
        String id = UUID.randomUUID().toString();
        TransacaoFinanceira t = new TransacaoFinanceira(id, TipoTransacao.SAIDA, metodo, valor, data, descricao, null);
        dao.salvar(t);
    }

    public List<TransacaoFinanceira> listarTodas() {
        return dao.listar();
    }

    public double totalGeralEntradas() {
        return dao.listar().stream().filter(t -> t.getTipo() == TipoTransacao.ENTRADA).mapToDouble(t -> t.getValor()).sum();
    }

    public double totalGeralSaidas() {
        return dao.listar().stream().filter(t -> t.getTipo() == TipoTransacao.SAIDA).mapToDouble(t -> t.getValor()).sum();
    }

    public List<TransacaoFinanceira> listarPorMetodo(MetodoPagamento metodo) {
        return dao.listar().stream().filter(t -> t.getMetodo() == metodo).collect(Collectors.toList());
    }
}

