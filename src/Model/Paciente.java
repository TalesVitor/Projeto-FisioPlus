package Model;

public class Paciente extends Pessoa {

    private String dataNascimento;

    public Paciente(String nome, String cpf, String telefone, String dataNascimento) {
        super(nome, cpf, telefone);
        this.dataNascimento = dataNascimento;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
}

