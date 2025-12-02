package Model;

import Enums.PacoteStatus;

public class Pacote {
    private String id;
    private String pacienteCpf;
    private String tratamentoNome; // opcional
    private int totalSessoes;
    private int sessoesUsadas;
    private double valorTotal;
    private PacoteStatus status;

    public Pacote(String id, String pacienteCpf, String tratamentoNome, int totalSessoes, int sessoesUsadas, double valorTotal, PacoteStatus status) {
        this.id = id;
        this.pacienteCpf = pacienteCpf;
        this.tratamentoNome = tratamentoNome;
        this.totalSessoes = totalSessoes;
        this.sessoesUsadas = sessoesUsadas;
        this.valorTotal = valorTotal;
        this.status = status == null ? PacoteStatus.ATIVO : status;
    }

    public String getId() { return id; }
    public String getPacienteCpf() { return pacienteCpf; }
    public String getTratamentoNome() { return tratamentoNome; }
    public int getTotalSessoes() { return totalSessoes; }
    public int getSessoesUsadas() { return sessoesUsadas; }
    public double getValorTotal() { return valorTotal; }
    public PacoteStatus getStatus() { return status; }

    public void usarSessao() {
        if (status == PacoteStatus.ENCERRADO) return;
        this.sessoesUsadas++;
        if (this.sessoesUsadas >= this.totalSessoes) {
            this.status = PacoteStatus.ENCERRADO;
        }
    }

    @Override
    public String toString() {
        return "Pacote ID: " + id +
                "\nPaciente CPF: " + pacienteCpf +
                "\nTratamento: " + (tratamentoNome == null ? "-" : tratamentoNome) +
                "\nTotal sessões: " + totalSessoes +
                "\nSessões usadas: " + sessoesUsadas +
                "\nSessões restantes: " + (totalSessoes - sessoesUsadas) +
                "\nValor total: " + valorTotal +
                "\nStatus: " + status;
    }
}

