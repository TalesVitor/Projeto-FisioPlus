package Model;

public class Evolucao {
    private String id;
    private String pacienteCpf;
    private String data; // yyyy-MM-dd
    private String descricao;

    public Evolucao(String id, String pacienteCpf, String data, String descricao) {
        this.id = id;
        this.pacienteCpf = pacienteCpf;
        this.data = data;
        this.descricao = descricao;
    }

    public String getId() { return id; }
    public String getPacienteCpf() { return pacienteCpf; }
    public String getData() { return data; }
    public String getDescricao() { return descricao; }

    @Override
    public String toString() {
        return "Evolução ID: " + id +
                "\nPaciente CPF: " + pacienteCpf +
                "\nData: " + data +
                "\nDescrição: " + descricao;
    }
}

