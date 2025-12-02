package Model;

import Enums.StatusPresenca;

public class Agendamento {
    private String id;
    private String pacienteCpf;
    private String fisioterapeutaCpf;
    private String tratamentoNome;
    private String data; // yyyy-MM-dd
    private String hora; // HH:mm
    private String status; // Agendado, Concluído, Cancelado
    private StatusPresenca statusPresenca = StatusPresenca.PENDENTE;


    public Agendamento(String id, String pacienteCpf, String fisioterapeutaCpf,
                       String tratamentoNome, String data, String hora, String status, String statusPresenca) {
        this.id = id;
        this.pacienteCpf = pacienteCpf;
        this.fisioterapeutaCpf = fisioterapeutaCpf;
        this.tratamentoNome = tratamentoNome;
        this.data = data;
        this.hora = hora;
        this.status = status;
        this.statusPresenca = StatusPresenca.PENDENTE;
    }

    public Agendamento(String id, String pacienteCpf, String fisioterapeutaCpf, String tratamentoNome, String data, String hora, String agendado) {
    }

    // getters
    public String getId() { return id; }
    public String getPacienteCpf() { return pacienteCpf; }
    public String getFisioterapeutaCpf() { return fisioterapeutaCpf; }
    public String getTratamentoNome() { return tratamentoNome; }
    public String getData() { return data; }
    public String getHora() { return hora; }
    public String getStatus() { return status; }
    public StatusPresenca getStatusPresenca() { return statusPresenca; }

    // setters
    public void setStatus(String status) { this.status = status; }
    public void setStatusPresenca(StatusPresenca statusPresenca) {
        this.statusPresenca = statusPresenca;
    }

    @Override
    public String toString() {
        return "Agendamento ID: " + id +
                "\nPaciente CPF: " + pacienteCpf +
                "\nFisioterapeuta CPF: " + fisioterapeutaCpf +
                "\nTratamento: " + tratamentoNome +
                "\nData: " + data +
                "\nHora: " + hora +
                "\nStatus: " + status +
                "\nPresença: " + statusPresenca;
    }
}
