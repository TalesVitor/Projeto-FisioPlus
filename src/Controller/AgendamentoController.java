package Controller;

import Service.AgendamentoService;

import java.util.List;
import Model.Agendamento;

public class AgendamentoController {

    private AgendamentoService service = new AgendamentoService();

    public void agendar(String pacienteCpf, String fisioterapeutaCpf, String tratamentoNome,
                        String data, String hora) {
        try {
            service.agendar(pacienteCpf, fisioterapeutaCpf, tratamentoNome, data, hora);
            System.out.println("Agendamento realizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao agendar: " + e.getMessage());
        }
    }

    public void listar() {
        List<Agendamento> lista = service.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum agendamento encontrado.");
            return;
        }
        for (Agendamento a : lista) {
            System.out.println("----------------------");
            System.out.println(a);
        }
    }
}

