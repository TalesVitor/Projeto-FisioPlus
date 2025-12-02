package Controller;

import Service.PacoteService;
import Model.Pacote;

import java.util.List;

public class PacoteController {

    private PacoteService service = new PacoteService();

    public void cadastrar(String pacienteCpf, String tratamentoNome, int totalSessoes, double valorTotal) {
        try {
            service.cadastrar(pacienteCpf, tratamentoNome, totalSessoes, valorTotal);
            System.out.println("Pacote cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar pacote: " + e.getMessage());
        }
    }

    public void listar() {
        List<Pacote> lista = service.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum pacote cadastrado.");
            return;
        }
        for (Pacote p : lista) {
            System.out.println("----------------------");
            System.out.println(p);
        }
    }

    public void listarPorPaciente(String cpf) {
        List<Pacote> lista = service.listarPorPaciente(cpf);
        if (lista.isEmpty()) {
            System.out.println("Nenhum pacote para esse paciente.");
            return;
        }
        for (Pacote p : lista) {
            System.out.println("----------------------");
            System.out.println(p);
        }
    }


    public boolean utilizarSessaoDoPaciente(String cpf) {
        return service.utilizarSessaoDoPaciente(cpf);
    }

}
