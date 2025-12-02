package Model;

import Enums.MetodoPagamento;
import Enums.TipoTransacao;

public class TransacaoFinanceira {
    private String id;
    private TipoTransacao tipo; // ENTRADA / SAIDA
    private MetodoPagamento metodo;
    private double valor;
    private String data; // yyyy-MM-dd
    private String descricao;
    private String pacienteCpf; // opcional, quando for pagamento do paciente

    public TransacaoFinanceira(String id, TipoTransacao tipo, MetodoPagamento metodo, double valor, String data, String descricao, String pacienteCpf) {
        this.id = id;
        this.tipo = tipo;
        this.metodo = metodo;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.pacienteCpf = pacienteCpf;
    }

    public String getId() { return id; }
    public TipoTransacao getTipo() { return tipo; }
    public MetodoPagamento getMetodo() { return metodo; }
    public double getValor() { return valor; }
    public String getData() { return data; }
    public String getDescricao() { return descricao; }
    public String getPacienteCpf() { return pacienteCpf; }

    @Override
    public String toString() {
        return "Transação ID: " + id +
                "\nTipo: " + tipo +
                "\nMétodo: " + metodo +
                "\nValor: " + valor +
                "\nData: " + data +
                "\nDescrição: " + descricao +
                (pacienteCpf==null?"":"\nPaciente CPF: " + pacienteCpf);
    }
}
