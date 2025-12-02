package Model;

public class Tratamento {
    private String nome;
    private String descricao;
    private int duracaoMinutos;
    private double valor;

    public Tratamento(String nome, String descricao, int duracaoMinutos, double valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracaoMinutos = duracaoMinutos;
        this.valor = valor;
    }

    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public int getDuracaoMinutos() { return duracaoMinutos; }
    public double getValor() { return valor; }

    @Override
    public String toString() {
        return "Tratamento: " + nome +
                "\nDescrição: " + descricao +
                "\nDuração (min): " + duracaoMinutos +
                "\nValor: " + valor;
    }
}

