package Controller;

import Model.Paciente;
import Service.PacienteService;

public class PacienteController {

    private PacienteService service = new PacienteService();

    public void cadastrar(String nome, String cpf, String telefone, String dataNascimento) {

        Paciente p = new Paciente(nome, cpf, telefone, dataNascimento);

        try {
            service.cadastrar(p);
            System.out.println("Paciente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

