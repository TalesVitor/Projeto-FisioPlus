package Controller;

import Service.EvolucaoService;
import Model.Evolucao;

import java.util.List;

public class EvolucaoController {

    private EvolucaoService service = new EvolucaoService();

    public void cadastrar(String pacienteCpf, String data, String descricao) {
        try {
            service.cadastrar(pacienteCpf, data, descricao);
            System.out.println("Evolução cadastrada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar evolução: " + e.getMessage());
        }
    }

    public void listarPorPaciente(String cpf) {
        List<Evolucao> lista = service.listarPorPaciente(cpf);
        if (lista.isEmpty()) {
            System.out.println("Nenhuma evolução encontrada para esse paciente.");
            return;
        }
        for (Evolucao e : lista) {
            System.out.println("----------------------");
            System.out.println(e);
        }
    }
}
