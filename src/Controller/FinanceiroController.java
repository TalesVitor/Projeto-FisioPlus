package Controller;

import Service.FinanceiroService;
import Enums.MetodoPagamento;
import Model.TransacaoFinanceira;

import java.util.List;

public class FinanceiroController {

    private FinanceiroService service = new FinanceiroService();

    public void registrarEntrada(String metodoStr, double valor, String data, String descricao, String pacienteCpf) {
        try {
            MetodoPagamento metodo = MetodoPagamento.valueOf(metodoStr.toUpperCase());
            service.registrarEntrada(metodo, valor, data, descricao, pacienteCpf==null||pacienteCpf.isEmpty()?null:pacienteCpf);
            System.out.println("Entrada registrada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Método de pagamento inválido.");
        } catch (Exception e) {
            System.out.println("Erro ao registrar entrada: " + e.getMessage());
        }
    }

    public void registrarSaida(String metodoStr, double valor, String data, String descricao) {
        try {
            MetodoPagamento metodo = MetodoPagamento.valueOf(metodoStr.toUpperCase());
            service.registrarSaida(metodo, valor, data, descricao);
            System.out.println("Saída registrada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Método de pagamento inválido.");
        } catch (Exception e) {
            System.out.println("Erro ao registrar saída: " + e.getMessage());
        }
    }

    public void listarTodas() {
        List<TransacaoFinanceira> lista = service.listarTodas();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma transação registrada.");
            return;
        }
        for (TransacaoFinanceira t : lista) {
            System.out.println("----------------------");
            System.out.println(t);
        }
    }

    public void resumoGeral() {
        double entradas = service.totalGeralEntradas();
        double saidas = service.totalGeralSaidas();
        System.out.println("Resumo financeiro:");
        System.out.println("Total entradas: " + entradas);
        System.out.println("Total saídas: " + saidas);
        System.out.println("Saldo: " + (entradas - saidas));
    }
}
