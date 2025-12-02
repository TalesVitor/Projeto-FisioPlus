package Controller;

import Model.Tratamento;
import Service.TratamentoService;

import java.util.List;

public class TratamentoController {

    private TratamentoService service = new TratamentoService();

    public void cadastrar(String nome, String descricao, int duracaoMinutos, double valor) {
        Tratamento t = new Tratamento(nome, descricao, duracaoMinutos, valor);
        try {
            service.cadastrar(t);
            System.out.println("Tratamento cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listar() {
        List<Tratamento> lista = service.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum tratamento cadastrado.");
            return;
        }
        for (Tratamento t : lista) {
            System.out.println("----------------------");
            System.out.println(t);
        }
    }
}

