package Model;

public class Fisioterapeuta extends Pessoa {

    private String crefito;

    public Fisioterapeuta(String nome, String cpf, String telefone, String crefito) {
        super(nome, cpf, telefone);
        this.crefito = crefito;
    }

    public String getCrefito() {
        return crefito;
    }
}
